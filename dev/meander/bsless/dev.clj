(ns meander.bsless.dev)

(defprotocol MonadPlus
  (-mzero [this])
  (-mplus [this that]))

(defprotocol Monad
  (-bind [this f])
  (-return [from this]))

(defprotocol MSplit
  ;; msplit :: m a → m (Maybe (a, m a))
  (-msplit [this]))

(defprotocol ILogicM
  ;; interleave :: m a → m a → m a
  (-interleave [this that])

  ;; >>- :: m a → (a → m b) → m b
  (>>- [this f])

  ;; ifte :: m a → (a → m b) → m b → m b
  (-ifte [this then else])

  ;; once :: m a → m a
  (-once [this]))

(defmacro mdo*
  {:style/indent 1}
  [[binding expr] body]
  `(-bind ~expr (fn [~binding] ~body)))

(extend-protocol MonadPlus
  clojure.lang.ISeq
  (-mzero [this] ())
  (-mplus [this that] (concat this that))
  clojure.lang.IPersistentVector
  (-mzero [this] [])
  (-mplus [this that] (into this that)))

(comment
  (-mzero '(1 2 3))
  (-mplus '(1 2 3) '(4 5 6))
  (-mzero [1 2])
  (-mplus [1 2] [1 2]))

(extend-protocol Monad
  clojure.lang.ISeq
  (-bind [this f] (mapcat f this))
  (-return [_ x] (list x))
  clojure.lang.IPersistentVector
  (-bind [this f] (into [] (mapcat f) this))
  (-return [_ x] [x]))

(comment
  (-bind '(1 2 3) inc)
  (-return '(1 2 3) 1))

(extend-protocol MSplit
  clojure.lang.ISeq
  (-msplit [this]
    (-return this (when-let [xs (seq this)]
                    [(first xs) (rest xs)])))
  clojure.lang.IPersistentVector
  (-msplit [this]
    (-return this (when (seq this)
                    [(first this) (subvec this 1)]))))

(comment
  (-msplit '(1 2 3 4))
  (-msplit '[1 2 3 4])
  (-msplit '())
  (-msplit '(1)))

(extend-protocol ILogicM
  Object
  (-interleave [sg1 sg2]
    (mdo* [r (-msplit sg1)]
      (if-let [[sg11 sg12] r]
        (-mplus (-return sg1 sg11) (-interleave sg2 sg12))
        sg2)))
  (>>- [sg g]
    (mdo* [r (-msplit sg)]
      (if-let [[sg1 sg2] r]
        (-interleave (g sg1) (>>- sg2 g))
        (-mzero sg))))

  (-ifte [t th el]
    (mdo* [r (-msplit t)]
      (if-let [[sg1 sg2] r]
        (-mplus (th sg1) (-bind sg2 th))
        el)))
  (-once [m]
    (mdo* [r (-msplit m)]
      (if-let [[sg1] r]
        (-return m sg1)
        (-mzero m)))))

(-interleave '(1 2 3) '(4 5 6))
(-interleave '[1 2 3] '[4 5 6])

(>>- '(1 2 3) (fn [x] (list (inc x))))
