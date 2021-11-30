package com.gmail.necnionch.myplugin.bukkitjs.bukkit.events;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.Script;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ScriptUnloadEvent extends Event {
    public static final HandlerList HANDLERS = new HandlerList();
    private final Script script;


    public ScriptUnloadEvent(Script script) {
        this.script = script;
    }

    public Script getScript() {
        return script;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
