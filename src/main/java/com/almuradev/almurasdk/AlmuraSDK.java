/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk;

import com.almuradev.almurasdk.client.ClientProxy;
import com.almuradev.almurasdk.server.ServerProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = AlmuraSDK.MOD_ID, name = "AlmuraSDK", version = "1.7.10-1277")
public class AlmuraSDK {

    public static final String MOD_ID = "almura_sdk";
    public static final Logger LOGGER = LogManager.getLogger(AlmuraSDK.class);
    public static final SimpleNetworkWrapper NETWORK_PERMISSIONS = new SimpleNetworkWrapper("PERMISSIONSREPL");

    @SidedProxy(clientSide = ClientProxy.CLASSPATH, serverSide = ServerProxy.CLASSPATH)
    public static CommonProxy PROXY;

    @EventHandler
    public void onPreInitializationEvent(FMLPreInitializationEvent event) {
        PROXY.onPreInitialization(event);
    }
}