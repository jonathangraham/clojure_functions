(ns clojure-functions.filter-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.filter :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))


(defn bool-fn [b _] b)

(def my-filter-property
    (prop/for-all [c colls b gen/boolean]
        (is (= (filter #(bool-fn b %) c) (my-filter #(bool-fn b %) c)))))

(defspec my-filter-property-test 50 my-filter-property)