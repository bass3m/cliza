(ns cliza.rules)

(defn eliza-rules []
  {"?*x hello ?*y"
   ["How do you do. Please state your problem."]

   "?*x I want ?*y"
   ["What would it mean if you got %y"
    "Why do you want %y?"
    "Suppose you got %y soon"]

   "?*x if ?*y"
   ["Do you really think its likely that %y"
    "Do you wish that %y"
    "What do you think about %y"
    "Really -- if %y"]

   "?*x no ?*y"
   ["Why not?"
    "You are being a bit negative"
    "Are you saying NO just to be negative?"]

   "?*x I was ?*y"
   ["Were you really?"
    "Perhaps I already knew you were %y"
    "Why do you tell me you were %y now?"]

   "?*x I feel ?*y"
   ["Do you often feel %y?"]

   "?*x I felt ?*y"
   ["What other feelings do you have ?"]

   "?*x sorry ?*y"
   ["Please don't apologise."
    "Apologies are not necessary."]

   "?*x i remember ?*y"
   ["Do you often think of %y?"
    "Does thinking of %y bring anything else to mind ?"
    "What else do you recollect ?"
    "Why do you recollect %y just now?"
    "What in the present situation reminds you of %y?"
    "What is the connection between me and %y?"]

   "?*x do you remember ?*y"
   ["Did you think I would forget %y?"
    "Why do you think I should recall %y now ?"
    "What about %y?"
    "You mentioned %y?"]

   "?*x i dreamt ?*y"
   ["Really, %y?"
    "Have you ever fantasized %y while you were awake ?"
    "Have you ever dreamed %y before ?"]

   "?*x dream ?*y"
   ["What does that dream suggest to you ?"
    "Do you dream often ?"
    "What persons appear in your dreams ?"
    "Do you believe that dreams have something to do with your problems ?"]
   ;; "?*x dream about ?*y"
   ;; ["How do you feel about %y in reality?"]

   "?*x perhaps ?*y"
   ["You don't seem quite certain."
    "Why the uncertain tone ?"
    "Can't you be more positive ?"
    "You aren't sure ?"
    "Don't you know ?"]

   "?*x computer ?*y"
   ["Do computers worry you ?"
    "Why do you mention computers ?"
    "What do you think machines have to do with your problem ?"
    "Don't you think computers can help people ?"
    "What about machines worrys you ?"
    "What do you think about machines ?"]

   "?*x name ?*y"
   ["I am not interested in names."]

   "?*x am i ?*y"
   ["Do you believe you are %y?"
    "Would you want to be %y?"
    "Do you wish I would tell you you are %y?"
    "What would it mean if you were %y?"]

   "?*x are you ?*y"
   ["Why are you interested in whether I am %y or not ?"
    "Would you prefer if I weren't %y?"
    "Perhaps I am %y in your fantasies."
    "Do you sometimes think I am %y?"]

   "?*x my mother ?*y"
   ["Who else in your family %y"
    "Tell me more about your family"]

   "?*x my father ?*y"
   ["Your father"
    "Does he influence you strongly?"
    "What else comes to mind when you think of your father?"]

   "?*x my family ?*y"
   ["Tell me more about your family"
    "Does he influence you strongly?"
    "What else comes to mind when you think of your father?"]

   "?*x i am glad ?*y"
   ["How have I helped you to be %y"
    "What makes you happy just now"
    "Can you explain why you are suddenly %y"]

   "?*x i am sad ?*y"
   ["I am sorry to hear you are depressed"
    "I'm sure it's not pleasant to be sad"]

   "?*x are like ?*y"
   ["What resemblance do you see between %x and %y"]

   "?*x is like ?*y"
   ["In what way is it that %x is like %y"
    "What resemblance do you see?"
    "How ?"
    "Could there really be some connection?"]

   "?*x alike ?*y"
   ["In what way?"
    "What similarities are there?"]

   "?*x same ?*y"
   ["What other connections do you see?"]

   "?*x i was ?*y"
   ["Were you really?"
    "Perhaps I already knew you were %y"
    "Why do you tell me you were %y now?"]

   "?*x was i ?*y"
   ["What if you were %y ?"
    "Do you think you were %y"
    "What would it mean if you were %y"]

   "?*x i am ?*y"
   ["In what way are you %y?"
    "Do you want to be %y?"]

   "?*x you are ?*y"
   ["What makes you think I am %y?"]

   "?*x because ?*y"
   ["Is that the real reason?"
    "What other reasons might there be?"
    "Does that reason seem to explain anything else?"]

   "?*x were you ?*y"
   ["Perhaps I was %y"
    "What do you think?"
    "What if I had been %y"]

   "?*x i can't ?*y"
   ["Maybe you could %y now"
    "What if you could %y?"]

   "?*x i ?*y you ?*z"
   ["Perhaps in your fantasy we %y each other"]

   "?*x why don't you ?*y"
   ["Should you %y yourself?"
    "Do you believe I don't %y"
    "Perhaps I will %y in good time"]

   "?*x yes ?*y"
   ["You seem quite positive"
    "You are sure"
    "I understand"]

   "?*x someone ?*y"
   ["Can you be more specific?"]

   "?*x everyone ?*y"
   ["surely not everyone"
    "Can you think of anyone in particular?"
    "Who for example?"
    "You are thinking of a special person"]

   "?*x always ?*y"
   ["Can you think of a specific example"
    "When?"
    "What incident are you thinking of?"
    "Really-- always"]

   "?*x what ?*y"
   ["Why do you ask?"
    "Does that question interest you?"
    "What is it you really want to know?"
    "What do you think?"
    "What comes to your mind when you ask that?"]

   "?*x my boss ?*y"
   ["Your boss?"
    "What about your boss?"
    "When did you last talk to your boss?"
    "If you could change anything about your boss, what would you change?"
    "What do you secretly believe about your boss?"]

   "?*x manager ?*y"
   ["Your manager?"
    "What about your manager?"
    "When did you last talk to your manager?"
    "If you could change anything about your manager what would you change?",
    "What do you secretly believe about your manager?"]
   })

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

(def dflt-question-resps ["I have no idea."
                          "I wish I knew."
                          "I haven't got a clue."])

(defn synonyms []
  {"can't" #{"cannot"}
   "glad" #{"elated" "better" "happy"}
   "feel" #{"think" "believe" "wish"}
   "want" #{"need" "desire"}
   "sad" #{"unhappy" "depressed" "sick"}
   "family" #{"family" "mother" "mom" "father" "dad"
              "sister" "brother" "wife" "children" "child"
              "uncle" "aunt" "grandma" "grandpa" "cousin"}})
