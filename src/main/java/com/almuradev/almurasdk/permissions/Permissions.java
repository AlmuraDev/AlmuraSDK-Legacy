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
 * Represents a set of permissions assigned by an authority
 *
 * @author Adam Mummery-Smith
 */
public interface Permissions {

    /**
     * Returns true if the specified permission is set in this permission container
     *
     * @param permission Name of the permission to test for
     * @return True if the permission exists in this set
     */
    boolean hasPermissionSet(String permission);

    /**
     * Returns true if the authority says we have this permission or false if the permission is denied or not set
     *
     * @param permission Name of the permission to test for
     */
    boolean hasPermission(String permission);

    /**
     * Returns true if the authority says we have this permission or if the permission is not specified by the authority returns the default value
     *
     * @param permission   Name of the permission to test for
     * @param defaultValue Value to return if the permission is NOT specified by the authority
     * @return State of the authority permission or default value if not specified
     */
    boolean hasPermission(String permission, boolean defaultValue);
}