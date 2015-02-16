/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.client;

import com.almuradev.almurasdk.AlmuraSDK;
import com.almuradev.almurasdk.CommonProxy;
import com.almuradev.almurasdk.client.network.play.C00PacketPermissionsQuery;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    public static final String CLASSPATH = "com.almuradev.almurasdk.client.ClientProxy";

    @Override
    public void onPreInitialization(FMLPreInitializationEvent event) {
        super.onPreInitialization(event);

        AlmuraSDK.NETWORK_PERMISSIONS.registerMessage(C00PacketPermissionsQuery.class, C00PacketPermissionsQuery.class, 0, Side.SERVER);
    }
}
