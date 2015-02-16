/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.permissions;

import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Serializable container object
 *
 * @author Adam Mummery-Smith
 */
public class ReplicatedPermissionsContainer implements Serializable {

    /**
     * Serial version UID to support Serializable interface
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
    public long remoteCacheTimeSeconds = 300L;


    public ReplicatedPermissionsContainer() {
    }

    public ReplicatedPermissionsContainer(String modName, Float modVersion, Collection<String> permissions) {
        this.modName = modName;
        this.modVersion = modVersion;
        this.permissions.addAll(permissions);
    }

    /**
     * Deserialises a replicated permissions container from a {@link ByteBuf}.
     *
     * @param buf Byte array containing the serialised data
     * @return new container or null if deserialisation failed
     */
    public static ReplicatedPermissionsContainer readPermissionsContainer(ByteBuf buf) {
        try {
            int readableBytes = buf.readableBytes();
            if (readableBytes == 0) {
                return null;
            }

            byte[] payload = new byte[readableBytes];
            buf.readBytes(payload);

            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(payload));
            return (ReplicatedPermissionsContainer) inputStream.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException ignored) {
        }

        return null;
    }

    /**
     * Serializes a replicated permissions container into a {@link ByteBuf}.
     *
     * @param buf Byte array to write to
     * @param container Container to serialize
     */
    public static void writePermissionsContainer(ByteBuf buf, ReplicatedPermissionsContainer container) {
        buf.writeBytes(container.getBytes());
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

