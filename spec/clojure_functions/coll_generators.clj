(ns clojure-functions.coll-generators
  (:require [clojure.test.check.generators :as gen]))

(def colls
    (gen/one-of [
      (gen/vector gen/any) 
      (gen/list gen/any) 
      (gen/set gen/any) 
      (gen/map gen/any gen/any) 
      gen/bytes 
      gen/string]))

(def colls-more-one-element
    (gen/one-of [
      (gen/such-that #(< 1 (count %))(gen/vector gen/any)) 
      (gen/such-that #(< 1 (count %))(gen/list gen/any)) 
      (gen/such-that #(< 1 (count %))(gen/set gen/any)) 
      (gen/such-that #(< 1 (count %))(gen/map gen/any gen/any)) 
      (gen/such-that #(< 1 (count %))gen/bytes) 
      (gen/such-that #(< 1 (count %))gen/string)]))