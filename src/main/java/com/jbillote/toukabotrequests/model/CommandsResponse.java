package com.jbillote.toukabotrequests.model;

import java.util.List;

public class CommandsResponse {
    List<String> commands;

    public CommandsResponse() {}

    public CommandsResponse(List<String> commands) {
        this.commands = commands;
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
