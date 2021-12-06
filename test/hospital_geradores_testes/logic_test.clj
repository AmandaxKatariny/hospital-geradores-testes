(ns hospital-geradores-testes.logic_test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all]
            [hospital-geradores-testes.logic :refer :all]
            [hospital-geradores-testes.model :as h.model]
            [clojure.test.check.generators :as gen]
            [schema.core :as s]
            [clojure.test.check.generators :as gen]))
(s/set-fn-validation! true)

; são testes Escritos baseados em exemplos
(deftest cabe-na-fila?-test
  ; borda do zero
  (testing "Que cabe numa fila vazia"
    (is (cabe-na-fila? {:espera []}, :espera)))

  (testing "Que cabe pessoas em filas de tamanho até 4 inclusive"
    (doseq [filas (gen/sample (gen/vector gen/string-alphanumeric 0 4))]
      (is (cabe-na-fila? {:espera filas}, :espera))))

  ; borda do limite
  (testing "Que não cabe na fila quando a fila está cheia"
    (is (not (cabe-na-fila? {:espera [1 5 37 54 21]}, :espera))))

  ; one off da borda do limite pra cima
  (testing "Que não cabe na fila quando tem mais do que uma fila cheia"
    (is (not (cabe-na-fila? {:espera [1 2 3 4 5 6]}, :espera))))

  ; dentro das bordas
  (testing "Que cabe na fila quando tem gente mas não está cheia"
    (is (cabe-na-fila? {:espera [1 2 3 4]}, :espera))
    (is (cabe-na-fila? {:espera [1 2]}, :espera)))

  (testing "Que não cabe quando o departamento não existe"
    (is (not (cabe-na-fila? {:espera [1 2 3 4]}, :raio-x)))))
