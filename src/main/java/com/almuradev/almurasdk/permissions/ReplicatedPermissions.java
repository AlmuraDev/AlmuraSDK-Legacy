/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk.permissions;

/**
 * Represents a set of permissions assigned by a remote authority such as a server
 *
 * @author Adam Mummery-Smith
 */
public interface ReplicatedPermissions extends Permissions {

    /**
     * Return true if this permissions object is valid (within cache period)
     */
    boolean isValid();

    /**
     * Forcibly invalidate this permission container, forces update at the next opportunity
     */
    void invalidate();

    /**
     * Temporarily forces the permissions object to be valid to prevent repeated re-validation
     */
    void notifyRefreshPending();
}

