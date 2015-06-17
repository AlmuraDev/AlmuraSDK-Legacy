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
package com.almuradev.almurasdk.client.gui.dialog;

import static com.google.common.base.Preconditions.checkNotNull;

import com.almuradev.almurasdk.client.gui.SimpleGui;
import com.almuradev.almurasdk.client.gui.components.UIForm;
import net.malisis.core.client.gui.GuiRenderer;
import net.malisis.core.client.gui.component.UIComponent;
import net.malisis.core.client.gui.icon.GuiIcon;
import net.malisis.core.renderer.font.FontRenderOptions;

public class UIMessageBox extends UIComponent<UIMessageBox> {
    private UIForm form;
    private FontRenderOptions messageOptions;
    private String message;
    private MessageBoxButtons buttonLayout;
    private MessageBoxIcon statusIcon;
    private MessageBoxExecutor executor;

    public UIMessageBox(SimpleGui parentGui, String name, String title, String message, FontRenderOptions messageOptions, MessageBoxButtons
            buttonLayout, MessageBoxIcon statusIcon, MessageBoxExecutor executor) {
        super(parentGui);
        setName(name);
        this.message = message;
        this.messageOptions = messageOptions;
        this.buttonLayout = buttonLayout;
        this.statusIcon = statusIcon;
        this.executor = executor;
        form = new UIForm(parentGui, 175, 125, title, true);
        form.setName("messagebox.form");
    }

    public String getTitle() {
        return form.getTitle();
    }

    public UIMessageBox setTitle(String title) {
        checkNotNull(form, "form");
        checkNotNull(title, "title");
        form.setTitle(title);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public UIMessageBox setMessage(String message) {
        this.message = message;
        return this;
    }

    public FontRenderOptions getMessageOptions() {
        return messageOptions;
    }

    public UIMessageBox setMessageOptions(FontRenderOptions messageOptions) {
        this.messageOptions = messageOptions;
        return this;
    }

    public MessageBoxButtons getButtonLayout() {
        return buttonLayout;
    }

    public UIMessageBox setButtonLayout(MessageBoxButtons buttonLayout) {
        this.buttonLayout = buttonLayout;
        // TODO Re-generate buttonLayout
        return this;
    }

    public MessageBoxIcon getStatusIcon() {
        return statusIcon;
    }

    public UIMessageBox setStatusIcon(MessageBoxIcon statusIcon) {
        this.statusIcon = statusIcon;
        // TODO Set statusIcon in GUI
        return this;
    }

    @Override
    public void drawBackground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick) {

    }

    @Override
    public void drawForeground(GuiRenderer renderer, int mouseX, int mouseY, float partialTick) {

    }

    /**
     * Returns a copy of this MessageBox. Useful for building off of a template.
     * <p>
     * This will not return a copy of the {@link MessageBoxExecutor}.
     * @return The copy
     */
    public UIMessageBox copy() {
        return null;
        //return new MessageBox();
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {
        private SimpleGui gui = null;
        private String name = "", title = "", message = "";
        private FontRenderOptions messageOptions = new FontRenderOptions();
        private MessageBoxButtons buttonLayout = MessageBoxButtons.OK;
        private MessageBoxIcon statusIcon = MessageBoxIcon.NONE;
        private MessageBoxExecutor executor = MessageBoxExecutor.NO_EXECUTION;

        public Builder gui(SimpleGui gui) {
            checkNotNull(gui, "gui");
            this.gui = gui;
            return this;
        }

        public Builder name(String name) {
            checkNotNull(name, "name");
            this.name = name;
            return this;
        }

        public Builder title(String title) {
            checkNotNull(title, "title");
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            checkNotNull(message, "message");
            this.message = message;
            return this;
        }

        public Builder format(FontRenderOptions messageOptions) {
            checkNotNull(messageOptions, "messageOptions");
            this.messageOptions = messageOptions;
            return this;
        }

        public Builder buttonLayout(MessageBoxButtons buttonLayout) {
            checkNotNull(buttonLayout, "buttonLayout");
            this.buttonLayout = buttonLayout;
            return this;
        }

        public Builder statusIcon(MessageBoxIcon statusIcon) {
            checkNotNull(statusIcon, "statusIcon");
            this.statusIcon = statusIcon;
            return this;
        }

        public Builder execute(MessageBoxExecutor handler) {
            checkNotNull(handler, "executor");
            this.executor = handler;
            return this;
        }

        public UIMessageBox build() {
            if (gui == null) {
                throw new RuntimeException("Attempt to build a MessageBox without providing a gui instance!");
            }
            return new UIMessageBox(gui, name, title, message, messageOptions, buttonLayout, statusIcon, executor);
        }
    }

    public enum MessageBoxButtons {
        OK,
        OK_CANCEL,
        YES_NO,
        YES_NO_CANCEL
    }

    public enum ClickedButton {
        OK,
        YES,
        NO,
        CANCEL
    }

    /**
     * TODO Ask Wifee to do this once we get our icons...
     */
    public enum MessageBoxIcon {
        ASTERISK(SimpleGui.TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        ERROR(SimpleGui.TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        EXCLAMATION(SimpleGui.TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        HAND(SimpleGui.TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        INFORMATION(SimpleGui.TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        NONE(SimpleGui.TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        STOP(SimpleGui.TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        WARNING(SimpleGui.TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0));

        private final GuiIcon icon;

        MessageBoxIcon(GuiIcon icon) {
            this.icon = icon;
        }

        public GuiIcon getIcon() {
            return icon;
        }
    }
}
