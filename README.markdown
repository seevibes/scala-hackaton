Scala Hackaton Starter App
==========================

This skeleton application will get you started using Scala and Akka. Please run `mvn test` from a clone of this repository to get all dependencies locally.

The project was setup using IDEA with the Scala plugin. You can get a free evaluation version of IDEA at http://www.jetbrains.com/idea/download/index.html

Alternatively, run from the command line using Apache Maven:

    $ mvn test

Expect one failure, as I wanted to let you see the failed test.

The first time you run this command, Maven will download the world, but after that it'll be quick. Please make sure to run that before you get to the Notman House, or you may have to wait a long time to get everything ready.

Running from the command line
=============================

    $ mvn compile exec:java -Dexec.mainClass=seevibes.HelloWorld
    $ mvn compile exec:java -Dexec.mainClass=seevibes.HelloActors
    $ mvn compile exec:java -Dexec.mainClass=seevibes.HelloTwitter -Dexec.args="TWITTER_USERNAME TWITTER_PASSWORD KEYWORD1 KEYWORD2 KEYWORDn"
