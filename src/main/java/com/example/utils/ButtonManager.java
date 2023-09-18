package com.example.utils;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

public class ButtonManager {
    public static Button exampleButton = Button.of(ButtonStyle.PRIMARY, "example-button", "Example Button", Emoji.fromUnicode("\uD83D\uDE03"));
}
