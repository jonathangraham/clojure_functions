(ns clojure-functions.map-spec
  (:require [speclj.core :refer :all]
            [clojure-functions.map :refer :all]))

(describe "test my-map function"

	(it "result empty list for my-map with identity on empty vector"
		(should= '() (my-map identity [])))

	(it "result 1 for my-map with identity on vector of 1"
		(should= '(1) (my-map identity [1])))

	(it "result 2 for my-map with inc on vector of 1"
		(should= '(2) (my-map inc [1])))

	(it "maps across a range"
		(should= '(1 2 3 4 5 6 7 8 9 10) (my-map inc (range 0 10))))

	(it "result 3 for my-map with + 2 on vector of 1"
		(should= '(3) (my-map #(+ 2 %) [1])))

	(it "result 3 for my-map with + 2 on vector [1 2]"
		(should= '(3 4) (my-map #(+ 2 %) [1 2])))

	(it "result 3 for my-map with + 2 on list (1 2)"
		(should= '(3 4) (my-map #(+ 2 %) '(1 2))))

	(it "maps two vectors"
		(should= '(4 6) (my-map + [1 2] [3 4])))

	(it "maps two vectors anonymous function"
		(should= '(6 16) (my-map #(* 2 %1 %2) [1 2] [3 4])))

	(it "stops when one list completed"
		(should= '(6 16) (my-map #(* 2 %1 %2) [1 2] [3 4 5 6])))

	(it "maps three vectors"
		(should= '(9 12) (my-map + [1 2] [3 4] [5 6])))

	(it "maps four vectors"
		(should= '(16 20) (my-map + [1 2] [3 4] [5 6] [7 8])))

	(it "maps with strings"
		(should= '("Hello Ford!" "Hello Arthur!" "Hello Tricia!") (my-map #(str "Hello " % "!" ) ["Ford" "Arthur" "Tricia"]))))

; (describe "test my-map function on advance features"

; 	(it "maps with vector function"
; 		(should= '([:a :d :g] [:b :e :h] [:c :f :i]) (apply my-map vector [[:a :b :c]
;                    [:d :e :f]
;                    [:g :h :i]])))

; 	(it "maps with hash-maps"
; 		(should= ("three" "two") (filter identity (my-map {2 "two" 3 "three"} [3 2])))))

