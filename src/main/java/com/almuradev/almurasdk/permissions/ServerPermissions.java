/*
 * This file is part of AlmuraSDK, licensed under the MIT License (MIT).
 *
 * Copyright (c) AlmuraDev <http://github.com/AlmuraDev/AlmuraSDK/>
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

import net.eq2online.permissions.ReplicatedPermissionsContainer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Replicated permissions implementation
 *
 * @author Adam Mummery-Smith
 */
public class ServerPermissions implements ReplicatedPermissions {

    /**
     * Pattern for recognising valid permissions in the server feed
     */
    private static final Pattern permissionPattern = Pattern.compile("^([\\+\\-])(([a-z0-9]+\\.)*[a-z0-9\\*]+)$", Pattern.CASE_INSENSITIVE);

    protected String modName;

    /**
     * Root permission node
     */
    protected Permission permissions = new Permission();

    /**
     * Time the permissions were updated
     */
    protected long createdTime = 0L;

    /**
     * Expiry time of the current data cache
     */
    protected long validUntil = 0L;

    /**
     * Time to cache server responses by default
     */
    protected long cacheTime = 10L * 60L * 500L;    // 5 minutes

    /**
     * Time to wait when refreshing server permissions before trying again
     */
    protected long refreshTime = 15L * 1000L;        // 15 seconds

    /**
     * @param response
     */
    public ServerPermissions(ReplicatedPermissionsContainer response) {
        this.createdTime = System.currentTimeMillis();
        this.validUntil = this.createdTime + this.cacheTime;

        if (response != null) {
            response.sanitise();

            this.modName = response.modName;
            this.validUntil = System.currentTimeMillis() + response.remoteCacheTimeSeconds * 1000L;

            for (String permissionString : response.permissions) {
                Matcher permissionMatcher = permissionPattern.matcher(permissionString);

                if (permissionMatcher.matches()) {
                    String name = permissionMatcher.group(2);
                    boolean value = permissionMatcher.group(1).equals("+");

                    this.permissions.setPermissionAndValue(name, value);
                }
            }
        }
    }

    /**
     * Get the permissible mod name
     */
    public String getModName() {
        return this.modName;
    }

    @Override
    public boolean hasPermissionSet(String permission) {
        return this.permissions.getPermission(permission) != null;
    }

    @Override
    public boolean hasPermission(String permission) {
        Permission perm = this.permissions.getPermission(permission);
        return perm != null && perm.getValue();
    }

    @Override
    public boolean hasPermission(String permission, boolean defaultValue) {
        Permission perm = this.permissions.getPermission(permission);

        return perm != null ? perm.getValue() : defaultValue;
    }

    @Override
    public boolean isValid() {
        return System.currentTimeMillis() < this.validUntil;
    }

    @Override
    public void invalidate() {
        this.validUntil = 0L;
    }

    @Override
    public void notifyRefreshPending() {
        this.validUntil = System.currentTimeMillis() + this.refreshTime;
    }
}

