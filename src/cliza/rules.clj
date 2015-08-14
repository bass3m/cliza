(ns cliza.rules)

(defn eliza-rules []
  {"?*x hello ?*y"
   {:weight 0
    :patterns
    ["How do you do. Please state your problem."
     "how are you?"
		 "how's it going?"
     "how's it going eh?"
     "how goes it?"
     "what's up?"
     "what's new?"
     "how's everything?"
		 "how is everything?"
     "how do you do?"
		 "how's it hanging?"
     "que pasa?"
		 "how are you doing?"]}

   "?*x if ?*y"
   {:weight 3
    :patterns
    ["Do you really think its likely that %y"
     "Do you wish that %y"
     "What do you think about %y"
     "Really -- if %y"]
    }

   "?*x no ?*y"
   {:weight 0
    :patterns
    ["Why not?"
     "You are being a bit negative"
     "Are you saying NO just to be negative?"]
    }

   "?*x i want ?*y"
   {:weight 2
    :patterns
    ["What would it mean if you got %y"
     "Why do you want %y?"
     "Suppose you got %y soon"]}

   "?*x i feel ?*y"
   {:weight 2
    :patterns
    ["Do you often feel %y?"]}

   "?*x i felt ?*y"
   {:weight 2
    :patterns
    ["What other feelings do you have?"]}

   "?*x i remember ?*y"
   {:weight 5
    :patterns
    ["Do you often think of %y?"
     "Does thinking of %y bring anything else to mind?"
     "What else do you recollect?"
     "Why do you recollect %y just now?"
     "What in the present situation reminds you of %y?"
     "What is the connection between me and %y?"]}

   "?*x do you remember ?*y"
   {:weight 5
    :patterns
    ["Did you think I would forget %y?"
     "Why do you think I should recall %y now?"
     "What about %y?"
     "You mentioned %y?"]}

   "?*x you remember ?*y"
   {:weight 5
    :patterns
    ["How could I forget %y?"
     "What about %y should I remember?"]}

   "?*x i forget ?*y"
   {:weight 5
    :patterns
    ["Can you think of why you might forget %y?"
     "Why can't you remember %y?"
     "How often do you think of %y?"
     "Does it bother you to forget that ?"
     "Could it be a mental block ?"
     "Are you generally forgetful ?"
     "Do you think you are suppressing %y?"]}

   "?*x did you forget ?*y"
   {:weight 5
    :patterns
    ["Would it bother you if I forgot %y?"
     "Why should I recall %y just now ?"
     "Are you sure you told me ?"
     "Why do you ask ?"]}

   "?*x i dreamt ?*y"
   {:weight 4
    :patterns
    ["Really, %y?"
     "Have you ever fantasized %y while you were awake?"
     "Have you ever dreamed %y before ?"]}

   "?*x dream ?*y"
   {:weight 3
    :patterns
    ["What does that dream suggest to you ?"
     "Do you dream often ?"
     "What persons appear in your dreams ?"
     "Do you believe that dreams have something to do with your problems?"]}

   "?*x dream about ?*y"
   {:weight 3
    :patterns
    ["How do you feel about %y in reality?"]}

   "?*x sorry ?*y"
   {:weight 0
    :patterns
    ["Please don't apologise."
     "Apologies are not necessary."]}

   "?*x perhaps ?*y"
   {:weight 0
    :patterns
    ["You don't seem quite certain."
     "Why the uncertain tone ?"
     "Can't you be more positive?"
     "You aren't sure?"
     "Don't you know?"]}

   "?*x machine ?*y"
   {:weight 50
    :patterns
    ["Why do you mention machines?"
     "What do you think machines have to do with your problem?"
     "Don't you think machines can help people?"
     "What about machines worrys you?"
     "What do you think about machines?"]}

   "?*x computer ?*y"
   {:weight 50
    :patterns
    ["Do computers worry you?"
     "Why do you mention computers?"
     "What do you think machines have to do with your problem?"
     "Don't you think computers can help people?"
     "What about machines worrys you?"
     "What do you think about machines?"]}

   "?*x drugs ?*y"
   {:weight 50
    :patterns
    ["do you use drugs often?"
     "are you addicted to drugs?"
     "maybe you should try to quit using drugs."]}

   "?*x name ?*y"
   {:weight 15
    :patterns
    ["I am not interested in names."]}

   "?*x am i ?*y"
   {:weight 0
    :patterns
    ["Do you believe you are %y?"
     "Would you want to be %y?"
     "Do you wish I would tell you you are %y?"
     "What would it mean if you were %y?"]}

   "?*x are you ?*y"
   {:weight 0
    :patterns
    ["Why are you interested in whether I am %y or not?"
     "Would you prefer if I weren't %y?"
     "Perhaps I am %y in your fantasies."
     "Do you sometimes think I am %y?"]}

   "?*x my mother ?*y"
   {:weight 0
    :patterns
    ["Who else in your family %y"
     "Tell me more about your mother"]}

   "?*x my father ?*y"
   {:weight 0
    :patterns
    ["Your father"
     "Does he influence you strongly?"
     "What else comes to mind when you think of your father?"]}

   "?*x my ?*y family ?*z"
   {:weight 0
    :patterns
    ["Tell me more about your family."
     "Who else in your family %z?"
     "Your %SYN?"
     "What else comes to mind when you think of your %SYN?"]}

   "?*x i am glad ?*y"
   {:weight 3
    :patterns
    ["How have I helped you to be %SYN?"
     "What makes you happy just now?"
     "Can you explain why you are suddenly %SYN?"]}

   "?*x i am sad ?*y"
   {:weight 3
    :patterns
    ["I am sorry to hear you are depressed"
     "I'm sure it's not pleasant to be sad"]}

   "?*x are like ?*y"
   {:weight 0
    :patterns
    ["What resemblance do you see between %x and %y"]}

   "?*x is like ?*y"
   {:weight 0
    :patterns
    ["In what way is it that %x is like %y"
     "What resemblance do you see?"
     "How ?"
     "Could there really be some connection?"]}

   "?*x alike ?*y"
   {:weight 0
    :patterns
    ["In what way?"
     "What similarities are there?"]}

   "?*x same ?*y"
   {:weight 0
    :patterns
    ["What other connections do you see?"]}

   "?*x i was ?*y"
   {:weight 2
    :patterns
    ["Were you really?"
     "Perhaps I already knew you were %y"
     "Why do you tell me you were %y now?"]}

   "?*x was i ?*y"
   {:weight 0
    :patterns
    ["What if you were %y ?"
     "Do you think you were %y"
     "What would it mean if you were %y"]}

   "?*x i am ?*y"
   {:weight 2
    :patterns
    ["In what way are you %y?"
     "Do you want to be %y?"]}

   "?*x you are ?*y"
   {:weight 0
    :patterns
    ["What makes you think I am %y?"]}

   "?*x because ?*y"
   {:weight 0
    :patterns
    ["Is that the real reason?"
     "What other reasons might 0 there be?"
     "Does that reason seem to explain anything else?"]}

   "?*x can you ?*y"
   {:weight 0
    :patterns
    ["You believe I can %y don't you?"
     "You want me to be able to %y"
     "Perhaps you would like to be able to %y yourself."]}

   "?*x can i ?*y"
   {:weight 0
    :patterns
    ["Whether or not you can %y depends on you more than me."
     "Do you want to be able to %y?"
     "Perhaps you don't want to %y"]}

   "?*x were you ?*y"
   {:weight 0
    :patterns
    ["Perhaps I was %y"
     "What do you think?"
     "What if I had been %y"]}

   "?*x i can't ?*y"
   {:weight 3
    :patterns
    ["Maybe you could %y now"
     "How do you know you can't %y?"
     "Have you tried?"
     "Do you really want to be able to %y?"
     "What if you could %y?"]}

   "?*x i don't ?*y"
   {:weight 3
    :patterns
    ["Don't you really %y?"
     "Does that trouble you?"
     "Why don't you %y?"
     "Do you wish to be able to %y?"]}

   "?*x i ?*y you ?*z"
   {:weight 4
    :patterns
    ["Perhaps in your fantasy we %y each other"
     "Do you wish to %y me?"
     "You seem to need to %y me."
     "Do you %y anyone else?"]}

   "?*x i ?*y"
   {:weight 0
    :patterns
    ["You say %x you %y?"
     "Why do you say %x you %y?"
     "Do you say %x you %y for some special reason?"
     "Can you elaborate on that?"
     "That's quite interesting."]}

   "?*x why don't you ?*y"
   {:weight 0
    :patterns
    ["Should you %y yourself?"
     "Do you believe I don't %y"
     "Perhaps I will %y in good time"]}

   "?*x yes ?*y"
   {:weight 0
    :patterns
    ["You seem quite positive"
     "You are sure"
     "I understand"]}

   "?*x someone ?*y"
   {:weight 0
    :patterns
    ["Can you be more specific?"]}

   "?*x everyone ?*y"
   {:weight 2
    :patterns
    ["Can you think of anyone in particular?"
     "Realy, %SYN?"
     "Surely not %SYN"
     "Who, for example?"
     "Are you thinking of a very special person?"
     "Who, may I ask?"
     "Someone special perhaps?"
     "You have a particular person in mind, don't you?"
     "Who do you think you're talking about?"
     "You are thinking of a special person"]}

   "?*x always ?*y"
   {:weight 0
    :patterns
    ["Can you think of a specific example"
     "When?"
     "What incident are you thinking of?"
     "Really-- always"]}

   "?*x what ?*y"
   {:weight 0
    :patterns
    ["Why do you ask?"
     "Does that question interest you?"
     "What is it you really want to know?"
     "What do you think?"
     "Are such questions much on your mind?"
     "What answer would please you most?"
     "Have you asked such questions before?"
     "Have you asked anyone else?"
     "What comes to your mind when you ask that?"]}

   "?*x your ?*y"
   {:weight 0
    :patterns
    ["Really my %y?"
     "What about your own %y?"
     "Are you worried about someone else's %y?"
     "What makes you think of my %y?"
     "Do you want my %y?"]}

   "?*x sex ?*y"
   {:weight 10
    :patterns
    ["Please watch your tongue!"
     "Please avoid such unwholesome thoughts."
     "Please get your mind out of the gutter."
     "Such lewdness is not appreciated."]}

   "?*x swear ?*y"
   {:weight 10
    :patterns
    ["Does it make you feel strong to use that kind of language?"
     "Are you venting your feelings now?"
     "Are you angry?"
     "Does this topic make you feel angry?"
     "Is something making you feel angry?"
     "Does using that kind of language make you feel better?"]}

   "?*x my boss ?*y"
   {:weight 0
    :patterns
    ["Your boss?"
     "What about your boss?"
     "When did you last talk to your boss?"
     "If you could change anything about your boss, what would you change?"
     "What do you secretly believe about your boss?"]}

   "?*x manager ?*y"
   {:weight 0
    :patterns
    ["Your manager?"
     "What about your manager?"
     "When did you last talk to your manager?"
     "If you could change anything about your manager what would you change?",
     "What do you secretly believe about your manager?"]}
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
   "feel" #{"think" "believe" "wish" "belief"}
   "want" #{"need" "desire"}
   "computer" #{"mac" "linux" "pc" "osx" "ubuntu" "c#" "scala" "coffeescript"
                "fpga" "virtex" "asic" "lenovo" "c++" "lisp" "java" "centos" "fedora"
                "centos" "python" "perl" "clojure" "javascript" "prolog" "haskell"
                "archlinux" "erlang" "cobol" "julia" "fortran" "pascal" "purescript"
                "redhat" "lisp" "smalltalk" "nim" "numpy" "scipy" "reactjs" "angularjs"
                "meteor" "knockoutjs" "meteorjs" "postgres" "couchdb" "mongodb" "riak"
                "mariadb" "sqlite" "idris" "lua" "luajit" "rethinkdb" "datomic" "mysql"
                "ravendb" "typescript" "dart" "redis" "agda" "coq" "memcachd" "pouchdb"
                "backbonejs" "golang" "scheme" "ocaml" "sml" "f#" "rust" "aws" "azure" "heroku"
                "dynamodb" "iojs" "forth" "paxos" "hadoop" "etcd" "zookeeper" "jquery" "drupal"
                "solr" "lucene" "coreos" "cassandradb" "slackware" "json" "yaml" "xml" "bigml"
                "wordpress" "matlab" "mathematica" "octave" "mahout" "neo4j" "mesos" "dynamodb"
                "oozie" "elasticsearch" "karaf" "mesosphere" "unity" "ansible" "axure" "gradle"
                "docker"}
   "everyone" #{"noone" "everybody" "nobody"}
   "sad" #{"unhappy" "depressed" "sick" "miserable"}
   "swear" #{"fuck" "fucker" "shit" "damn" "bastard"}
   "sports" #{"atheletics" "baseball" "basketball" "football" "frisbee" "mlb"
              "gym" "gymnastics" "hockey" "lacrosse" "soccer" "softball" "mma"
              "sports" "swimming" "swim" "tennis" "volleyball" "nfl" "nba"
              "cricket" "rugby" "boxing" "wrestling" "foosball" "futball"
              "bowling" "badminton" "nhl" "skiing" "snowboarding"}
   "sex" #{"fucked" "screw" "screwing" "fucking" "rape" "raped"
           "kiss" "kissing" "kisses" "screws" "fucks"}
   "problems" #{"inhibitions" "hangups" "difficulties" "troubles"
                "anxieties" "frustrations"}
   "family" #{"family" "mother" "mom" "father" "dad" "niece"
              "sister" "brother" "wife" "children" "child" "nephew"
              "uncle" "aunt" "grandma" "grandpa" "cousin"}})
