# DeliverySystem-PAO
Delivery System, homework for Advanced Object Oriented Programing

## Stage 1
My original assignment was to make a food delivery system, but I thought I wouldn't be able to get to 8 classes with a food delivery, so I made a universal delivery system kinda thing. (Like glovo, foodpanda, etc)

The menu is pretty self-explanatory.
For choosing actions / commands, you have to manually type out each command. It ignores case-sensitivity, for user convenience. Fully typing out commands however, doesn't seem like a good idea for user convenience. But I wanted to do it this way to play around with some java methods and syntax.

In the main method, there's 3 establishments I have hard-coded. This definitely isn't the best way to do it, the best would be reading each establishment from corresponding input files, but for demo purposes, I feel like this is enough.

Another thing that is placeholder are the toString() formatting for each class. It looks clunky and very hard to read, I will be changing that for the next stage.
There's also some comments along the way with the code. The whole class structure could be a little bit better, but overall, I like how it's all turning out so far.

I haven't extensively tested it, because I've basically speedcoded it in a day, I hope there won't be any surprises. But if there are, let me know, I'd appreciate it!

## Stage 2
I realized there was a bug in my code when using the send order request. I actually fixed it and now I forgot exactly what it was, sometimes it would send the list empty or something like that.

The menu remains unchanged but now it logs every action taken (except for exit).

Now we have new methods in the Service class that read data from files from .csv files. We use them in the main method to read establishments and the delivery companies. I think everything should work nicely, it does for me on Linux Mint 20.1 (it should work on Windows as well, pretty sure). I also realized that I never call my `DeliveryCompany.deliverOrder()` method, which should _effectively_ deliver the order, as in, it _removes the item ordered_ from the respective Establishment that had it. I will be calling it in `Serviciu.sendOrder()` method, although I feel like it should be called elsewhere.

I also don't use the drivers for anything so far and I don't have a user data base with proper login / register. Probably will do it for Stage 3. I think I might leave drivers as an extra, although at first I thought they could be assigned to orders. I mean, they still can, but I might have to refactor some code, and that wasn't part of the task in stage 2, so it's fine.

I hope reading from csv files and writing to a log is enough for this stage. I also could _write_ classes to csv files, but I don't see the need to in my project. There could be a use for adding new products I guess, but would be weird to add products in the menu as a _user_. The menu is for the _user_.

Ah and I've created a class called Pair because the version of Java I used to run it doesn't have Pair. It's in the `utility` package.
