package com.gmail.necnionch.myplugin.bukkitjs.bukkit;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.Script;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.ScriptExecutor;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;


interface BukkitJS {
    Script[] getScripts();

    Script getScript(String name);

    boolean isLoaded(Script script);

    void loadScript(Script script) throws ScriptException;

    void unloadScript(Script script);

    ScriptEngine getScriptEngine();

    Script enableScript(String name) throws ScriptException;

    Script loadScript(File file) throws ScriptException;

    boolean disableScript(Script script);

    Object execute(ScriptExecutor executor) throws ScriptException, IOException;

}
