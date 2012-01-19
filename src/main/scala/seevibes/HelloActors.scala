package seevibes

import akka.actor.Actor

object HelloActors {

    case object SayHello

    class MyActor extends Actor {
        protected def receive = {
            case SayHello =>
                println("I was told to say \"Hello World!\"")
        }
    }

    def main(args: Array[String]) {
        val actor = Actor.actorOf(new MyActor).start()
        actor ! SayHello
        Thread.sleep(1000)
        Actor.registry.shutdownAll()
        Thread.sleep(100)
    }
}
