/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.client;

import com.almuradev.almurasdk.CommonProxy;
import com.almuradev.almurasdk.permissions.Permissible;
import com.almuradev.almurasdk.permissions.PermissionsManager;
import com.almuradev.almurasdk.permissions.PermissionsManagerClient;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

public class ClientProxy extends CommonProxy {

    public static final String CLASSPATH = "com.almuradev.almurasdk.client.ClientProxy";

    // ================ Test Code ===============
    private Permissible myPermissible;

    @Override
    public void onPreInitialization(FMLPreInitializationEvent event) {
        super.onPreInitialization(event);
        FMLCommonHandler.instance().bus().register(this);

        myPermissible = new Permissible() {
            @Override
            public String getPermissibleModName() {
                return "backpack";
            }

            @Override
            public float getPermissibleModVersion() {
                return 1;
            }

            @Override
            public void registerPermissions(PermissionsManagerClient permissionsManager) {
                permissionsManager.registerModPermission(this, "open");
            }

            @Override
            public void onPermissionsCleared(PermissionsManager manager) {

            }

            @Override
            public void onPermissionsChanged(PermissionsManager manager) {

            }
        };

        PermissionsManagerClient.getInstance().registerPermissible(myPermissible);
    }

    @SubscribeEvent
    public void onTickEvent(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().inGameHasFocus) {
            System.out.println("Server says mod.backpack.open: " + PermissionsManagerClient.getInstance().getPermissions(myPermissible).hasPermission("mod.backpack.open"));
        }
    }
}
