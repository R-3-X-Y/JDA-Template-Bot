package com.example.utils.events;

import com.example.utils.commands.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandListener extends ListenerAdapter {
    private final List<SlashCommand> slashCommands = new ArrayList<>();

    public void AddSlashCommand(SlashCommand slashCommand) {
        slashCommands.add(slashCommand);
    }

    public CommandListener() {

    }

    public List<CommandData> GetAllCommandData() {
        List<CommandData> commandData = new ArrayList<>();
        for (SlashCommand slashCommand : slashCommands) {
            commandData.add(slashCommand.getCommandData());
        }
        return commandData;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        String commandName = event.getName();
        for (SlashCommand slashCommand : slashCommands) {
            if (slashCommand.getCommandData().getName().equals(commandName)) {
                slashCommand.call(event);
                return;
            }
        }
    }
}
