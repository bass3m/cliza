(ns cliza.eliza
  (:require [cliza.rules :as r]))

(defn tokenize [inp]
  (re-seq #"(?:[a-z0-9]|\?.|\?\*.|')+" (clojure.string/lower-case inp)))

(defn switch-viewpoint
  [inp]
  (clojure.string/join " " (replace {"i" "you"
                                     "you" "i"
                                     "me" "you"
                                     "my" "your"
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

(defn synonym-of
  [word]
  (some (fn [[k v]] (when (contains? v word) k)) (r/synonyms)))

;; probably use something like the reduce below for abbrev and synonyms
(defn process-input
  [inp]
  {:input inp
   :tokens (map (fn [w] (or (synonym-of w) w)) (tokenize inp))
   :question? (.endsWith inp "?")})

(defn process-output
  [bindings ret]
  (reduce (fn [acc [k v]]
            (println "Fn: acc:" acc ":k:" k ":v:" v)
            (clojure.string/replace acc
                                    (re-pattern (str "%" k))
                                    (switch-viewpoint v)))
          ret bindings))

(defn match-rule
  [inp-map [k v :as rule]]
  (let [result (pattern-match (tokenize k) (:tokens inp-map))]
    ;; result is bindings
    (when result
      (let [ret (rand-nth v)]
        (println "Bindings:" result)
        (process-output result ret)))))

;; transform synonyms before matching rules

(defn use-eliza-rules
  [inp]
  (let [inp-map (process-input inp)
        result (some (partial match-rule inp-map) (r/eliza-rules))]
    (println "inp map:" inp-map)
    (or result
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
