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
package com.almuradev.almurasdk.client.gui.components;

import com.almuradev.almurasdk.client.gui.SimpleGui;
import net.malisis.core.client.gui.GuiRenderer;
import net.malisis.core.client.gui.GuiTexture;
import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.UIComponent;
import net.malisis.core.client.gui.element.GuiShape;
import net.malisis.core.client.gui.element.SimpleGuiShape;
import net.malisis.core.client.gui.icon.GuiIcon;

public class UIPropertyBar extends UIComponent<UIPropertyBar> {

//  private static final Color LIGHT_GREEN = new Color("light_green", 65280);
//  private static final Color LIGHT_ORANGE = new Color("light_orange", 13413376);
//  private static final Color RED = new Color("red", 11474462);
    public static final int LIGHT_GREEN = 0x00FF00;
    public static final int LIGHT_ORANGE = 0xCCAC00;
    public static final int RED = 0xAF161E;

    private GuiTexture texture = SimpleGui.TEXTURE_SPRITESHEET;

    private final GuiShape iconShape;
    private final GuiShape barShape;

    private final GuiIcon barIcon;
    private final GuiIcon symbolIcon;
    private final int iconSize = 7;
    private final int iconGap = 3;

    private boolean relativeColor = true;
    private int color = 0;
    private float amount;

    public UIPropertyBar(MalisisGui gui, GuiIcon symbolIcon) {
        super(gui);

        setSize(105, 7);

        this.symbolIcon = symbolIcon;
        this.barIcon = SimpleGui.ICON_BAR;

        iconShape = new SimpleGuiShape();
        iconShape.setSize(iconSize, iconSize);
        iconShape.storeState();

        barShape = new SimpleGuiShape();
        barShape.setSize(width - iconSize - iconGap, 7);
        barShape.translate(iconSize + iconGap, 0, 1);
        barShape.storeState();
    }

    public boolean isRelativeColor() {
        return relativeColor;
    }

    public UIPropertyBar setRelativeColor(boolean relativeColor) {
        this.relativeColor = relativeColor;
        return this;
    }

    public UIPropertyBar setColor(int color) {
        this.color = color;
        return this;
    }

    public int getColor() {

        if (!relativeColor)
            return color;

        if (amount >= 0.6F)
            return LIGHT_GREEN;
        if (amount >= 0.3F)
            return LIGHT_ORANGE;
        return RED;
    }

    public float getAmount() {
        return amount;
    }

    public UIPropertyBar setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    private int getBarWidth() {
        return (int) ((width - iconSize - iconGap - 2) * amount);
    }

    @Override
    public void drawBackground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick) {
        renderer.bindTexture(texture);

        // draw the icon
        iconShape.resetState();
        rp.icon.set(symbolIcon);
        renderer.drawShape(iconShape, rp);

        // draw the background
        barShape.resetState();
        rp.icon.set(barIcon);
        renderer.drawShape(barShape, rp);
    }

    @Override
    public void drawForeground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick) {
        // draw the colored rectangle
        if (amount > 0)
            renderer.drawRectangle(iconSize + iconGap + 1, 1, 0, getBarWidth(), 5, getColor(), 255);
    }

}
