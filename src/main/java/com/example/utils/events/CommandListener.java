package com.example.utils.events;

import com.example.utils.commands.MessageCommand;
import com.example.utils.commands.SlashCommand;
import com.example.utils.commands.UserCommand;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandListener extends ListenerAdapter {
    private final List<SlashCommand> slashCommands = new ArrayList<>();
    private final List<MessageCommand> messageCommands = new ArrayList<>();
    private final List<UserCommand> userCommands = new ArrayList<>();

    public void addSlashCommand(SlashCommand slashCommand) {
        slashCommands.add(slashCommand);
    }

    public void addMessageCommand(MessageCommand messageCommand) {
        messageCommands.add(messageCommand);
    }

    public void addUserCommand(UserCommand userCommand) {
        userCommands.add(userCommand);
    }

    public CommandListener() {

    }

    public List<CommandData> GetAllCommandData() {
        List<CommandData> commandData = new ArrayList<>();
        for (SlashCommand slashCommand : slashCommands) {
            commandData.add(slashCommand.getCommandData());
        }
        for (MessageCommand messageCommand : messageCommands) {
            commandData.add(messageCommand.getCommandData());
        }
        for (UserCommand userCommand : userCommands) {
            commandData.add(userCommand.getCommandData());
        }
        return commandData;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        String commandName = event.getName();
        for (SlashCommand slashCommand : slashCommands) {
            if (slashCommand.getCommandData().getName().equals(commandName)) {
                slashCommand.call(event.getInteraction());
                return;
            }
        }
    }

    @Override
    public void onMessageContextInteraction(@NotNull MessageContextInteractionEvent event) {
        super.onMessageContextInteraction(event);
        String commandName = event.getName();
        for (MessageCommand messageCommand : messageCommands) {
            if (messageCommand.getCommandData().getName().equals(commandName)) {
                messageCommand.call(event.getInteraction());
                return;
            }
        }
    }

    @Override
    public void onUserContextInteraction(@NotNull UserContextInteractionEvent event) {
        super.onUserContextInteraction(event);
        String commandName = event.getName();
        for (UserCommand userCommand : userCommands) {
            if (userCommand.getCommandData().getName().equals(commandName)) {
                userCommand.call(event.getInteraction());
                return;
            }
        }
        System.err.println("[CommandListener]: Unable to find command with name'" + commandName + "'");
    }
}
