(ns clojure-functions.filter-spec
  (:require [speclj.core :refer :all]
  			[clojure-functions.filter :refer :all]
  			[clojure-functions.count :refer :all]))

(describe "test filter function"

	(it "result empty lazy sequence when filtering for zero on an empty vector"
		(should= clojure.lang.LazySeq (class (my-filter zero? [])))
		(should= 0 (my-count (my-filter zero? [])))
		(should= clojure.lang.LazySeq (class (my-filter zero? '())))
		(should= clojure.lang.LazySeq (class (my-filter zero? "")))
		(should= clojure.lang.LazySeq (class (my-filter zero? {})))
		(should= clojure.lang.LazySeq (class (my-filter zero? #{}))))

	(it "result even numbers when filtering for even numbers"
		(should= '(0 2 4 6 8) (my-filter even? (range 10))))

	(it "filter strings"
		(should= '("a" "b" "n" "f" "q") (my-filter #(= 1 (my-count %)) ["a" "aa" "b" "n" "f" "lisp" "clojure" "q" ""])))

	(it "filter maps"
		(should= '([:c 101] [:d 102]) (my-filter #(> (second %) 100)
       {:a 1
        :b 2
        :c 101
        :d 102
        :e -1}))))



