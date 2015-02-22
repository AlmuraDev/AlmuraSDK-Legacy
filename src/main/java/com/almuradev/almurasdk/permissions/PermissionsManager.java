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
     * Get the underlying permissions node for this manager for the specified permissions' mod id.
     *
     * @param permModId The id
     * @return The permissions node or null if not found
     */
    Permissions getPermissions(String permModId);

    /**
     * Register a new event listener, the registered object will receive callbacks for permissions events
     *
     * @param permissible
     */
    void registerPermissible(Permissible permissible);

    /**
     * Register a permission for all mods, the permission will be prefixed with "mod.all." to provide
     * a common namespace for client mods when permissions are replicated to the server
     *
     * @param permission
     */
    void registerPermission(String permission);

    /**
     * Register a permission for the specified mod, the permission will be prefixed with "mod.{modname}." to provide
     * a common namespace for client mods when permissions are replicated to the server
     *
     * @param mod
     * @param permission
     */
    void registerModPermission(Permissible mod, String permission);

    /**
     * Get the value of the specified permission for all mods.
     *
     * @param permission Permission to check for
     */
    boolean getPermission(String permission);

    /**
     * Get the value of the specified permission for all mods and return the default value if the permission is not set
     *
     * @param permission   Permission to check for
     * @param defaultValue Value to return if the permission is not set
     */
    boolean getPermission(String permission, boolean defaultValue);

    /**
     * Get the value of the specified permission for the specified mod. The permission will be prefixed with "mod.{modname}."
     * in keeping with registerModPermission as a convenience.
     *
     * @param mod
     * @param permission
     */
    boolean getModPermission(Permissible mod, String permission);

    /**
     * Get the value of the specified permission for the specified mod. The permission will be prefixed with "mod.{modname}."
     * in keeping with registerModPermission as a convenience.
     *
     * @param modName
     * @param permission
     */
    boolean getModPermission(String modName, String permission);

    /**
     * Get the value of the specified permission for the specified mod. The permission will be prefixed with "mod.{modname}."
     * in keeping with registerModPermission as a convenience. If the permission does not exist, the specified default value
     * will be returned.
     *
     * @param mod
     * @param permission
     * @param defaultValue
     */
    boolean getModPermission(Permissible mod, String permission, boolean defaultValue);

    /**
     * Get the value of the specified permission for the specified mod. The permission will be prefixed with "mod.{modname}."
     * in keeping with registerModPermission as a convenience.
     *
     * @param modName
     * @param permission
     */
    boolean getModPermission(String modName, String permission, boolean defaultValue);
}

