(ns hospital-geradores-testes.core
  (:require [clojure.test.check.generators :as gen]))

;(gen/boolean)
; usando vírgula somenta para deixar claro a QUANTIDADE DE SAMPLES
(println (gen/sample gen/boolean, 100))
(println (gen/sample gen/int, 100))
(println (gen/sample gen/string))
(println (gen/sample gen/string-alphanumeric, 100))

(println (gen/sample (gen/vector gen/int 15), 5))