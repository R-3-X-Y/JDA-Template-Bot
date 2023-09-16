package com.example.utils.events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ButtonListener extends ListenerAdapter {
    private final Map<String,Consumer<ButtonInteraction>> buttons = new HashMap<>();

    public void addButton(String id, Consumer<ButtonInteraction> executor) {
        if (buttons.containsKey(id)) {
            System.err.println("[ButtonListener]: Attempting to add button '{" + id + "}', but a button already exists with that id!");
            return;
        }
        buttons.put(id, executor);
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        System.out.println("A");
        String id = event.getButton().getId();
        Consumer<ButtonInteraction> executor = buttons.get(id);
        if (executor != null) {
            System.out.println("B");
            executor.accept(event.getInteraction());
        }
    }
}
