package com.gmail.necnionch.myplugin.bukkitjs.bukkit;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.Script;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;


public class DynamicCommandManager {
    private static CommandMap commandMap;
    private static Map<String, Command> knownCommands;
    private final BukkitJSPlugin plugin;

    public DynamicCommandManager(BukkitJSPlugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("unchecked")
    public static void initReflections() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method mapMethod = Bukkit.getServer().getClass().getDeclaredMethod("getCommandMap");
        commandMap = (CommandMap) mapMethod.invoke(Bukkit.getServer());

        try {
            if (commandMap instanceof SimpleCommandMap) {
                SimpleCommandMap commandMap = (SimpleCommandMap) DynamicCommandManager.commandMap;

                Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
                knownCommandsField.setAccessible(true);
                knownCommands = (Map<String, Command>) knownCommandsField.get(commandMap);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


    public void register(Script script, Command command) {
        if (commandMap != null)
            commandMap.register(script.getName().toLowerCase(Locale.ROOT), command);
    }

    public void unregister(Script script, Command command) {

        if (commandMap != null) {
            command.unregister(commandMap);
        }

        if (knownCommands != null) {
            knownCommands.entrySet().removeIf(entry -> command.equals(entry.getValue()));
        }

    }




}
