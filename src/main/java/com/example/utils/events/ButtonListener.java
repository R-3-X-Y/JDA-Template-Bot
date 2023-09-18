package com.example.utils.events;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ButtonListener extends ListenerAdapter {
    private final Map<String, Consumer<ButtonInteraction>> buttons = new HashMap<>();

    public void addButton(String id, Consumer<ButtonInteraction> executor) {
        if (buttons.containsKey(id)) {
            System.err.println("[ButtonListener]: Attempting to add button '{" + id + "}', but a button already exists with that id!");
            return;
        }
        buttons.put(id, executor);
    }

    public void addButton(Button button, Consumer<ButtonInteraction> executor) {
        if (buttons.containsKey(button.getId())) {
            System.err.println("[ButtonListener]: Attempting to add button '{" + button.getId() + "}', but a button already exists with that id!");
            return;
        }
        buttons.put(button.getId(), executor);
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        String id = event.getButton().getId();
        Consumer<ButtonInteraction> executor = buttons.get(id);
        if (executor != null) {
            executor.accept(event.getInteraction());
            return;
        }
        System.out.println("[ButtonListener]: Unable to find button with id:'" + id + "'");
    }
}
