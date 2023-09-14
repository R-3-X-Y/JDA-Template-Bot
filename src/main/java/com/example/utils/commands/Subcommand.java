package com.example.utils.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.*;
import org.jetbrains.annotations.NotNull;

public abstract class Subcommand {
    private final SubcommandData commandData;
    private final OptionData[] options;
    public Subcommand(@NotNull String name, @NotNull String description) {
        this.options = null;

        commandData = new SubcommandData(name, description);
    }
    public Subcommand(@NotNull String name, @NotNull String description, @NotNull OptionData[] options) {
        this.options = options;

        commandData = new SubcommandData(name, description);

        commandData.addOptions(options);
    }
    public abstract void execute(SlashCommandInteractionEvent event);
    public void call(SlashCommandInteractionEvent event) {
        execute(event);
    }
    public SubcommandData getCommandData() {
        return commandData;
    }
}
