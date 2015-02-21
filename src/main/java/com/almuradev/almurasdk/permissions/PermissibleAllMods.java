/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk.permissions;

import java.util.HashSet;
import java.util.Set;

public class PermissibleAllMods implements Permissible {

    private Set<Permissible> permissibles = new HashSet<>();

    public void addPermissible(Permissible permissible) {
        this.permissibles.add(permissible);
    }

    @Override
    public String getPermissibleModName() {
        return "all";
    }

    @Override
    public float getPermissibleModVersion() {
        return 0.0F;
    }

    @Override
    public void registerPermissions(PermissionsManager permissionsManager) {
    }

    @Override
    public void onPermissionsCleared(PermissionsManager manager) {
        for (Permissible permissible : this.permissibles) {
            permissible.onPermissionsCleared(manager);
        }
    }

    @Override
    public void onPermissionsChanged(PermissionsManager manager) {
        for (Permissible permissible : this.permissibles) {
            permissible.onPermissionsChanged(manager);
        }
    }
}

