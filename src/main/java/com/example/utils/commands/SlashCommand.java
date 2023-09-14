package com.example.utils.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.*;
import org.jetbrains.annotations.NotNull;

public abstract class SlashCommand {
    private final SlashCommandData commandData;
    private final SubcommandGroup[] subcommandGroups;
    private final Subcommand[] subcommands;
    private final OptionData[] options;

    public SlashCommand(@NotNull String name, @NotNull String description) {
        this.subcommandGroups = null;
        this.subcommands = null;
        this.options = null;

        commandData = Commands.slash(name, description);
    }
    public SlashCommand(@NotNull String name, @NotNull String description, @NotNull SubcommandGroup[] subcommandGroups) {
        this.subcommandGroups = subcommandGroups;
        this.subcommands = null;
        this.options = null;

        commandData = Commands.slash(name, description);

        for (SubcommandGroup subcommandGroup : subcommandGroups)
            commandData.addSubcommandGroups(subcommandGroup.getCommandData());
    }
    public SlashCommand(@NotNull String name, @NotNull String description, @NotNull Subcommand[] subcommands) {
        this.subcommandGroups = null;
        this.subcommands = subcommands;
        this.options = null;

        commandData = Commands.slash(name, description);

        for (Subcommand subcommand : subcommands)
            commandData.addSubcommands(subcommand.getCommandData());
    }
    public SlashCommand(@NotNull String name, @NotNull String description, @NotNull SubcommandGroup[] subcommandGroups, @NotNull Subcommand[] subcommands) {
        this.subcommandGroups = subcommandGroups;
        this.subcommands = subcommands;
        this.options = null;

        commandData = Commands.slash(name, description);

        for (SubcommandGroup subcommandGroup : subcommandGroups)
            commandData.addSubcommandGroups(subcommandGroup.getCommandData());
        for (Subcommand subcommand : subcommands)
            commandData.addSubcommands(subcommand.getCommandData());
    }

    public SlashCommand(@NotNull String name, @NotNull String description, @NotNull OptionData[] options) {
        this.subcommandGroups = null;
        this.subcommands = null;
        this.options = options;

        commandData = Commands.slash(name, description);

        commandData.addOptions(options);
    }
    public abstract void execute(SlashCommandInteractionEvent event);

    public void call(SlashCommandInteractionEvent event) {
        String subcommandGroupName = event.getSubcommandGroup();
        String subcommandName = event.getSubcommandName();

        if (subcommandGroupName == null && subcommandName == null) {
            execute(event);
            return;
        }

        if (subcommandGroupName != null) {
            for (SubcommandGroup subcommandGroup : subcommandGroups) {
                if (subcommandGroup.getCommandData().getName().equals(subcommandGroupName)) {
                    subcommandGroup.call(event);
                    return;
                }
            }
        }

        if (subcommandName != null) {
            for (Subcommand subcommand : subcommands) {
                if (subcommand.getCommandData().getName().equals(subcommandName)) {
                    subcommand.call(event);
                    return;
                }
            }
        }
        execute(event);
    }
    public SlashCommandData getCommandData() {
        return commandData;
    }
}
