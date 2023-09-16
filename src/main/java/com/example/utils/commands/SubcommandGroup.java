package com.example.utils.commands;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import org.jetbrains.annotations.NotNull;

public abstract class SubcommandGroup {
    private final SubcommandGroupData commandData;
    private final Subcommand[] subcommands;
    public SubcommandGroup(@NotNull String name, @NotNull String description, @NotNull Subcommand[] subcommands) {
        this.subcommands = subcommands;

        commandData = new SubcommandGroupData(name, description);

        for (Subcommand subcommand : subcommands)
            commandData.addSubcommands(subcommand.getCommandData());
    }
    public abstract void execute(SlashCommandInteraction interaction);
    public void call(SlashCommandInteraction interaction) {
        String subcommandName = interaction.getSubcommandName();

        if (subcommandName == null) {
            execute(interaction);
            return;
        }

        for (Subcommand subcommand : subcommands) {
            if (subcommand.getCommandData().getName().equals(subcommandName)) {
                subcommand.call(interaction);
                return;
            }
        }

        execute(interaction);
    }
    public SubcommandGroupData getCommandData() {
        return commandData;
    }
}
