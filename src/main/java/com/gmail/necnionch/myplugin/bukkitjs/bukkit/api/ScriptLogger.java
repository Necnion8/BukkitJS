package com.gmail.necnionch.myplugin.bukkitjs.bukkit.api;

import org.bukkit.Bukkit;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ScriptLogger extends Logger {
//    private final String prefix;

    public ScriptLogger(String scriptName) {
        super("BukkitJS:" + scriptName, null);
        setParent(Bukkit.getLogger());
//        prefix = "(" + scriptName + ") ";
    }

    @Override
    public void log(LogRecord record) {
//        record.setMessage(prefix + record.getMessage());
        super.log(record);
    }

}

