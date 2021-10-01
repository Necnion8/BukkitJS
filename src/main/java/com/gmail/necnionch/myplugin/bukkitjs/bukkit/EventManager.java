package com.gmail.necnionch.myplugin.bukkitjs.bukkit;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.EventHandler;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.Script;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.event.*;
import org.bukkit.plugin.RegisteredListener;

import java.util.Locale;


public class EventManager {
    private final BukkitJSPlugin owner;
    private final Multimap<String, EventHandler> handlers = ArrayListMultimap.create();
    private final MyEventListener listener = new MyEventListener(this);

    public EventManager(BukkitJSPlugin owner) {
        this.owner = owner;
    }


    public void register() {
        owner.getServer().getPluginManager().registerEvents(listener, owner);
    }

    public void unregister() {
    }



    public void handleEvent(Event event) {
        String eventName = event.getEventName().substring(0, event.getEventName().length() - 5).toLowerCase(Locale.ROOT);

        handlers.get(eventName).forEach(handle -> {
            if (event instanceof Cancellable && ((Cancellable) event).isCancelled() && !handle.isAcceptCancelled())
                return;

            try {
                handle.getHandler().onEvent(event);
            } catch (Throwable e) {
                handle.getScript().getLogger().severe("Failed to event handle (Script: " + handle.getScript().getName() + ", Event: " + eventName + ")");
                e.printStackTrace();
            }

        });
    }




    public void addHandler(EventHandler handler) {
        handlers.put(handler.getEventName(), handler);
    }

    public void removeHandler(Script script) {
        handlers.entries().removeIf(entry -> entry.getValue().getScript().equals(script));
    }



}
