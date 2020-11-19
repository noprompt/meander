(ns meander.defsyntax-test.gh-145
  (:require [meander.epsilon :as m :include-macros true]))

(m/defsyntax gen-map
  "Shorthand for the map spread syntax

   (gen-map !k !v) would expand to:

   {& ([!k !v] ...)}

   (me/rewrite [1 2 3 4]
   [!xs !ys ...]
   (gen-map !xs !ys))
   ;; => {1 2, 3 4}"
  [!k !v]
  `{~'& ([~!k ~!v] ~'...)})
