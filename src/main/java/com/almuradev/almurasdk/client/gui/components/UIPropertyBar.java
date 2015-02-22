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
package com.almuradev.almurasdk.client.gui.components;

import net.malisis.core.client.gui.GuiTexture;
import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.UIComponent;
import net.malisis.core.client.gui.component.container.UIBackgroundContainer;
import net.malisis.core.client.gui.component.container.UIContainer;
import net.malisis.core.client.gui.component.decoration.UIImage;
import net.malisis.core.client.gui.icon.GuiIcon;
import net.malisis.core.renderer.animation.transformation.ITransformable;

public class UIPropertyBar extends UIContainer<UIPropertyBar> implements ITransformable.Color {

    private final UIImage barImage;
    private final UIBackgroundContainer background;
    private final int gapBetweenSymbolAndBar = 10;
    private final int symbolWidth = 7;
    private final int symbolHeight = 7;

    public UIPropertyBar(MalisisGui gui, GuiTexture spriteSheet, GuiIcon symbolIcon, GuiIcon barIcon) {
        this(gui, spriteSheet, symbolIcon, barIcon, UIComponent.INHERITED, UIComponent.INHERITED);
    }

    public UIPropertyBar(MalisisGui gui, GuiTexture spriteSheet, GuiIcon symbolIcon, GuiIcon barIcon, int width, int height) {
        super(gui, width, height);

        final UIImage symbolImage = new UIImage(gui, spriteSheet, symbolIcon);
        symbolImage.setSize(symbolWidth, symbolHeight);
        symbolImage.setPosition(UIComponent.INHERITED, UIComponent.INHERITED);

        barImage = new UIImage(gui, spriteSheet, barIcon);
        barImage.setSize(UIComponent.INHERITED - gapBetweenSymbolAndBar, UIComponent.INHERITED);
        barImage.setPosition(gapBetweenSymbolAndBar, UIComponent.INHERITED);

        background = new UIBackgroundContainer(gui, UIComponent.INHERITED - gapBetweenSymbolAndBar, UIComponent.INHERITED - 3);
        background.setPosition(gapBetweenSymbolAndBar + 1, UIComponent.INHERITED + 1);
        background.setClipContent(false);

        add(symbolImage, background, barImage);
    }

    @Override
    public void setColor(int color) {
        background.setColor(color);
    }

    public int getAmount() {
        return background.getWidth();
    }

    public UIPropertyBar setAmount(float percentage) {
        background.setSize((int) (percentage * (getWidth() - gapBetweenSymbolAndBar)), background.getHeight());
        return this;
    }

    @Override
    public UIPropertyBar setVisible(boolean visible) {
        background.setVisible(visible);
        return this;
    }
}
