(ns clojure-functions.count-spec
  (:require [speclj.core :refer :all]
            [clojure-functions.count :refer :all]))

(describe "test count function"

	(it "result 0 for an empty list"
		(should= 0 (my-count '())))

	(it "result 0 for nil"
		(should= 0 (my-count nil)))

	(it "result 1 for a list of one item"
		(should= 1 (my-count '(1))))

	(it "result 2 for a list of two items"
		(should= 2 (my-count '(1 2))))

	(it "result 10 for a list of ten items"
		(should= 10 (my-count '(:a :b :c :d :e :f :g :h :i :j))))

	(it "result 0 for an empty vector"
		(should= 0 (my-count [])))

	(it "result 10 for a vector of ten elements"
		(should= 10 (my-count [1 2 [3 4] 5 6 7 8 9 10 11])))

	(it "result 0 for an empty string"
		(should= 0 (my-count "")))

	(it "result 10 for a ten character string"
		(should= 10 (my-count "helloworld")))

	(it "result 0 for an empty map"
		(should= 0 (my-count {})))

	(it "result 1 for a map with a single key value pair"
		(should= 1 (my-count {:a 1})))

	(it "result 5 for a map with five key value pairs"
		(should= 5 (my-count {:a 1, "b" [1 2], 3 4, :d 5, "e" {:s 10}}))))