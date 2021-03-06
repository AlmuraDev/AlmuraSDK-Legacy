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
import com.almuradev.almurasdk.util.Colors;
import com.google.common.eventbus.Subscribe;
import net.malisis.core.client.gui.Anchor;
import net.malisis.core.client.gui.component.UIComponent;
import net.malisis.core.client.gui.component.container.UIBackgroundContainer;
import net.malisis.core.client.gui.component.interaction.UIButton;
import net.malisis.core.client.gui.element.SimpleGuiShape;
import net.malisis.core.util.MouseButton;

public class UIForm extends UIBackgroundContainer {

    public static final com.almuradev.almurasdk.util.Color ALMURA_BLUE = new com.almuradev.almurasdk.util.Color("almura_blue", 9283532);
    private static final int TITLE_BAR_HEIGHT = 13;
    private final UIBackgroundContainer contentContainer, titleContainer;

    /**
     * Creates a form with no title that has a close button
     *
     * @param parent The parent {@link SimpleGui}
     * @param width  The width of the form
     * @param height The height of the form
     */
    public UIForm(SimpleGui parent, int width, int height) {
        this(parent, width, height, "", true);
    }

    /**
     * Creates a form with a title that has a close buton
     *
     * @param parent The parent {@link SimpleGui}
     * @param width  The width of the form
     * @param height The height of the form
     * @param title  The title of the form
     */
    public UIForm(SimpleGui parent, int width, int height, String title) {
        this(parent, width, height, title, true);
    }

    /**
     * Creates a form with a title that may or may not show a close button
     *
     * @param parent          The parent {@link SimpleGui}
     * @param width           The width of the form
     * @param height          The height of the form
     * @param title           The title of the form
     * @param showCloseButton Specifies if this form has a close button
     */
    public UIForm(SimpleGui parent, int width, int height, String title, boolean showCloseButton) {
        super(parent);

        // Setup controls
        titleContainer = new DraggableBackgroundContainer(parent, showCloseButton);
        contentContainer = new UIBackgroundContainer(parent);

        // Setup title
        setTitle(title);
        titleLabel.getFontRenderOptions().color = Colors.BLACK.getGuiColorCode();
        titleLabel.setPosition(4, 1, Anchor.LEFT | Anchor.MIDDLE);

        setSize(width, height);

        titleContainer.setSize(INHERITED, TITLE_BAR_HEIGHT);
        titleContainer.setColor(ALMURA_BLUE.getGuiColorCode());
        titleContainer.register(this);

        contentContainer.setSize(INHERITED, getHeight() - TITLE_BAR_HEIGHT);
        contentContainer.setPosition(0, TITLE_BAR_HEIGHT);
        contentContainer.setBackgroundAlpha(0);

        // Add controls
        this.add(titleContainer, contentContainer);

        // Set form properties
        setColor(Integer.MIN_VALUE);
        setBackgroundAlpha(175);
    }

    /**
     * The {@link UIBackgroundContainer} that holds all the content, all controls are added to this.
     *
     * @return The content container
     */
    public final UIBackgroundContainer getContentContainer() {
        return contentContainer;
    }

    /**
     * Sets the width of the form
     *
     * @param width The width to set the form to
     */
    public final void setWidth(int width) {
        this.setSize(width, height);
    }

    /**
     * Sets the height of the form
     *
     * @param height The height to set the form to
     */
    public final void setHeight(int height) {
        this.setSize(width, height);
    }

    @Override
    public final UIForm setSize(int width, int height) {
        super.setSize(width, height);
        contentContainer.setSize(INHERITED, getHeight() - TITLE_BAR_HEIGHT);
        return this;
    }

    @Override
    public final UIBackgroundContainer setTitle(String title) {
        if (title == null || title.isEmpty()) {
            titleContainer.remove(titleLabel);
            return this;
        }

        titleLabel.setText(title);
        titleContainer.add(titleLabel);
        return this;
    }

    private class DraggableBackgroundContainer extends UIBackgroundContainer {

        private CloseButton titleCloseButton;

        public DraggableBackgroundContainer(SimpleGui parent, boolean showCloseButton) {
            super(parent);

            if (showCloseButton) {
                titleCloseButton = new CloseButton(parent);
                titleCloseButton.setPosition(0, 0, Anchor.RIGHT | Anchor.TOP);
                titleCloseButton.setName("form.title.close");
                titleCloseButton.register(this);
                add(titleCloseButton);
            }
        }

        @Override
        //TODO Grinch, this now has snapshot/live values and may need to be re-done
        public boolean onDrag(int lastX, int lastY, int x, int y, MouseButton button) {
            if (button != MouseButton.LEFT || (titleCloseButton != null && titleCloseButton.isInsideBounds(x, y))) {
                return false;
            }

            final UIComponent<?> parentContainer = getParent().getParent();
            if (parentContainer == null) {
                return false;
            }

            final int xPos = getParent().getParent().relativeX(x) - relativeX(lastX);
            final int yPos = getParent().getParent().relativeY(y) - relativeY(lastY);

            getParent().setPosition(xPos < 0 ? 0 : xPos, yPos < 0 ? 0 : yPos, Anchor.NONE);
            return true;
        }

        @Subscribe
        public void onClick(UIButton.ClickEvent event) {
            switch (event.getComponent().getName().toLowerCase()) {
                case "form.title.close":
                    getParent().getGui().close();
            }
        }
    }

    private class CloseButton extends UIButton {

        public CloseButton(SimpleGui gui) {
            super(gui, "");
            width = 23;
            height = 10;
            shape = new SimpleGuiShape();
            icon = SimpleGui.ICON_CLOSE_NORMAL;
            iconHovered = SimpleGui.ICON_CLOSE_HOVER;
            iconPressed = SimpleGui.ICON_CLOSE_PRESSED;
        }
    }
}
