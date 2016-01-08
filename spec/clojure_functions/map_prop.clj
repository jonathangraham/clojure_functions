(ns clojure-functions.map-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.map :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(defspec my-map-property-test 20
    (prop/for-all [cs (gen/not-empty (gen/vector colls))]
        (= (apply map list (concat cs)) (apply my-map list (concat cs)))))