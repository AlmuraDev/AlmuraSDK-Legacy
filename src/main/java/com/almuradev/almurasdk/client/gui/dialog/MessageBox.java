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
import com.google.common.base.Optional;
import net.malisis.core.client.gui.icon.GuiIcon;

public class MessageBox extends SimpleGui {
    private UIForm form;
    private String title;

    public MessageBox(String title) {
        this.title = title;
    }
    @Override
    public void construct() {
        form = new UIForm(this, 125, 175, title);
    }

    /**
     * Returns a copy of this MessageBox. Useful for building off of a template.
     * <p>
     * This will not return a copy of the {@link MessageBoxExecutor}.
     * @return The copy
     */
    public MessageBox copy() {
        return null;
        //return new MessageBox();
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {
        private Optional<SimpleGui> parent;
        private String name, title, message;
        private MessageBoxButtons  buttons;
        private MessageBoxIcon icon;
        private MessageBoxExecutor handler;

        public Builder parent(SimpleGui parent) {
            this.parent = Optional.fromNullable(parent);
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

        public Builder buttons(MessageBoxButtons buttons) {
            checkNotNull(buttons, "buttons");
            this.buttons = buttons;
            return this;
        }

        public Builder icon(MessageBoxIcon icon) {
            checkNotNull(icon, "icon");
            this.icon = icon;
            return this;
        }

        public Builder execute(MessageBoxExecutor handler) {
            checkNotNull(handler, "handler");
            this.handler = handler;
            return this;
        }

        public MessageBox build() {
            return null;
            //return new MessageBox();
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
        ASTERISK(TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        ERROR(TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        EXCLAMATION(TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        HAND(TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        INFORMATION(TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        NONE(TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        STOP(TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0)),
        WARNING(TEXTURE_SPRITESHEET.getIcon(0, 0, 0, 0));

        private final GuiIcon icon;

        MessageBoxIcon(GuiIcon icon) {
            this.icon = icon;
        }

        public GuiIcon getIcon() {
            return icon;
        }
    }

    public interface MessageBoxExecutor {
        void execute(Optional<SimpleGui> parent, String name, String title, String message, ClickedButton button, MessageBoxIcon icon);
    }
}
