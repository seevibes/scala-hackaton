package seevibes

import twitter4j.conf.ConfigurationBuilder
import akka.actor.Actors
import twitter4j._

object HelloTwitter {

    class StreamListener
            extends StatusListener
            with RateLimitStatusListener
            with ConnectionLifeCycleListener {
        def onRateLimitStatus(event: RateLimitStatusEvent) {
            println("Rate Limit Status: " + event)
        }

        def onRateLimitReached(event: RateLimitStatusEvent) {
            println("Rate Limit Reached: " + event)
        }

        def onStatus(status: Status) {
            println("@" + status.getUser.getScreenName + " : " + status.getText)
        }

        def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}

        def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}

        def onScrubGeo(userId: Long, upToStatusId: Long) {}

        def onException(ex: Exception) {
            println(ex)
        }

        def onConnect() {
            println("Connected")
        }

        def onDisconnect() {
            println("Disconnected")
        }

        def onCleanUp() {
            println("Clean up")
        }
    }

    private def setupListeners(stream: TwitterStream) {
        val listener = new StreamListener
        stream.addListener(listener)
        stream.addRateLimitStatusListener(listener)
        stream.addConnectionLifeCycleListener(listener)
    }

    def main(args: Array[String]) {
        if (args.length < 3) {
            println("USAGE: scala seevibes.HelloWorldTest TWITTER_USERNAME TWITTER_PASSWORD KEYWORD1 [KEYWORD2 [KEYWORDn...]]")
            println("Received arguments are: \"" + args.mkString("\", \"") + "\"")
            System.exit(1)
        }

        val stream = buildStream(args(0), args(1))
        setupListeners(stream)

        val query = new FilterQuery
        query.setIncludeEntities(true)
        query.track(args.drop(2))
        stream.filter(query)

        Thread.sleep(10000)
        Actors.registry().shutdownAll()
        Thread.sleep(100)
    }

    private def buildStream(username: String, password: String) = {
        val cb = new ConfigurationBuilder
        cb.setUser(username)
                .setPassword(password)
                .setIncludeEntitiesEnabled(true)
                .setDebugEnabled(true)

        new TwitterStreamFactory(cb build).getInstance()
    }
}
