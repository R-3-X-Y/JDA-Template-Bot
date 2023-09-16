package com.example.utils.commands;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.context.UserContextInteraction;
import org.jetbrains.annotations.NotNull;

public abstract class UserCommand {
    private final CommandData commandData;
    public UserCommand(@NotNull String name) {
        commandData = Commands.user(name);
    }
    public void call(UserContextInteraction interaction) {
        execute(interaction);
    }
    public abstract void execute(UserContextInteraction interaction);
    public CommandData getCommandData() {
        return commandData;
    }
}
