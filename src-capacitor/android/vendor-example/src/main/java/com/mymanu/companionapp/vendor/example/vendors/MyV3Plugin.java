/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.vendor.example.vendors;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.core.GaiaPacket;
import com.mymanu.companionapp.core.gaia.core.sending.GaiaSender;
import com.mymanu.companionapp.core.gaia.core.v3.V3Plugin;
import com.mymanu.companionapp.core.gaia.core.v3.packets.CommandPacket;
import com.mymanu.companionapp.core.gaia.core.v3.packets.ErrorPacket;
import com.mymanu.companionapp.core.gaia.core.v3.packets.NotificationPacket;
import com.mymanu.companionapp.core.gaia.core.v3.packets.ResponsePacket;
import com.mymanu.companionapp.core.gaia.core.v3.packets.V3Packet;
import com.mymanu.companionapp.core.gaia.core.v3.packets.V3PacketFactory;
import com.mymanu.companionapp.core.publications.PublicationManager;
import com.mymanu.companionapp.vendor.example.publications.MyPublisher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
 * Such as on the device the implementation of features are called plugins. In the core library
 * only the plugins that corresponds to the supported libraries are loaded.
 * This is an example of implementation of a V3 Plugin for a V3 Vendor.
 */
public class MyV3Plugin extends V3Plugin {

    /*
     * The implementation can use the core publication system to notify the application.
     * This vendor uses an example publisher to demonstrate the system.
     */
    private final MyPublisher publisher = new MyPublisher();

    /*
     * Constructor needs to call super(int vendorId, int featureId, @NonNull GaiaSender sender).
     */
    MyV3Plugin(int vendor, int feature, @NonNull GaiaSender sender,
               PublicationManager publicationManager) {
        super(vendor, feature, sender);

        // as an example, the vendor can register its publisher in its constructor
        // this a required step for corresponding subscribers to be notified
        publicationManager.register(publisher);
    }

    @Override
    protected void onStarted() {
        /*
         * Anything that needs to be done when the plugin starts.
         * A plugin is started by its vendor.
         */
    }

    @Override
    protected void onStopped() {
        /*
         * Anything that needs to be done when the plugin stops.
         * A plugin is stopped by its vendor or when the application terminates.
         */
    }

    @Override
    protected void onNotification(NotificationPacket packet) {
        // When a plugin receives a V3 packet of type notification it calls this method
        // this can be implemented by looking at the command ID of the packet
        switch (packet.getCommand()) {

        }
    }

    @Override
    protected void onResponse(ResponsePacket response, @Nullable CommandPacket sent) {
        // When a plugin receives a V3 packet of type response it calls this method
        // this can be implemented by looking at the command ID of the packet

        switch (response.getCommand()) {
            case MyVendorIDs.MY_V3_COMMAND_ID:
                // response to the command sent with sendSomething
                publisher.publishSomething();
                break;
        }
    }

    @Override
    protected void onError(ErrorPacket errorPacket, @Nullable CommandPacket sent) {
        // When a plugin receives a V3 packet of type response it calls this method
        // this can be implemented by looking at the command ID of the packet and the error status

        switch (errorPacket.getV3ErrorStatus()) {
            /*
             * Status are defined as either framework ones or feature specific.
             */

            case FEATURE_SPECIFIC:
                /*
                 * In the case of a feature specific, the default status is set to
                 * "FEATURE_SPECIFIC"
                 *  and the byte value of the error is accessible using #getStatusValue()
                 */
                int error = errorPacket.getStatusValue();
                // do something with error
                break;
        }
    }

    @Override
    protected void onFailed(GaiaPacket packet, Reason reason) {
        // this is called when a packet could not be sent to the device.

    }

    public void sendSomething() {
        // only commands can be sent from a mobile application
        V3Packet packet = V3PacketFactory.buildCommandPacket(getVendor(), getFeature(),
                                                             MyVendorIDs.MY_V3_COMMAND_ID);
        send(packet);
    }
}
