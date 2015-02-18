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

public class ClientProxy extends CommonProxy {

    public static final String CLASSPATH = "com.almuradev.almurasdk.client.ClientProxy";

    // ================ Test Code ===============
    private Permissible myPermissible;

    @Override
    public void onPreInitialization(FMLPreInitializationEvent event) {
        super.onPreInitialization(event);
        FMLCommonHandler.instance().bus().register(this);
        
        // TODO Register with FML better
        PermissionsManagerClient.getInstance();
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
                System.out.println(manager.getPermissions(this).hasPermission("mod.backpack.open"));
            }
        };

        PermissionsManagerClient.getInstance().registerPermissible(myPermissible);
    }
}
