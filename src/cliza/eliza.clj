(ns cliza.eliza)

(def eliza-rules
  {"?*x hello ?*y" ["How do you do. Please state your problem."]
   "?*x I want ?*y" ["What would it mean if you got %y"
                     "Why do you want %y ?"
                     "Suppose you got %y soon"]
   "?*x if ?*y" ["Do you really think its likely that %y"
                 "Do you wish that %y"
                 "What do you think about %y"
                 "Really -- if %y"]
   "?*x no ?*y" ["Why not?"
                 "You are being a bit negative"
                 "Are you saying NO just to be negative?"]
   "?*x I was ?*y" ["Were you really?"
                    "Perhaps I already knew you were %y"
                    "Why do you tell me you were %y now?"]
   "?*x I feel ?*y" ["Do you often feel %y ?"]
   "?*x I felt ?*y" ["What other feelings do you have ?"]
   "?*x sorry ?*y" ["Please don't apologise."
                    "Apologies are not necessary."]
   "?*x i remember ?*y" ["Do you often think of %y ?"
                         "Does thinking of %y bring anything else to mind ?"
                         "What else do you recollect ?"
                         "Why do you recollect %y just now ?"
                         "What in the present situation reminds you of %y ?"
                         "What is the connection between me and %y ?"]
   "?*x do you remember ?*y" ["Did you think I would forget %y ?"
                              "Why do you think I should recall %y now ?"
                              "What about %y ?"
                              "You mentioned %y ?"]
   "?*x i dreamed ?*y" ["Really, %y ?"
                        "Have you ever fantasized %y while you were awake ?"
                        "Have you ever dreamed %y before ?"]
   })

(def dflt-question-resps ["I have no idea."
                          "I wish I knew."
                          "I haven't got a clue."])

(def dflt-resps ["Very interesting"
                 "Now you're just talking nonsense!"
                 "Have some sense, man!"
                 "I am not sure I understand you fully"
                 "What does that suggest to you?"
                 "Please continue"
                 "Go on"
                 "Do you feel strongly about that?"
                 "I feel like I've heard that before"
                 "I don't know much about that."
                 "I'm no expert on that."
                 "I don't have much to say about that."
                 "Tell me more?"
                 "Yes .. and?"
                 "And then what?"
                 "Mmkay."
                 "What makes you say that?"
                 "Aaaaah."
                 "Sure."])

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

(defn process-input
  [inp]
  {:input inp
   :tokens (tokenize inp)
   :question? (.endsWith inp "?")})

(defn process-output
  [bindings ret]
  (reduce (fn [acc [k v]]
            (println "Fn: acc:" acc ":k:" k ":v:" v)
            (clojure.string/replace acc (re-pattern (str "%" k)) v))
          ret bindings))

(defn match-rule
  [inp-map [k v :as rule]]
  (let [result (pattern-match (tokenize k) (:tokens inp-map))]
    ;; result is bindings
    (when result
      (let [ret (rand-nth v)]
        (println "Bindings:" result)
        (println "Rand Ret:" ret)
        (println "k:" k ":v:" v)
        (process-output result ret)))))

;; transform synonyms before matching rules

(defn use-eliza-rules
  [inp]
  (let [inp-map (process-input inp)
        result (some (partial match-rule inp-map) eliza-rules)]
    (or result
        (if (:question? inp-map)
          (rand-nth dflt-question-resps)
          (rand-nth dflt-resps)))))

(defn chat-loop []
  (loop []
    (print "eliza> ")
    (flush)
    (when-let [input (not-empty (read-line))]
      (println (use-eliza-rules (clojure.string/trim input)))
      ;; we can check for "goodbye" like the original Eliza
      (recur))))
