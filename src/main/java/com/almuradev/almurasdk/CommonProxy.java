/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk;

import com.almuradev.almurasdk.server.network.play.S00PacketPermissions;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

    public void onPreInitialization(FMLPreInitializationEvent event) {
        AlmuraSDK.NETWORK_PERMISSIONS.registerMessage(S00PacketPermissions.class, S00PacketPermissions.class, 0, Side.CLIENT);
    }
}
