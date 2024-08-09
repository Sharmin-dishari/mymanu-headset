/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.vendor.example.publications;

import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;

import androidx.annotation.NonNull;

/*
 * Example code for implementing a subscriber.
 */
public interface MySubscriber extends Subscriber {

    @NonNull
    @Override
    default Subscription getSubscription() {
        /*
         * Provide the ID Publishers will use to dispatch events
         * The ID must be unique to this mapping.
         */
        return MySubscriptions.MY_SUBSCRIPTION_1;
    }

    /*
     * Define the methods that are to be called for each publication defined in the corresponding
     *  publisher. In our example, the corresponding publisher is MyPublisher.
     */

    void somethingHappened();

    void somethingElseHappened();

}
