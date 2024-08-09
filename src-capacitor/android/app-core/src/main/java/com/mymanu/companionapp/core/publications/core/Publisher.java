/*
 * ************************************************************************************************
 * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.          *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.core;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import com.mymanu.companionapp.core.GaiaClientService;
import com.mymanu.companionapp.core.publications.PublicationManager;

/**
 * <p>Publishers are used to notify {@link Subscriber}. They publish information to their
 * corresponding subscribers.</p>
 * <p>A publisher must be registered using
 * {@link PublicationManager#register(Publisher)}
 * to get subscribers. The subscribers are matched to a Publisher if they have the same
 * {@link Subscription}.</p>
 * <p>Subscriptions must be unique for the matching to work.</p>
 */
public abstract class Publisher<S extends Subscriber> {

    /**
     * The list of subscribers that are registered for this subscription.
     */
    private final List<S> mSubscribers = new CopyOnWriteArrayList<>();

    /**
     * <p>To get the unique {@link Subscription} that corresponds to this publisher.</p>
     */
    public abstract Subscription getSubscription();

    /**
     * <p>This is used by the
     * {@link PublicationManager} to add
     * corresponding subscribers to this publisher.</p>
     * <p>Once subscribed, the subscriber will be notified of any new publications this
     * publisher does.</p>
     *
     * @param subscriber
     *         The subscriber to register.
     */
    public void subscribe(S subscriber) {
        if (!mSubscribers.contains(subscriber)) {
            mSubscribers.add(subscriber);
        }
    }

    /**
     * <p>This is used by the
     * {@link PublicationManager} to remove
     * subscribers from this publisher.</p>
     * <p>Once called the subscriber will not receive anymore publications. The subscriber can be
     * registered again using
     * {@link PublicationManager#subscribe(Subscriber)}.</p>
     *
     * @param subscriber
     *         The subscriber to remove.
     */
    public void unsubscribe(S subscriber) {
        mSubscribers.remove(subscriber);
    }

    /**
     * This clear the list of subscribers that have registered for publications from this publisher.
     */
    public void releaseSubscribers() {
        mSubscribers.clear();
    }

    /**
     * Called by children to publish information to the registered publishers.
     *
     * @param action
     *         An expected action that calls the subscriber.
     */
    protected void forEachSubscriber(Consumer<S> action) {
        mSubscribers.forEach(subscriber -> publish(subscriber, () -> action.accept(subscriber)));
    }

    /**
     * Run the publication to the subscriber on the execution thread of the subscriber.
     */
    private void publish(S subscriber, Runnable publication) {
        ExecutionType executionType = subscriber.getExecutionType();
        if (executionType == ExecutionType.UI_THREAD) {
            GaiaClientService.getTaskManager().runOnMain(publication);
        }
        else if (executionType == ExecutionType.BACKGROUND) {
            GaiaClientService.getTaskManager().runInBackground(publication);
        }
        else {
            // unexpected
            publication.run();
        }
    }
}
