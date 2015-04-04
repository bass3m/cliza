# cliza
Eliza (of PAIP fame) in Clojure

### What is Eliza ?
Eliza is a chatbot originally developed by MIT professor Joseph Weizenbaum in 1966.
Eliza emulates a Rogerian psychoanalyst. Rogerians are "nondirective", they try to let the patient reveal himself.

### How to use ?

You start using eliza by invoking the ```chat-loop``` function, you terminate by entering an empty line.
Here's an example of using eliza from the repl.

```
cliza.eliza=> (chat-loop)
eliza> can you help me ?
You believe I can help you don't you?
eliza> yes i do
That's quite interesting.
eliza> why is it interesting ?
I have no idea.
eliza> ok, let's start that again
Please continue
eliza> can you help me ?
You believe I can help you don't you?
eliza> yes i do
Can you elaborate on that?
eliza> i need help with my problem
What would it mean if you got help with your problem
eliza> it would mean the world to me
Mmkay.
eliza>
nil
cliza.eliza=>
```