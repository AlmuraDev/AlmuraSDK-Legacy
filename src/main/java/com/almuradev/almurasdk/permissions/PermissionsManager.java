/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.permissions;

import cpw.mods.fml.common.gameevent.TickEvent;

/**
 * Interface for permissions manager implementations
 *
 * @author Adam Mummery-Smith
 */
public interface PermissionsManager {

    /**
     * Get the underlying permissions node for this manager for the specified mod
     *
     * @param mod Mod to fetch permissions for
     */
    Permissions getPermissions(Permissible mod);

    /**
     * Get the time the permissions for the specified mod were last updated
     *
     * @param mod Mod to check for
     * @return Timestamp when the permissions were last updated
     */
    Long getPermissionUpdateTime(Permissible mod);

    /**
     * Register a new event listener, the registered object will receive callbacks for permissions events
     *
     * @param permissible
     */
    void registerPermissible(Permissible permissible);
}

