package org.kot.rsp.userinterface;

import org.kot.engine.userinterface.Command;

public enum RpsCommands implements Command {
    EXIT("exit"),
    HELP("help");

    private final String expectedInput;

    RpsCommands(String expectedInput) {
        this.expectedInput = expectedInput;
    }

    public String getExpectedInput() {
        return expectedInput;
    }
}