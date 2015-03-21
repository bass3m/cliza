(ns cliza.eliza)

(def eliza-rules
  {"?*x hello ?*y" ["How do you do. Please state your problem."]
   "?*x I want ?*y" ["What would it mean if you got ?y"
                     "Why do you want ?y"
                     "Suppose you got ?y soon"]
   "?*x if ?*y" ["Do you really think its likely that ?y"
                 "Do you wish that ?y"
                 "What do you think about ?y"
                 "Really -- if ?y"]
   "?*x no ?*y" ["Why not?"
                 "You are being a bit negative"
                 "Are you saying NO just to be negative?"]
   "?*x I was ?*y" ["Were you really?"
                    "Perhaps I already knew you were ?y"
                    "Why do you tell me you were ?y now?"]
   "?*x I feel ?*y" ["Do you often feel ?y ?"]
   "?*x I felt ?*y" ["What other feelings do you have ?"]
   })

(defn tokenize [inp]
  (re-seq #"(?:[a-z0-9*?]|')+" (clojure.string/lower-case inp)))

(defn switch-viewpoint
  [inp]
  (replace {"i" "you"
            "you" "i"
            "me" "you"
            "my" "your"
            "are" "am"
            "am" "are"}
           (cond-> inp (string? inp) tokenize)))

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
         (.startsWith (java.lang.String. patt) "?*"))))

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
   (.indexOf (java.lang.String. st) sub start)))

(defn segment-match
  ([pattern inp bdings] (segment-match pattern inp bdings 0))
  ([pattern inp bdings start]
   ;;(println "Seg: pattern" pattern ":inp:" inp ":b:" bdings ":st:" start)
   (let [joined-inp (clojure.string/join " " inp)
         v (second (re-find #"^\?\*(.)$" (first pattern)))
         pat (rest pattern)]
     ;; (println "V:" v ":pat:" pat)
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

(defn use-eliza-rules
  [inp]
  (some (fn [[k v]]
          (let [result (pattern-match
                        (tokenize k)
                        (tokenize inp))]
          ;;  (println "Result:" result)
            (when result
              (rand-nth v)
              )))
        eliza-rules))
