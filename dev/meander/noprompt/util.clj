(ns meander.noprompt.util)

(defn clamp [value value-min value-max]
  (max value-min (min value value-max)))

;; SEE: https://www.sidefx.com/docs/houdini/expressions/fit01.html
(defn fit01
  "Return the number between new-min and new-max relative to f in
  between 0 and 1. If the f is outside the 0 to 1 range it will be
  clamped."
  [f new-min new-max]
  (+ new-min (* (clamp f 0 1) (-' new-max new-min))))
