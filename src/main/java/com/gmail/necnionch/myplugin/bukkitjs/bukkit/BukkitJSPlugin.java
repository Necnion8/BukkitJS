package com.gmail.necnionch.myplugin.bukkitjs.bukkit;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.CommandBukkit;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.events.ScriptLoadEvent;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.events.ScriptUnloadEvent;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.Script;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.ScriptExecutor;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class BukkitJSPlugin extends JavaPlugin implements BukkitJS {
    static BukkitJSPlugin instance;
    private final MainCommand mainCommand = new MainCommand(this);
    private final ScriptEngineManager manager = new ScriptEngineManager();
    private final EventManager eventManager = new EventManager(this);
    private final DynamicCommandManager commandManager = new DynamicCommandManager(this);
    private final Map<String, Script> scripts = Maps.newHashMap();
    private final Set<Script> loadedScripts = Sets.newHashSet();
    private final File scriptsDirectory = new File(getDataFolder(), "scripts");

    /**
     * ScriptObjectMirror.call() を使いたい！！でも org.openjdk (Java16) にしかない！
     * ダメだ！！仕方がない！ Java16以下(Minecraft 1.16)との互換性は諦めよう！！\\UWAAAA!//
     */

    @Override
    public void onLoad() {
        try {
            DynamicCommandManager.initReflections();
        } catch (Throwable e) {
            e.printStackTrace();
            getLogger().warning("Failed to initialize CommandMap reflection. Disabled script commands.");
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        //noinspection ConstantConditions
        CommandBukkit.register(mainCommand, getCommand("bukkitjavascript"));

        getLogger().info("initialing ScriptEngine");
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        getScriptEngine();

        eventManager.register();
        loadScriptAll();
    }

    @Override
    public void onDisable() {
        unloadScriptAll();
        eventManager.unregister();
    }


    public static BukkitJS getAPI() {
        return Objects.requireNonNull(instance, "Plugin is not enabled");
    }

    public File getScriptsDirectory() {
        return scriptsDirectory;
    }

    // loading

    public void unloadScriptAll() {
        for (Script script : Sets.newHashSet(loadedScripts)) {
            try {
                unloadScript(script);
            } catch (Throwable e) {
                getLogger().warning("Unloading script fail: " + script.getName());
                e.printStackTrace();
            }
        }
        loadedScripts.clear();
        scripts.clear();
    }

    public void loadScriptAll() {
        unloadScriptAll();

        //noinspection ResultOfMethodCallIgnored
        scriptsDirectory.mkdirs();

        File[] files = scriptsDirectory.listFiles();
        Pattern filter = Pattern.compile("(.+)\\.js$");
        Script script;

        if (files != null)
            for (File child : files) {
                Matcher m = filter.matcher(child.getName());
                if (!m.find() || child.getName().startsWith("-"))
                    continue;

                String scriptName = m.group(1);
                if (scripts.containsKey(scriptName))
                    continue;

                script = new Script(this, eventManager, commandManager, getScriptEngine(), scriptName, child);
                scripts.put(scriptName, script);
            }

        if (!scripts.isEmpty())
            scripts.values().forEach(s -> {
                try {
                    loadScript(s);
                } catch (ScriptException e) {
                    s.getLogger().severe("Failed to load: " + e.getMessage());
                }
            });

        getLogger().info("" + loadedScripts.size() + " scripts loaded!");
    }

    public String[] getDisabledScript() {
        List<String> disabled = Lists.newArrayList();
        File[] files = scriptsDirectory.listFiles();
        Pattern filter = Pattern.compile("^-(.+)\\.js$");

        if (files != null)
            for (File child : files) {
                Matcher m = filter.matcher(child.getName());
                if (!m.find())
                    continue;

                disabled.add(m.group(1));
            }

        return disabled.toArray(new String[0]);
    }


    // api

    @Override
    public ScriptEngine getScriptEngine() {
        ScriptEngine engine = manager.getEngineByName("nashorn");
        if (engine == null)  // The runtime engine is unavailable. Use the built-in engine instead. (Java16?)
            engine = manager.getEngineByExtension("internal.nashorn");

        if (engine == null) {
            manager.registerEngineExtension("internal.nashorn", new NashornScriptEngineFactory());
            engine = manager.getEngineByExtension("internal.nashorn");
        }

        if (engine == null)
            throw new UnsupportedOperationException("Engine has not available");

        return engine;
    }

    @Override
    public Script[] getScripts() {
        return scripts.values().toArray(new Script[0]);
    }

    @Override
    public Script getScript(String name) {
        return scripts.get(name);
    }

    @Override
    public boolean isLoaded(Script script) {
        return loadedScripts.contains(script);
    }

    @Override
    public void loadScript(Script script) throws ScriptException {
        if (isLoaded(script))
            throw new IllegalStateException("Already loaded");
        if (!scripts.containsValue(script))
            throw new IllegalArgumentException("Unknown script");

        boolean loaded = false;
        try {
            script.execute(
                    ScriptExecutor.fromFile(
                            Objects.requireNonNull(script.getScriptFile()).toPath()
                    )
            );
            loaded = true;

        } catch (ScriptException e) {
            throw e;
        } catch (Exception e) {
            throw new ScriptException(e);
        } catch (Throwable e) {
            throw new ScriptException(e.getMessage());
        } finally {
            if (!loaded)
                try {
                    script.unload();
                } catch (Throwable ignored) {}

        }

        loadedScripts.add(script);

        getServer().getPluginManager()
                .callEvent(new ScriptLoadEvent(script));
    }

    @Override
    public void unloadScript(Script script) {
        if (!isLoaded(script))
            throw new IllegalStateException("Already unloaded");

        if (!scripts.containsValue(script))
            throw new IllegalArgumentException("Unknown script");

        if (isEnabled()) {
            getServer().getPluginManager()
                    .callEvent(new ScriptUnloadEvent(script));
        }

        try {
            script.unload();
        } finally {
            loadedScripts.remove(script);
        }
    }

    @Override
    public Script enableScript(String name) throws ScriptException {
        File target = new File(scriptsDirectory, "-" + name + ".js");
        if (!target.isFile())
            throw new IllegalArgumentException("not exist file: " + target.getName());

        String renamed = target.getName().replaceAll("^-", "");
        File newFile = new File(target.getParentFile() + "/" + renamed);
        if (!target.renameTo(newFile))
            return null;

        return loadScript(newFile);
    }

    @Override
    public Script loadScript(File file) throws ScriptException {
        return loadScript(file, null);
    }

    @Override
    public Script loadScript(File file, @Nullable Consumer<Script> preload) throws ScriptException {
        Pattern filter = Pattern.compile("(.+)\\.js$");

        Matcher m = filter.matcher(file.getName());
        if (!m.find())
            return null;

        String scriptName = m.group(1);
        if (scripts.containsKey(scriptName))
            throw new IllegalArgumentException("Already exists script name: " + scriptName);

        ScriptEngine scriptEngine = getScriptEngine();
        Script script = new Script(this, eventManager, commandManager, scriptEngine, scriptName, file);
        scripts.put(scriptName, script);

        if (preload != null)
            preload.accept(script);

        loadScript(script);
        return script;
    }

    @Override
    public boolean disableScript(Script script) {
        File file = script.getScriptFile();
        if (file == null)
            throw new UnsupportedOperationException();

        if (isLoaded(script))
            unloadScript(script);

        scripts.remove(script.getName(), script);

        File newFile = new File(file.getParentFile() + "/-" + file.getName());
        return file.renameTo(newFile);
    }

    @Override
    public Object execute(ScriptExecutor executor) throws ScriptException, IOException {
        Script script = new Script(this, eventManager, commandManager, getScriptEngine(), "SimpleExecuteDebug", null);

        try {
            return script.execute(executor);

        } finally {
            script.unload();
        }

    }

    @Override
    public <T> T execute(File scriptFile, @Nullable Function<Object, T> parser, @Nullable Consumer<Script> preload) throws ScriptException, IOException {
        Pattern filter = Pattern.compile("(.+)\\.js$");

        String scriptName = scriptFile.getName();
        Matcher m = filter.matcher(scriptFile.getName());
        if (m.find())
            scriptName = m.group(1);

        Script script = new Script(this, eventManager, commandManager, getScriptEngine(), scriptName, scriptFile);

        if (preload != null)
            preload.accept(script);

        try {
            Object ret = script.execute(ScriptExecutor.fromFile(scriptFile.toPath()));
            if (parser != null)
                return parser.apply(ret);
            //noinspection unchecked
            return (T) ret;

        } finally {
            script.unload();
        }
    }

}
