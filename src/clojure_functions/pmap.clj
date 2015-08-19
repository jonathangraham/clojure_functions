(ns clojure-functions.pmap
	(:require [clojure-functions.map :refer :all]))

(defn long-running-job [n]
    (Thread/sleep 1000) ; wait for 3 seconds
    (+ n 10))

(defn my-pmap 
	([function coll1]
  		(let [results (my-map #(future (function %)) coll1)]
    	(my-map deref results)))
	([function coll1 coll2]
  		(let [results (my-map #(future (function %1%2)) coll1 coll2)]
    	(my-map deref results)))
    ([function coll1 coll2 & more]
    	(loop [input1 coll1 input2 coll2 remaining more]
    		(if (zero? (count remaining))
				(my-pmap function input1 input2)
				(recur (my-pmap function input1 input2) (first remaining) (rest remaining))))))


(defn run-myfunc [map_type & coll]
	(let [starttime (System/nanoTime)]
		(loop [remaining coll]
			(map_type long-running-job (first remaining))
			(if (= 1 (count remaining))
				(/ (- (System/nanoTime) starttime) 1e9)
				(recur (rest remaining))))))

