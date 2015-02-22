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
package com.almuradev.almurasdk.server.network.play;

import com.almuradev.almurasdk.permissions.Permissible;
import com.almuradev.almurasdk.permissions.PermissionsManagerClient;
import com.almuradev.almurasdk.permissions.ServerPermissions;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.eq2online.permissions.ReplicatedPermissionsContainer;

/**
 * Two-way communication of permissions:
 *
 * C to S
 *     Sends the server all registered permissibles.
 * S to C
 *     Sends the client the status of the permissibles (true or false enable status)
 */
public class S00PacketPermissions implements IMessage, IMessageHandler<S00PacketPermissions, IMessage> {

    public ReplicatedPermissionsContainer container;

    public S00PacketPermissions() {
    }

    public S00PacketPermissions(ReplicatedPermissionsContainer container) {
        this.container = container;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        container = ReplicatedPermissionsContainer.fromBytes(buf.readBytes(buf.readableBytes()).array());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBytes(container.getBytes());
    }

    @Override
    public IMessage onMessage(S00PacketPermissions message, MessageContext ctx) {
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
