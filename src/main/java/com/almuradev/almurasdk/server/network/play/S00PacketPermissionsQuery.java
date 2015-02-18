/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.server.network.play;

import com.almuradev.almurasdk.permissions.Permissible;
import com.almuradev.almurasdk.permissions.PermissionsManagerClient;
import com.almuradev.almurasdk.permissions.ServerPermissions;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.eq2online.permissions.ReplicatedPermissionsContainer;

public class S00PacketPermissionsQuery implements IMessage, IMessageHandler<S00PacketPermissionsQuery, IMessage> {

    public ReplicatedPermissionsContainer container;

    public S00PacketPermissionsQuery() {
    }

    public S00PacketPermissionsQuery(ReplicatedPermissionsContainer container) {
        this.container = container;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        container = ReplicatedPermissionsContainer.fromBytes(buf.array());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBytes(container.getBytes());
    }

    @Override
    public IMessage onMessage(S00PacketPermissionsQuery message, MessageContext ctx) {
        if (ctx.side.isClient()) {
            ServerPermissions permissions = null;

            try {
                permissions = new ServerPermissions(message.container);
            } catch (Exception ignored) {
            }

            if (permissions != null && permissions.getModName() != null) {
                PermissionsManagerClient.getInstance().putServerPermissions(permissions.getModName(), permissions);
                Permissible permissible = PermissionsManagerClient.getInstance().getForMod(permissions.getModName());
                if (permissible != null) {
                    permissible.onPermissionsChanged(PermissionsManagerClient.getInstance());
                }
            }
        }

        return null;
    }
}
