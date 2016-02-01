package it.unipd.math.pcd.actors;

import it.unipd.math.pcd.actors.exceptions.NoSuchActorException;


public class ImplActorSystem extends AbsActorSystem {

    @Override
    public void stop(ActorRef<?> actor) {
        if (this.getMap().containsKey(actor)) {
            AbsActor a=(AbsActor) getActor(actor);
            a.interrupt();
            this.getMap().remove(actor);
        }
        else {
            throw new NoSuchActorException();
        }
    }

    @Override
    public void stop() {
        for (ActorRef<?> actor : this.getMap().keySet()) {
            this.stop(actor);
        }
    }

    @Override
    protected ActorRef createActorReference(ActorMode mode) {
        if(mode.equals(ActorMode.LOCAL)) {
            return new ImplActorRef(this);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

}
