package com.gmail.necnionch.myplugin.bukkitjs.bukkit.api;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.BukkitJSPlugin;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.EventHandler;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.Script;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.ScriptTask;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.objects.annotations.Function;

import java.util.Collection;
import java.util.Locale;
import java.util.UUID;

public class ScriptAPI {

    private final BukkitJSPlugin plugin;
    private final Script script;
    private ScriptConfig config;

    public ScriptAPI(Script script, BukkitJSPlugin plugin) {
        this.script = script;
        this.plugin = plugin;

    }

    @Override
    public String toString() {
        return "Script BukkitJS{}";
    }


    public Server getServer() {
        return plugin.getServer();
    }


    public Collection<? extends Player> getPlayers() {
        return getServer().getOnlinePlayers();
    }

    public Player getPlayer(String name) {
        try {
            return getPlayer(UUID.fromString(name));
        } catch (IllegalArgumentException ignored) {
        }

        return getServer().getPlayer(name);
    }

    public Player getPlayer(UUID player) {
        return getServer().getPlayer(player);
    }

    public Plugin getPlugin(String plugin) {
        return getServer().getPluginManager().getPlugin(plugin);
    }

    public Plugin getOwner() {
        return plugin;
    }

    public void broadcast(String message) {
        broadcast(message, null);
    }

    public void broadcast(String message, String permission) {
        getPlayers().forEach(p -> {
            if (permission == null || p.hasPermission(permission))
                p.sendMessage(message);
        });
    }



    public void on(String eventName, boolean acceptCancelled, Object callable) {  // todo: EntityDamageByEntityEventの登録で、EntityDamageEventも呼ばれ二回実行される問題
        if (!(callable instanceof ScriptObjectMirror))
            throw new IllegalArgumentException("第三引数は関数でなければなりません");

        script.registerHandler(new EventHandler(script, eventName.toLowerCase(Locale.ROOT), event -> {
            ((ScriptObjectMirror) callable).call(null, event);
        }, acceptCancelled));
    }

    public void on(String eventName, Object callable) {
        if (!(callable instanceof ScriptObjectMirror))
            throw new IllegalArgumentException("第二引数は関数でなければなりません");
        on(eventName, false, callable);
    }

    public void onEvent(Class<? extends Event> eventClass, boolean acceptCancelled, Object callable) {
        if (!(callable instanceof ScriptObjectMirror))
            throw new IllegalArgumentException("第三引数は関数でなければなりません");

        script.registerHandler(eventClass, new EventHandler(script, "", event -> {
            ((ScriptObjectMirror) callable).call(null, event);
        }, acceptCancelled));
    }

    public void onEvent(Class<? extends Event> eventClass, Object callable) {
        if (!(callable instanceof ScriptObjectMirror))
            throw new IllegalArgumentException("第二引数は関数でなければなりません");
        onEvent(eventClass, false, callable);

    }

    @Function
    public ScriptConfig getConfig() {
        if (config == null)
            config = new ScriptConfig(plugin.getScriptsDirectory(), script);
        return config;
    }



    public ScriptTask scheduleTask(long delay, ScriptTask.Handler handler) {
        return script.scheduleDelayedTask(handler, delay);
    }

    public ScriptTask scheduleLoopTask(long period, ScriptTask.Handler handler) {
        return script.scheduleRepeatingTask(handler, period);
    }



    public void command(String name, String permission, Object callable) {  // todo: permission is not working
        if (!(callable instanceof ScriptObjectMirror))
            throw new IllegalArgumentException("第三引数は関数でなければなりません");
        Command command = new Command(name) {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                try {
                    ((ScriptObjectMirror) callable).call(null, sender, args);
                } catch (Throwable e) {
                    e.printStackTrace();
                    throw e;
                }
                return true;
            }

            @Nullable
            @Override
            public String getPermission() {
                return permission;
            }
        };
        script.registerCommand(command);
    }

    public void command(String name, Object callable) {
        if (!(callable instanceof ScriptObjectMirror))
            throw new IllegalArgumentException("第二引数は関数でなければなりません");
        command(name, null, callable);
    }






    public static String parseColorCode(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }




}
