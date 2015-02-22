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
package com.almuradev.almurasdk.permissions;

public interface Permissible {

    /**
     * Returns the node name of the mod, replicated permissions will be of the form mod.{modname}.permission.node so this
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
