package com.almuradev.almurasdk.client.gui.dialog;

import com.almuradev.almurasdk.client.gui.SimpleGui;
import com.google.common.base.Optional;

public interface MessageBoxExecutor {
    MessageBoxExecutor NO_EXECUTION = new NullMessageBoxExecutor();

    void execute(Optional<SimpleGui> parent, String name, String title, String message, UIMessageBox.ClickedButton button,
            UIMessageBox.MessageBoxIcon icon);

    final class NullMessageBoxExecutor implements MessageBoxExecutor {

        @Override
        public void execute(Optional<SimpleGui> parent, String name, String title, String message, UIMessageBox.ClickedButton button,
                UIMessageBox.MessageBoxIcon icon) {
        }
    }
}
