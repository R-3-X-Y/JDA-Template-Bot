package com.example;

import com.example.utils.events.ButtonListener;
import com.example.utils.events.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {
    private static final String token = System.getenv("BotToken");
    public static JDA jda;
    private static final CommandListener commandListener = new CommandListener();
    private static final ButtonListener buttonListener = new ButtonListener();

    public static void main(String[] args) {
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.addEventListeners(commandListener, buttonListener);
        jda = jdaBuilder.build();
        jda.updateCommands().addCommands(commandListener.GetAllCommandData()).queue();
    }

    public static CommandListener getCommandListener() {
        return commandListener;
    }

    public static ButtonListener getButtonManager() {
        return buttonListener;
    }
}
