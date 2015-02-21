/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk.permissions;

public class LocalPermissions implements Permissions {

    @Override
    public boolean hasPermissionSet(String permission) {
        return true;
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }

    @Override
    public boolean hasPermission(String permission, boolean defaultValue) {
        return defaultValue;
    }
}
