(ns cliza.rules)

(defn eliza-rules []
  {"?*x hello ?*y"
   ["How do you do. Please state your problem."]

   "?*x I want ?*y"
   ["What would it mean if you got %y"
    "Why do you want %y ?"
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
   ["Do you often feel %y ?"]

   "?*x I felt ?*y"
   ["What other feelings do you have ?"]

   "?*x sorry ?*y"
   ["Please don't apologise."
    "Apologies are not necessary."]

   "?*x i remember ?*y"
   ["Do you often think of %y ?"
    "Does thinking of %y bring anything else to mind ?"
    "What else do you recollect ?"
    "Why do you recollect %y just now ?"
    "What in the present situation reminds you of %y ?"
    "What is the connection between me and %y ?"]

   "?*x do you remember ?*y"
   ["Did you think I would forget %y ?"
    "Why do you think I should recall %y now ?"
    "What about %y ?"
    "You mentioned %y ?"]

   "?*x i dreamt ?*y"
   ["Really, %y ?"
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
   ["Do you believe you are %y ?"
    "Would you want to be %y ?"
    "Do you wish I would tell you you are %y ?"
    "What would it mean if you were %y ?"]

   "?*x are you ?*y"
   ["Why are you interested in whether I am %y or not ?"
    "Would you prefer if I weren't %y ?"
    "Perhaps I am %y in your fantasies."
    "Do you sometimes think I am %y ?"]

   "?*x my mother ?*y"
   ["Who else in your family %y"
    "Tell me more about your family"]

   "?*x my father ?*y"
   ["Your father"
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
   })
