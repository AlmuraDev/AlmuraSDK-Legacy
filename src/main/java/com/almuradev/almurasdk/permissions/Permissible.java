/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.permissions;

public interface Permissible {

    /**
     * Returns the node name of the mod, replicated permissions will be of the form mod.<name>.permission.node so this
     * method must return a valid name for use in permission nodes. This method must also return the same value every
     * time it is called since permissible names are not necessarily cached.
     *
     * @return Permissible name
     */
    String getPermissibleModName();

    /**
     * The mod version to replicate to the server
     *
     * @return Mod version as a float
     */
    float getPermissibleModVersion();

    /**
     * Called by the permissions manager at initialisation to instruct the mod to populate the list of permissions it
     * supports. This method should call back against the supplied permissions manager to register the permissions to
     * be sent to the server when connecting.
     *
     * @param permissionsManager permissions manager
     */
    void registerPermissions(PermissionsManager permissionsManager);

    /**
     * Called when the permissions set is cleared
     *
     * @param manager
     */
    void onPermissionsCleared(PermissionsManager manager);

    /**
     * Called when the permissions are changed (eg. when new permissions are received from the server)
     *
     * @param manager
     */
    void onPermissionsChanged(PermissionsManager manager);
}
