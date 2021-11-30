package com.gmail.necnionch.myplugin.bukkitjs.bukkit.script;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.BukkitJSPlugin;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.DynamicCommandManager;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.EventManager;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.api.ScriptAPI;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.api.ScriptLogger;
import com.google.common.collect.Sets;
import org.bukkit.command.Command;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

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


    @SuppressWarnings("unchecked")
    public static  <T> T objectTo(@NotNull Class<T> clazz, Object object) throws ClassCastException {
        if (object == null)
            return null;

        // fix numbers
        if (clazz.equals(Integer.class)) {
            return (T) ((Integer) ((Number) object).intValue());
        } else if (clazz.equals(Double.class)) {
            return (T) ((Double) ((Number) object).doubleValue());
        } else if (clazz.equals(Long.class)) {
            return (T) ((Long) ((Number) object).longValue());
        } else if (clazz.equals(Float.class)) {
            return (T) ((Float) ((Number) object).floatValue());
        } else if (clazz.equals(Short.class)) {
            return (T) ((Short) ((Number) object).shortValue());
        }

        return clazz.cast(object);
    }

    private <T> T getAttrClass(String key, T def, @NotNull Class<T> castTo) {
        Object obj = getAttr(key, castTo);
        if (obj == null)
            return def;
        try {
            T ret = objectTo(castTo, obj);
            return (ret != null) ? ret : def;
        } catch (ClassCastException e) {
            return def;
        }
    }

    public <T> T getAttr(String key, @NotNull Class<T> castTo) {
        return objectTo(castTo, engine.get(key));
    }

    public Object invokeFunction(String funcName, Object... args) throws ScriptException, NoSuchMethodException, UnsupportedOperationException {
        if (!(engine instanceof Invocable))
            return new UnsupportedOperationException("engine has not invocable");

        return ((Invocable) engine).invokeFunction(funcName, args);
    }

    public <T> T invokeFunction(@NotNull Class<T> returnType, T def, String funcName, Object... args) throws NoSuchMethodException {
        T ret = def;
        try {
            ret = objectTo(returnType, invokeFunction(funcName, args));
            if (ret == null)
                ret = def;
        } catch (ClassCastException e) {
            logger.warning("CastError script=" + name + ", func=" + funcName);
        } catch (NoSuchMethodException e) {
            throw e;
        } catch (ScriptException e) {
            logger.warning("ScriptError script=" + name + ", func=" + funcName);
            logger.warning(">> " + e.getMessage());

            Throwable cause = e.getCause();
            while (cause != null) {
                logger.warning(">> " + e.getCause().getMessage());
                cause = cause.getCause();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return ret;
    }




}
