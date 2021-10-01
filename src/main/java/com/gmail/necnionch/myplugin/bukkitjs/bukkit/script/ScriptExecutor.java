package com.gmail.necnionch.myplugin.bukkitjs.bukkit.script;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface ScriptExecutor {
    Object execute(ScriptEngine engine) throws ScriptException, IOException;


    static ScriptExecutor fromFile(Path scriptPath) {
        return (engine) -> engine.eval(Files.newBufferedReader(scriptPath));
    }

    static ScriptExecutor fromCode(String scriptCode) {
        return (engine -> engine.eval(scriptCode));
    }

}
