/*
 * This file is part of AlmuraSDK, licensed under the MIT License (MIT).
 *
 * Copyright (c) AlmuraDev <http://beta.almuramc.com/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.almuradev.almurasdk;

import com.almuradev.almurasdk.client.ClientProxy;
import com.almuradev.almurasdk.permissions.PermissionsManager;
import com.almuradev.almurasdk.permissions.PermissionsManagerClient;
import com.almuradev.almurasdk.server.ServerProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = AlmuraSDK.MOD_ID, name = "AlmuraSDK", version = "1.7.10-1291")
public final class AlmuraSDK {

    public static final String MOD_ID = "almurasdk";
    public static final Logger LOGGER = LogManager.getLogger(AlmuraSDK.class);
    public static final SimpleNetworkWrapper NETWORK_PERMISSIONS = new SimpleNetworkWrapper("PERMISSIONSREPL");

    @SidedProxy(clientSide = ClientProxy.CLASSPATH, serverSide = ServerProxy.CLASSPATH)
    public static CommonProxy PROXY;

    @EventHandler
    public void onPreInitializationEvent(FMLPreInitializationEvent event) {
        PROXY.onPreInitializationEvent(event);
    }

    public static PermissionsManager getPermissionsManager() {
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT:
                return PermissionsManagerClient.getInstance();
            default:
                throw new RuntimeException("Permissions management has not been developed for side [" + FMLCommonHandler.instance().getEffectiveSide().name() + "].");
        }
    }
}