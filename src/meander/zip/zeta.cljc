(ns meander.zip.zeta
  {:no-doc true}
  (:require [clojure.zip :as zip]))

(defn rightmost-loc [loc]
  (if (zip/end? loc)
    (let [{:keys [zip/branch? zip/children zip/make-node]} (meta loc)]
      (recur (zip/zipper branch? children make-node loc)))
    (if-some [next-loc (zip/next loc)]
      (if (zip/end? next-loc)
        loc
        (recur next-loc)))))

(defn top-down [f loc]
  (let [loc* (f loc)]
    (if-some [next-loc (zip/next loc*)]
      (if (zip/end? next-loc)
        loc*
        (recur f (f next-loc)))
      loc)))

(defn bottom-up [f loc]
  (loop [loc (rightmost-loc loc)]
    (if-some [prev-loc (zip/prev loc)]
      (recur (f prev-loc))
      loc)))

(defn down-until
  "Repeatedly calls `zip/down` on loc until (pred loc) is
  satisfied. Returns the loc if found and nil otherwise."
  [pred loc]
  (if (pred loc)
    loc
    (if-some [loc (zip/down loc)]
      (recur pred loc)
      nil)))

(defn up-until
  "Repeatedly calls `zip/up` on loc until (pred loc) is
  satisfied. Returns the loc if found and nil otherwise."
  [pred loc]
  (if (pred loc)
    loc
    (if-some [loc (zip/up loc)]
      (recur pred loc)
      nil)))

(defn prev-until
  "Repeatedly calls `zip/prev` on loc until (pred loc) is
  satisfied. Returns the loc if found and nil otherwise."
  [pred loc]
  (if (pred loc)
    loc
    (if-some [loc (zip/prev loc)]
      (recur pred loc)
      nil)))

(defn next-until
  "Repeatedly calls `zip/next` on loc until (pred loc) is
  satisfied. Returns the loc if found and nil otherwise."
  [pred loc]
  (if (pred loc)
    loc
    (if-some [loc (zip/next loc)]
      (recur pred loc)
      nil)))
