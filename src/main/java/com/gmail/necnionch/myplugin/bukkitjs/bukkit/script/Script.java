package com.gmail.necnionch.myplugin.bukkitjs.bukkit.script;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.BukkitJSPlugin;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.DynamicCommandManager;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.EventManager;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.api.ScriptAPI;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.api.ScriptLogger;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.bukkit.command.Command;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Script {
    private final ScriptEngine engine;
    private final ScriptLogger logger;
    private final String name;
    private final BukkitJSPlugin owner;
    private final ScriptAPI api;
    private final EventManager eventManager;
    private final File scriptFile;
    private final DynamicCommandManager commandManager;
    private final Set<Command> commands = Sets.newHashSet();
    private final Set<Listener> eventClassListeners = Sets.newHashSet();

    public Script(BukkitJSPlugin owner, EventManager eventManager, DynamicCommandManager commandManager, ScriptEngine engine, String name, File scriptFile) {
        this.owner = owner;
        this.eventManager = eventManager;
        this.commandManager = commandManager;
        this.engine = engine;
        this.name = name;
        this.scriptFile = scriptFile;
        this.logger = new ScriptLogger(name);
        this.api = new ScriptAPI(this, owner);
        putVariables();
    }

    public ScriptEngine getEngine() {
        return engine;
    }

    public ScriptLogger getLogger() {
        return logger;
    }

    public String getName() {
        return name;
    }

    public File getScriptFile() {
        return scriptFile;
    }

    public ScriptAPI getAPI() {
        return api;
    }


    public void putVariables() {
        engine.put("log", logger);
        engine.put("plugin", owner);
        engine.put("bjs", api);
    }


    public Object execute(ScriptExecutor executor) throws ScriptException, IOException {
        logger.info("Loading " + name + " script");
        return executor.execute(engine);
    }



    public void registerHandler(EventHandler handler) {
        eventManager.addHandler(handler);
    }

    public void registerHandler(Class<? extends Event> eventClass, EventHandler handler) {
        Listener listener = new Listener() {};

        owner.getServer().getPluginManager().registerEvent(eventClass, listener, EventPriority.NORMAL, (l, event) -> {
            if (event.getClass().equals(eventClass)) {
                try {
                    handler.getHandler().onEvent(event);
                } catch (Throwable e) {
                    handler.getScript().getLogger().severe("Failed to event handle (Script: " + getName() + ", Event: " + event.getEventName() + ")");
                    e.printStackTrace();
                }
            }
        }, owner, !handler.isAcceptCancelled());

        eventClassListeners.add(listener);
    }


    public void registerCommand(Command command) {
        commandManager.register(this, command);
        commands.add(command);
    }

    public void unregisterCommand(Command command) {
        commandManager.unregister(this, command);
        commands.remove(command);
    }



    public ScriptTask scheduleDelayedTask(ScriptTask.Handler handler, long delay) {
        ScriptTask task = new ScriptTask(this, handler);
        task.scheduleDelayedTask(owner, delay);
        return task;
    }

    public ScriptTask scheduleRepeatingTask(ScriptTask.Handler handler, long period) {
        ScriptTask task = new ScriptTask(this, handler);
        task.scheduleRepeatingTask(owner, period);
        return task;
    }



    public void unload() {
        logger.info("Unloading " + name + " script");


        eventClassListeners.forEach(HandlerList::unregisterAll);
        eventManager.removeHandler(this);
        for (ScriptTask task : Sets.newHashSet(ScriptTask.TASKS.get(this))) {
            task.cancel();
        }

        commands.forEach(c -> commandManager.unregister(this, c));
        commands.clear();

        if (engine.get("onUnload") != null) {
            try {
                ((Invocable) engine).invokeFunction("onUnload");
            } catch (ScriptException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

    }


}
