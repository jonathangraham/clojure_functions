(ns clojure-functions.map-spec
  (:require [speclj.core :refer :all]
            [clojure-functions.map :refer :all]
            [clojure-functions.count :refer :all]))

(describe "test my-map function"

	(it "result empty lazy sequence when mapping with zero? on an empty vector"
		(should= clojure.lang.LazySeq (class (my-map zero? [])))
		(should= 0 (my-count (my-map zero? []))))

	(it "result 1 for my-map with identity on vector of 1"
		(should= '(1) (my-map identity [1])))

	(it "maps across a range"
		(should= '(1 2 3 4 5 6 7 8 9 10) (my-map inc (range 0 10))))

	(it "maps with strings"
		(should= '("Hi A!" "Hi B!" "Hi C!") (my-map #(str "Hi " % "!" ) ["A" "B" "C"])))

	(it "maps with hash-maps"
		(should= '("three" "two") (filter identity (my-map {2 "two" 3 "three"} [5 3 2]))))

	(it "maps two vectors"
		(should= '(4 6) (my-map + [1 2] [3 4])))

	(it "stops when one list completed"
		(should= '(6 16) (my-map #(* 2 %1 %2) [1 2] [3 4 5 6])))

	(it "maps three lists"
		(should= '(9 12) (my-map + '(1 2) '(3 4) '(5 6))))

	(it "maps four vectors"
		(should= '(16 20) (my-map + [1 2] [3 4] [5 6] [7 8])))

	(it "maps with non-commutative functions"
		(should= '([:a :d :g] [:b :e :h] [:c :f :i]) 
			(apply my-map vector [[:a :b :c][:d :e :f][:g :h :i]]))))