/**
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2015 Riccardo Cardin
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p/>
 * Please, insert description here.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */

/**
 * Please, insert description here.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
package it.unipd.math.pcd.actors;

/**
 * Defines common properties of all actors.
 *
 * @author Riccardo Cardin
 * @version 1.0
 * @since 1.0
 */
public abstract class AbsActor<T extends Message> implements Actor<T> {

    protected MailBox<T> mailBox=new MailBox<>();

    protected boolean isInterrupted=false;

    protected Thread mailboxManager = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!isInterrupted) {
                synchronized (mailBox) {
                    while (mailBox.size() == 0) {
                        try {
                            mailBox.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sender = mailBox.getSender();
                    T msg = mailBox.getMessage();
                    mailBox.remove();
                    receive(msg);
                }
            }
            synchronized (mailBox) {
                while(mailBox.size() != 0){
                    sender = mailBox.getSender();
                    T msg = mailBox.getMessage();
                    mailBox.remove();
                    receive(msg);
                }
            }
        }
    });

    /**
     * Self-reference of the actor
     */
    protected ActorRef<T> self;

    /**
     * Sender of the current message
     */
    protected ActorRef<T> sender;

    public AbsActor() {
        mailboxManager.start();
    }

    /**
     * Sets the self-referece.
     *
     * @param self The reference to itself
     * @return The actor.
     */
    protected final Actor<T> setSelf(ActorRef<T> self) {
        this.self = self;
        return this;
    }

    protected void interrupt() {
        isInterrupted=true;
    }

    protected final void reciveMail(T message, ActorRef<T> sender) {
        synchronized(mailBox) {
            if (!isInterrupted) {
                Mail<T> m=new Mail<>(message, sender);
                mailBox.add(m);
                mailBox.notifyAll();
            }
        }
    }


}
