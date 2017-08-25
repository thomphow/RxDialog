# RxDialog
Please forgive me. Yet another RX front end to Android Dialogs.

[![Link to Demo Video](https://user-images.githubusercontent.com/5545791/29631006-2b8d0cfe-880c-11e7-8202-4b8498890ae7.jpg)](https://www.youtube.com/watch?v=qdYbmrjAeCY)

Some influences with respect to creating an RxDialog:

No man is an island (https://web.cs.dal.ca/~johnston/poetry/island.html)

Dan Lew: http://blog.danlew.net/ https://github.com/JakeWharton/RxBinding/issues/32

"To me, it seems like a fairly large task to make a proper Rx dialog library. There's a lot of depth to dialogs, if you're dealing with them in a general sense, since you can do just about anything with them - they're just floating windows.

I do so many crazy things with dialogs that I don't think a library could ever cover all the use cases. E.g., some dialogs have custom layouts inside of them which have lots of logic/control outside the bounds of any normal dialog listener

If I were to make a library I'd make it tightly focused on some very common use cases, such as displaying a title/message/buttons. Once you start expanding beyond that (into things like adapters and custom views) it gets a lot more complex."

I did look at a number of open source libraries but this RxDialog library is not a "derivative work" of the collective or any individual.

I was most tempted to use Xavier Leprêtre's rx-alert-dialog work (https://github.com/xavierlepretre/rx-alert-dialogbut) but my needs for a Custom View (yes, what Dan Lew said maybe shouldn't be such a thing) and thinking I could write something much simpler, lead me to what I have created. 

Kaushik Gopol, one of the two Fragments Podcast hosts, commented verbally that he too created his own Rx Dialog when he first started learning RX .... and reading between his words, that he considered it a mistake to have done.( https://kaush.co/ http://fragmentedpodcast.com/ )
While I don't consider what I have done a mistake, I do view it as possable a right of passage as people learn to use RX.

Howard

