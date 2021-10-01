package com.gmail.necnionch.myplugin.bukkitjs.bukkit.script;

import org.bukkit.event.Event;

public class EventHandler {

    private final Script script;
    private final String eventName;
    private final Handler handler;
    private final boolean acceptCancelled;

    public EventHandler(Script script, String eventName, Handler handler, boolean acceptCancelled) {
        this.script = script;
        this.eventName = eventName;
        this.handler = handler;
        this.acceptCancelled = acceptCancelled;
    }

    public Script getScript() {
        return script;
    }

    public String getEventName() {
        return eventName;
    }

    public Handler getHandler() {
        return handler;
    }

    public boolean isAcceptCancelled() {
        return acceptCancelled;
    }

    public interface Handler {
        void onEvent(Event event);

    }
}
