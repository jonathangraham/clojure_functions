(ns clojure-functions.coll-generators
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(def tuples
  (gen/bind (gen/vector gen/int)
    (fn [i]
      (apply gen/tuple (repeatedly (count i) #(gen/elements gen/any))))))

(def hashes
  (gen/bind (gen/vector gen/int)
    (fn [i]
      (apply gen/hash-map (repeatedly (* 2 (count i)) #(gen/elements gen/any))))))

(def colls
    (gen/one-of [
      (gen/vector gen/any) 
      (gen/list gen/any) 
      (gen/set gen/any) 
      (gen/map gen/keyword gen/any) 
      gen/bytes 
      gen/string
      tuples
      hashes]))

(def colls-more-one-element
    (gen/one-of
    [(gen/such-that #(< 1 (count %))(gen/vector gen/any)) 
    (gen/such-that #(< 1 (count %))(gen/list gen/any)) 
    (gen/such-that #(< 1 (count %))(gen/set gen/any)) 
    (gen/such-that #(< 1 (count %))(gen/map gen/keyword gen/any)) 
    (gen/such-that #(< 1 (count %))gen/bytes) 
    (gen/such-that #(< 1 (count %))gen/string)
    (gen/such-that #(< 1 (count %))tuples)
    (gen/such-that #(< 1 (count %))hashes)
    ]))