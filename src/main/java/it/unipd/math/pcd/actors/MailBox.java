package it.unipd.math.pcd.actors;

import java.util.LinkedList;


public final class MailBox<T extends Message> {

    private LinkedList<Mail<T>> mailbox=new LinkedList<>();

    public void add(Mail<T> m) {
        mailbox.addFirst(m);
    }

    public void remove() {
        mailbox.removeLast();
    }

    public int size() {
        return mailbox.size();
    }

    public T getMessage() {
        return mailbox.getLast().getMessage();
    }

    public ActorRef<T> getSender() {
        return mailbox.getLast().getSender();
    }

}
