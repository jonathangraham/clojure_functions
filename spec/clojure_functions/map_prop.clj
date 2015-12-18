(ns clojure-functions.map-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.map :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))


(def my-map-property
    (prop/for-all [c colls c1 colls c2 colls c3 colls c4 colls]
        (is (= (map list c) (my-map list c)))
        (is (= (map list c c1) (my-map list c c1)))
        (is (= (map list c c1 c2) (my-map list c c1 c2)))
        (is (= (map list c c1 c2 c3 c4) (my-map list c c1 c2 c3 c4)))))

(defspec my-map-property-test 50 my-map-property)