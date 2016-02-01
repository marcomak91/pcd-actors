package it.unipd.math.pcd.actors;


public final class Mail<T extends Message> {

    private final T message;

    private final ActorRef<T> sender;

    public Mail(T message, ActorRef<T> sender) {
        this.message=message;
        this.sender=sender;
    }

    public T getMessage() {
        return message;
    }

    public ActorRef<T> getSender() {
        return sender;
    }

}