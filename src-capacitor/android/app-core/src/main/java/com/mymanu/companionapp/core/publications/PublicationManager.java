/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications;

import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;

/**
 * Provides methods to register Subscribers and Publishers in order to access the publication
 * system of the core module.
 * All subscribers and publishers are automatically released when the application terminates.
 */
public interface PublicationManager {

    /**
     * Use this method to subscribe for publications. Once subscribed, the subscriber will be
     * notified of any new publications mapping its
     * {@link Subscription Subscription}.
     *
     * @param subscriber
     *         The subscriber to register.
     */
    void subscribe(Subscriber subscriber);

    /**
     * Use this method to unsubscribe from publications. Once called the given subscriber will
     * not receive anymore publications. The subscriber can be registered again using
     * {@link #subscribe(Subscriber)}.
     *
     * @param subscriber The subscriber to remove.
     */
    void unsubscribe(Subscriber subscriber);

    /**
     * Use this method to add publisher to the system. On registration the manager automatically
     * subscribes all {@link Subscriber} that corresponds to the
     * {@link Subscription Subscription} of
     * the given publisher.
     *
     * @param publisher The publisher to register.
     */
    @SuppressWarnings("rawtypes")
    void register(Publisher publisher);

    /**
     * Use this method to remove a publisher from the system. Once called the publisher won't
     * have any subscribers attached to it anymore. The publisher can be registered again using
     * {@link #register(Publisher)}.
     *
     * @param publisher The publisher to unregister.
     */
    @SuppressWarnings("rawtypes")
    void unregister(Publisher publisher);

}
