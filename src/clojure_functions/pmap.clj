(ns clojure-functions.pmap
	(:require [clojure-functions.map :refer :all]))

(defn my-pmap
	([f coll]
		(let [results (map #(future (f %)) coll)]
			(map deref results)))
	([f c1 c2]
		(let [results (map #(future (f %1 %2)) c1 c2)]
			(map deref results))))