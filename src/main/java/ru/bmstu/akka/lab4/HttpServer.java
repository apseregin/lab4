package ru.bmstu.akka.lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

public class HttpServer extends AllDirectives {
    ActorSystem system;
    ActorRef storageActor;
    public HttpServer(ActorSystem system) {
        this.system = system;

        storageActor = system.actorOf(Props.create(StorageActor.class));
    }
    public Route getRoute() {
        return concat(
                post( () -> {
                    storageActor.tell(new StorageActor.StoreMsg(123, "Test"), ActorRef.noSender());
                    
                }),
                get()
        );
    }
}
