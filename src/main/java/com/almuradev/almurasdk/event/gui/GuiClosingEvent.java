/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk.event.gui;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.client.gui.GuiScreen;

/**
 * Fired when a {@link net.minecraft.client.gui.GuiScreen} is attempting to be closed.
 */
@Cancelable
public class GuiClosingEvent extends Event {
    public final GuiScreen closed;

    public GuiClosingEvent(GuiScreen closed) {
        this.closed = closed;
    }
}
