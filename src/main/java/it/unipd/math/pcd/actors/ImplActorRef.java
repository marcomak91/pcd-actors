package it.unipd.math.pcd.actors;

/**
 * Created by mprelaz on 27/01/16.
 */
public class ImplActorRef implements ActorRef {

    private final static ImplActorSystem ias = ImplActorSystem.getInstance();

    @Override
    public void send(Message message, ActorRef to) {
        Actor a = ias.get(to);
        a.receive(message);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
