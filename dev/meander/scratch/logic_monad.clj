;; https://okmij.org/ftp/Computation/LogicT.pdf
(ns logic-monad)

;; {:object _, :bindings {}, :seed _}
;; (bind [state f]) -> (let [[head tail] (msplit state)] (mplus (f head) (bind tail f)))
;; class Monad m ⇒ MonadPlus m where
;; mzero :: m a
;; mplus :: m a → m a → m a

(defprotocol MonadPlus
  :extend-via-metadata true
  (mzero [this])
  (mplus [this that]))

(defprotocol ILogicM
  :extend-via-metadata true
  ;; msplit :: m a → m (Maybe (a, m a))
  (msplit [this])

  ;; interleave :: m a → m a → m a
  (interleave [this that])

  ;; bind :: m a → (a → m b) → m b
  (bind [this f])

  ;; ifte :: m a → (a → m b) → m b → m b 
  (ifte [this then else])

  ;; once :: m a → m a
  (once [this]))

(defmacro ^{:style/indent 1} fnm
  [args expr]
  `(fn [~@args]
     (with-meta ~expr (meta ~(first args)))))

(let [mplus-impl {`mzero (fnm [this]
                           (empty this))
                  `mplus (fnm [this that]
                           (concat this that))}

      make-mplus (fn [& xs]
                   (with-meta xs mplus-impl))

      logicm-impl {`msplit (fnm [this]
                             (if-some [[head & tail] (seq this)]
                               (list [head tail])
                               ()))
                   `interleave (fnm [sg1 sg2]
                                 (mapcat
                                  (fn [r]
                                    (if-some [[sg11 sg12] (seq r)]
                                      (mplus (make-mplus sg11) (interleave sg2 sg12))
                                      sg2))
                                  (msplit sg1)))
                   `bind (fnm [sg f]
                           (let [zero (mzero sg)]
                             (mapcat
                              (fn [r]
                                (if-some [[sg1 sg2] (seq r)]
                                  (interleave (f sg1) (bind sg2 f))
                                  zero))
                              (msplit sg))))

                   `ifte (fn [this then else])
                   `once (fn [this])}

      state-impl {`geto :object
                  `seto (fnm [this object]
                          (assoc this :object object))}

      state (with-meta {:object 10} state-impl)

      m (with-meta (list state) (merge mplus-impl logicm-impl))]
  (msplit m))
