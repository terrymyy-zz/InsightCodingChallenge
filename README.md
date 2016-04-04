# InsightCodingChallenge
This is a Java project created for Insight Coding Challenge, finished on 2016/04/04.
This project consists of 5 classes(.java):
* CleanData.java(which read in the data, clean and abstract tweets infomation, write tweets into clean form etc.)
* Tweet.java(which generate every tweets that contains date and hashtags, also methods like add new hashtags, toString, print etc.)
* HashTagGraph.java(which generate hashtag graph, methods like createGraph when new tweet come in, write graph.txt file, calculate average degree and write degree file etc.)
* Window.java(which creates a 60s window, methods: check current time with new-come tweet time, process new tweet, write average degree file etc.)
* Main.java(main logic of the whole process of reading in files, create tweets, hashtag graph and 60s window, finally write all the average degree into one output file)

Usage: simply run following code in Terminal:
bash run.sh

This code has pass coding challenge test.
