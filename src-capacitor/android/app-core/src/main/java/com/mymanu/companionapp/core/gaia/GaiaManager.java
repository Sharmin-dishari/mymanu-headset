/*
 * ************************************************************************************************
 * * © 2020, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.       *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia;

import com.mymanu.companionapp.core.GaiaClientService;
import com.mymanu.companionapp.core.gaia.core.sending.GaiaSender;
import com.mymanu.companionapp.core.gaia.core.Vendor;
import com.mymanu.companionapp.core.gaia.core.transport.GaiaReader;
import com.mymanu.companionapp.core.gaia.core.Plugin;

/**
 * <p>Represents a managing class for GAIA. This allows to register a GAIA {@link Vendor} with the
 * core library.</p>
 * <p>The {@link GaiaManager} can be retrieved using {@link GaiaClientService#getGaiaManager()}.</p>
 */
public interface GaiaManager {

    /**
     * <p>This method allows vendors to register in order to get the packets that corresponds to
     * their vendor ID.</p>
     */
    void registerVendor(Vendor vendor);

    /**
     * <p>To get the sender GAIA {@link Vendor} and
     * {@link Plugin} need to send packets to a
     * connected device.</p>
     */
    GaiaSender getSender();

    /**
     * To get the GAIA stream analyser for RFCOMM connections. This is used by the transport
     * layer when initialising a connection.
     */
    GaiaReader.Listener getReaderListener();

}
