(ns clojure-functions.map-prop
  (:require [clojure-functions.map :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(def colls
    (gen/one-of 
    [(gen/vector gen/any) 
    (gen/list gen/any) 
    (gen/set gen/any) 
    (gen/map gen/keyword gen/any) 
    gen/bytes 
    gen/string
    (gen/tuple gen/any gen/any)
    (gen/tuple gen/any gen/any gen/any)
    (gen/hash-map gen/keyword gen/any gen/keyword gen/any)]))

(def my-map-property
    (prop/for-all [c colls c1 colls c2 colls c3 colls c4 colls]
        (is (= (map list c) (my-map list c)))
        (is (= (map list c c1) (my-map list c c1)))
        (is (= (map list c c1 c2) (my-map list c c1 c2)))
        (is (= (map list c c1 c2 c3 c4) (my-map list c c1 c2 c3 c4)))))

(defspec my-map-property-test 50 my-map-property)