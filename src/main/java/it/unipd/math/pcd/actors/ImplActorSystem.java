package it.unipd.math.pcd.actors;

/**
 * Created by mprelaz on 27/01/16.
 */
public class ImplActorSystem extends AbsActorSystem {

    private static ImplActorSystem ias;

    private ImplActorSystem(){
        super();
    }

    public static ImplActorSystem getInstance(){
        if(ias==null){
            ias = new ImplActorSystem();
        }
        return ias;
    }

    @Override
    protected ActorRef createActorReference(ActorMode mode) {
        if(mode.equals(ActorMode.LOCAL)) {
            return new ImplActorRef();
        }
        else{
            return new ImplActorRef(); //da modificare
        }
    }

}
