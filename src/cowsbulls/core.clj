(ns cowsbulls.core)

(def answer [1 4 2 7])

(defn respond-to-guess [guess]
  {:exist (count (filter (set answer) guess))
   :match (count (filter true? (map = guess answer)))})

(def attempts (atom {}))

(defn next-guess [response]
	[(rand-int 10) (rand-int 10) (rand-int 10) (rand-int 10)])

(defn game []
  (loop [guess [1 2 3 4]]
    (let [{:keys [exist match] :as response} (respond-to-guess guess)]
      (do 
        (println (str "Guess: " (apply str guess) ", Exists: " (response :exist) ", Match: " (response :match)))
	    (if (and (= exist 4) (= match 4))
	      :win
	      (do
          (swap! attempts assoc guess response)
;          (println (str @attempts))
          (recur (next-guess response))))))))
