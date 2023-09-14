package com.example;

import com.example.utils.events.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {
    private static final String token = "OTY3ODMwODM0OTk0MzYwNDEw.GcnkFz.A3H-F3-KMGpjUax1L6lReIGvRIL2n30xBRcgGQ";
    public static JDA jda;

    private static final CommandListener commandListener = new CommandListener();

    public static void main(String[] args) {
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.addEventListeners(commandListener);
        jda = jdaBuilder.build();
        jda.updateCommands().addCommands(commandListener.GetAllCommandData()).queue();
    }
}
