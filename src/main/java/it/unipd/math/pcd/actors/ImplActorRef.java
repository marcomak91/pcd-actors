package it.unipd.math.pcd.actors;

/**
 * Created by mprelaz on 27/01/16.
 */
public class ImplActorRef<T extends Message> implements ActorRef<T> {

    private ImplActorSystem actorSystem;

    public ImplActorRef(ImplActorSystem actorSystem) {
        this.actorSystem=actorSystem;
    }

    @Override
    public void send(Message message, ActorRef to) {
        AbsActor a = (AbsActor) actorSystem.getActor(to);
        a.reciveMail(message, this);
    }

    @Override
    public int compareTo(ActorRef ar) {
        return ( this == ar ) ? 0 : -1;
    }

}
