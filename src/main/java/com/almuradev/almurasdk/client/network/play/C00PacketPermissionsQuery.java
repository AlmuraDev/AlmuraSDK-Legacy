/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.client.network.play;

import com.almuradev.almurasdk.permissions.ReplicatedPermissionsContainer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class C00PacketPermissionsQuery implements IMessage, IMessageHandler<C00PacketPermissionsQuery, IMessage> {

    public ReplicatedPermissionsContainer container;

    public C00PacketPermissionsQuery() {
    }

    public C00PacketPermissionsQuery(ReplicatedPermissionsContainer container) {
        this.container = container;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        container = ReplicatedPermissionsContainer.readPermissionsContainer(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ReplicatedPermissionsContainer.writePermissionsContainer(buf, container);
    }

    @Override
    public IMessage onMessage(C00PacketPermissionsQuery message, MessageContext ctx) {
        return null;
    }
}
