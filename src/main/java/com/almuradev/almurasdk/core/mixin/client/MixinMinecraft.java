/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk.core.mixin.client;

import com.almuradev.almurasdk.event.gui.GuiClosingEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Inject(method = "displayGuiScreen", at = @At(value = "HEAD"), cancellable = true)
    public void displayGuiScreen(GuiScreen screen, CallbackInfo ci)
    {
        if (screen != null) {
            if (MinecraftForge.EVENT_BUS.post(new GuiClosingEvent(screen))) {
                ci.cancel();
            }
        }
    }
}
