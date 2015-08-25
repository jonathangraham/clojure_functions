(ns clojure-functions.reduce-spec
  (:require [speclj.core :refer :all]
            [clojure-functions.reduce :refer :all]))

(describe "test my-reduce function"

	(it "result 1 for addition function, with val of 1 and empty collection"
		(should= 1 (my-reduce + 1 '())))

	(it "result 1 for addition function, with val of 1 and empty collection"
		(should= 1 (my-reduce + 1 '())))

	(it "result 2 for addition function, with val of 1 and collection containing element 1"
		(should= 2 (my-reduce + 1 '(1))))

	(it "result 6 for addition function, with val of 1 and collection containing elements 2 and 3"
		(should= 6 (my-reduce + 1 [2 3])))

	(it "convert a vector to a set"
		(should= #{:a :b :c} (my-reduce conj #{} [:a :b :c])))

	(it "convert tuples to a map"
		(should= {:b 2, :a 1} (my-reduce conj {} [[:a 1] [:b 2]])))

	(it "add one collection to another"
		(should= [1 2 3 4 5 6] (my-reduce conj [1 2 3][4 5 6])))

	(it "combine sequences with cons"
		(should= '(6 5 4 1 2 3) (my-reduce #(cons %2 %1) [1 2 3] [4 5 6])))
	
	(it "result 1 for addition function with no val and list containing element 1"
		(should= 1 (my-reduce + [1])))

	(it "result 6 for multiplication on collection containing elements 1, 2 and 3"
		(should= 6 (my-reduce * [1 2 3])))

	(it "Combine a vector of collections into a single collection of the type of the first collection in the vector"
		(should= [1 2 3 :a :b :c [4 5] 6] (my-reduce into [[1 2 3] [:a :b :c] '([4 5] 6)])))

	(it "result 0 for addition on empty list"
		(should= 0 (my-reduce + [])))

	(it "result 1 for multiplication on empty list"
		(should= 1 (my-reduce * []))))