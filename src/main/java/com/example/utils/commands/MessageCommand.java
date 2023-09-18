package com.example.utils.commands;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.context.MessageContextInteraction;
import org.jetbrains.annotations.NotNull;

public abstract class MessageCommand {
    private final CommandData commandData;

    public MessageCommand(@NotNull String name) {
        commandData = Commands.user(name);
    }

    public void call(MessageContextInteraction interaction) {
        execute(interaction);
    }

    public abstract void execute(MessageContextInteraction interaction);

    public CommandData getCommandData() {
        return commandData;
    }
}
