(ns cliza.eliza
  (:require [cliza.rules :as r]))

(defn tokenize [inp]
  (re-seq #"(?:[a-z0-9]|\?.|\?\*.|')+" (clojure.string/lower-case inp)))

;; need a preprocess to convert i'm to i am etc..

(defn switch-viewpoint
  [inp]
  (clojure.string/join " " (replace {"i" "you"
                                     "i'm" "you are"
                                     "you" "me"
                                     "your" "my"
                                     "yourself" "myself"
                                     "yours" "mine"
                                     "were" "was"
                                     "me" "you"
                                     "my" "your"
                                     "myself" "yourself"
                                     "are" "am"
                                     "am" "are"}
                                    (cond-> inp (string? inp) tokenize))))

(defn variable?
  "Variables have form : ?X"
  [pattern]
  (and (= \? (first pattern)) (= (count pattern) 2)))

(defn segment?
  "Segment have form: ?*X"
  [pattern]
  (when-let [patt (first pattern)]
    (and (string? patt)
         (= (count patt) 3)
         (.startsWith patt "?*"))))

(defn variable-match
  [v inp bdings]
  (let [b (get bdings v)]
    (cond
      (nil? b) (assoc bdings v (clojure.string/trim inp))
      (= b inp) bdings
      :else nil)))

(declare segment-match)

(defn pattern-match
  ([pattern inp] (pattern-match pattern inp {}))
  ([pattern inp bdings]
   (cond
     (nil? bdings) nil
     (variable? pattern) (variable-match pattern inp bdings)
     (= pattern inp) bdings
     (segment? pattern) (segment-match pattern inp bdings)
     (and (seq? pattern) (seq? inp)) (pattern-match
                                      (rest pattern) (rest inp)
                                      (pattern-match
                                       (first pattern) (first inp) bdings))
     :else nil)))

(defn position
  ([st sub] (position st sub 0))
  ([st sub start]
   (.indexOf st sub start)))

(defn segment-match
  ([pattern inp bdings] (segment-match pattern inp bdings 0))
  ([pattern inp bdings start]
   (let [joined-inp (clojure.string/join " " inp)
         v (second (re-find #"^\?\*(.)$" (first pattern)))
         pat (rest pattern)]
     (if (empty? pat)
       (variable-match v joined-inp bdings)
       (let [pos (position joined-inp (first pat) start)]
         (if (neg? pos)
           nil
           (let [b2 (pattern-match
                     pat
                     (tokenize (subs joined-inp pos))
                     (variable-match v (subs joined-inp 0 pos) bdings))]
             (if (nil? b2)
               (segment-match pattern inp bdings (inc pos))
               b2))))))))

(defn replace-abbrevs
  [tokens]
  (replace {"dont" "don't"
            "cant" "can't"
            "recollect" "remember"
            "recall" "remember"
            "maybe" "perhaps"
            "certainly" "yes"
            "machine" "computer"
            "computers" "computer"}
           tokens))

(defn synonym-of
  "return synonym and original word as a vector"
  [word]
  (some (fn [[k v]] (when (contains? v word) [k word])) (r/synonyms)))

(defn process-input
  [inp]
  (let [tokens (tokenize inp)
        synms (map (fn [w] (synonym-of w)) tokens)]
    {:input inp
     :synonym (first (remove nil? synms))
     :tokens (map (fn [[s w] t] (or s t)) synms tokens)
     :question? (.endsWith inp "?")}))

(defn process-output
  [bindings ret]
  (let [ret-str (reduce (fn [acc [k v]]
                          (clojure.string/replace acc
                                                  (re-pattern (str "%" k))
                                                  (switch-viewpoint v)))
                        ret bindings)]
    ret-str))

(defn process-rule
  [bindings [k v :as rule]]
  (process-output bindings (rand-nth (:patterns v))))

(defn process-rule
  [bindings [k v :as rule]]
  (-> bindings
      (process-output (rand-nth (:patterns v)))
      (clojure.string/replace #"\s+" " ")))

(defn match-rule
  [inp-map [k v :as rule]]
  (pattern-match (tokenize k) (:tokens inp-map)))

(defn process-synonyms
  "the synonym is the second entry in the synonym entry of map
  marked by %SYN is patterns"
  [inp-map out-str]
  (clojure.string/replace out-str #"%SYN" (second (:synonym inp-map))))

(defn best-match
  [matches]
  (reduce
   (fn [acc x]
     (cond
       (> (:weight x) (:weight acc)) x
       (and (= (:weight x)(:weight acc))
            (> (-> x :bindings vals clojure.string/join count)
               (-> acc :bindings vals clojure.string/join count))) x
               :else acc))
   matches))

(defn use-eliza-rules
  [inp]
  (let [inp-map (process-input inp)
        result (for [r (r/eliza-rules)
                     :let [bindings (match-rule inp-map r)]
                     :when bindings]
                 {:bindings bindings
                  :weight (-> r second :weight)
                  :out-str (process-rule bindings r)})]
    (or (and (not-empty result) (->> result
                                     best-match
                                     :out-str
                                     (process-synonyms inp-map)))
        (if (:question? inp-map)
          (rand-nth r/dflt-question-resps)
          (rand-nth r/dflt-resps)))))

(defn chat-loop []
  (loop []
    (print "eliza> ")
    (flush)
    (when-let [input (not-empty (read-line))]
      (println (use-eliza-rules (clojure.string/trim input)))
      ;; we can check for "goodbye" like the original Eliza
      (recur))))
