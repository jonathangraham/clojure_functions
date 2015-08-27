(ns clojure-functions.pmap-spec
  (:require [speclj.core :refer :all]
            [clojure-functions.pmap :refer :all]
            [clojure-functions.map :refer :all]))

(defn long-running-job 
	([n]
    	(Thread/sleep 1000)
    	(+ n 10))
	([n1 n2]
    	(Thread/sleep 1000)
    	(+ n1 n2 10)))

(defn realize-lazy-seq 
	([map-type f c]
		(loop [res (map-type f c)]
    		(when res
       			(recur (next res)))))
	([map-type f c1 c2]
		(loop [res (map-type f c1 c2)]
    		(when res
       			(recur (next res))))))

(defn test-time 
	([map-type f c]
		(let [st (System/nanoTime)]
			(realize-lazy-seq map-type f c)
			(/ (- (System/nanoTime) st) 1e9)))
	([map-type f c1 c2]
		(let [st (System/nanoTime)]
			(realize-lazy-seq map-type f c1 c2)
			(/ (- (System/nanoTime) st) 1e9))))





(describe "test my-pmap function"

	(it "result as map"
		(should= (map inc [1 2]) (my-pmap inc [1 2])))

	(it "test long-running-job function"
		(should= '(11 12) (my-pmap long-running-job [1 2])))

	(it "test long-running-job time"
		(should (< 1.0 (let [st (System/nanoTime)]
							(long-running-job 1)
							(/ (- (System/nanoTime) st) 1e9)))))

	(it "test time long-running job with map"
		(should (< 4.0 (test-time map long-running-job [1 2 3 4]))))

	(it "test time long-running job with my-pmap"
		(should (> 1.1 (test-time my-pmap long-running-job [1 2 3 4]))))

	(it "maps two vectors"
		(should= '(4 6) (my-pmap + [1 2] [3 4])))

	(it "test time long-running job with my-pmap and two collections"
		(should (> 1.1 (time (test-time my-pmap long-running-job [1 2 3 4][1 2 3 4]))))))
