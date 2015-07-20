(ns clojure-functions.map)

(defn my-map
	([function coll]
		(loop [input coll output []]
		(if (empty? input)
			output
			(recur (rest input) (conj output (function (first input)))))))
	([function coll1 coll2]
		(loop [input1 coll1 input2 coll2 output []]
		(if (empty? (or input1 input2))
			output
			(recur (rest input1) (rest input2) (conj output (function (first input1) (first input2)))))))
	([function coll1 coll2 & more]
		(loop [input1 coll1 input2 coll2 remaining more]
			(if (zero? (count remaining))
				(my-map function input1 input2)
				(recur (my-map function input1 input2) (first remaining) (rest remaining))))))