(ns clojure-functions.reduce-spec
  (:require [speclj.core :refer :all]
            [clojure-functions.reduce :refer :all]))

(describe "test my-reduce function"

	
	(it "result 1 for addition on list containing element 1"
		(should= 1 (my-reduce + [1]))
		(should= 1 (my-reduce + 1 [])))

	(it "result 3 for addition on list containing element 1 and 2"
		(should= 3 (my-reduce + [1 2]))
		(should= 4 (my-reduce + 1 [1 2])))

	(it "result 2 for multiplication on list containing element 1 and 2"
		(should= 2 (my-reduce * [1 2])))

	(it "result 6 for multiplication on list containing elements 1, 2 and 3"
		(should= 6 (my-reduce * [1 2 3])))

	(it "convert a vector to a set"
		(should= #{:a :b :c} (my-reduce conj #{} [:a :b :c])))

	(it "add one collection to another"
		(should= [1 2 3 4 5 6] (my-reduce conj [1 2 3][4 5 6])))

	(it "result 0 for addition on empty list"
		(should= 0 (my-reduce + [])))

	(it "result 0 for addition on empty list"
		(should= 1 (my-reduce * [])))
)

