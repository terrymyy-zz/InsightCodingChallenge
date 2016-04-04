#!/usr/bin/env bash

# example of the run script for running the word count

# I'll execute my programs, with the input directory tweet_input and output the files in the directory tweet_output
cd ./src/
javac -cp . tweet/Tweet.java
javac -cp . tweet/CleanData.java
javac -cp . tweet/HashTagGraph.java
javac -cp . tweet/Window.java
javac -cp . tweet/Main.java
java  -cp . tweet/Main