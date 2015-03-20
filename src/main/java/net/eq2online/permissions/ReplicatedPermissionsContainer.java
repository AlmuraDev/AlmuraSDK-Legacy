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
package net.eq2online.permissions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Serializable container object
 *
 * @author Adam Mummery-Smith
 */
public class ReplicatedPermissionsContainer implements Serializable {

    public static final String CHANNEL = "PERMISSIONSREPL";
    /**
     * Serial version UID to suppoer Serializable interface
     */
    private static final long serialVersionUID = -764940324881984960L;
    /**
     * Mod name
     */
    public String modName = "all";
    /**
     * Mod version
     */
    public Float modVersion = 0.0F;
    /**
     * List of permissions to replicate, prepend "-" for a negated permission and "+" for a granted permission
     */
    public Set<String> permissions = new TreeSet<>();
    /**
     * Amount of time in seconds that the client will trust these permissions for before requesting an update
     */
    public long remoteCacheTimeSeconds = 600L;    // 10 minutes

    public ReplicatedPermissionsContainer() {
    }

    public ReplicatedPermissionsContainer(String modName, Float modVersion, Collection<String> permissions) {
        this.modName = modName;
        this.modVersion = modVersion;
        this.permissions.addAll(permissions);
    }

    /**
     * Deserialises a replicated permissions container from a byte array
     *
     * @param data Byte array containing the serialised data
     * @return new container or null if deserialisation failed
     */
    public static ReplicatedPermissionsContainer fromBytes(byte[] data) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            return (ReplicatedPermissionsContainer) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException ignored) {
        }

        return null;
    }

    /**
     * Deserialises a replicated permissions container from a byte array, stripping away the discriminator.
     *
     * @param data Byte array containing the serialised data
     * @return new container or null if deserialisation failed
     */
    public static ReplicatedPermissionsContainer fromBytesWithDiscriminator(byte[] data) {
        try {
            final byte[] actualBytes = Arrays.copyOfRange(data, 1, data.length);
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(actualBytes));
            return (ReplicatedPermissionsContainer) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException ignored) {
        }

        return null;
    }

    /**
     * Add all of the listed permissions to this container
     *
     * @param permissions
     */
    public void addAll(Collection<String> permissions) {
        this.permissions.addAll(permissions);
    }

    /**
     * Check and correct
     */
    public void sanitise() {
        if (this.modName == null || this.modName.length() < 1) {
            this.modName = "all";
        }
        if (this.modVersion == null || this.modVersion < 0.0F) {
            this.modVersion = 0.0F;
        }
        if (this.remoteCacheTimeSeconds < 0) {
            this.remoteCacheTimeSeconds = 600L;
        }
    }

    /**
     * Serialise this container to a byte array for transmission to a remote host
     *
     * @return
     */
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteStream).writeObject(this);
            return byteStream.toByteArray();
        } catch (IOException ignored) {
        }

        return new byte[0];
    }
}
