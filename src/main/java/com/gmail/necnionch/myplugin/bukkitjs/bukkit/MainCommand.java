package com.gmail.necnionch.myplugin.bukkitjs.bukkit;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.Command;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.CommandSender;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.RootCommand;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.errors.CommandError;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.errors.InternalCommandError;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.errors.NotFoundCommandError;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.errors.PermissionCommandError;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.Script;
import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.ScriptExecutor;
import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.script.ScriptException;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


public class MainCommand extends RootCommand {
    private final BukkitJSPlugin plugin;


    public MainCommand(BukkitJSPlugin plugin) {
        this.plugin = plugin;

        addCommand("reload", null, this::execReload, this::loadedScripts);
        addCommand("load", null, this::execLoad, this::genLoads);
        addCommand("unload", null, this::execUnload, this::loadedScripts);
        addCommand("list", null, this::execList, (s, c, a) -> Collections.emptyList());
        addCommand("debug", null, this::execDebug, (s, c, a) -> Collections.emptyList());

        addCommand("enable", null, this::execEnable, this::genDisables);
        addCommand("disable", null, this::execDisable, this::loadedScripts);
    }


    private @NotNull List<String> loadedScripts(CommandSender s, String c, List<String> args) {
        if (checkPermission(s) && args.size() == 1) {
            return generateSuggests(args.get(0), Stream.of(plugin.getScripts())
                    .filter(plugin::isLoaded)
                    .map(Script::getName)
                    .toArray(String[]::new));
        }
        return Collections.emptyList();
    }

    private @NotNull List<String> genLoads(CommandSender s, String c, List<String> args) {
        if (checkPermission(s) && args.size() == 1) {
            return generateSuggests(args.get(0), Stream.of(plugin.getScripts())
                    .filter(script -> !plugin.isLoaded(script))
                    .map(Script::getName)
                    .toArray(String[]::new));
        }
        return Collections.emptyList();
    }

    private @NotNull List<String> genDisables(CommandSender s, String c, List<String> args) {
        if (checkPermission(s) && args.size() == 1)
            return generateSuggests(args.get(0), plugin.getDisabledScript());
        return Collections.emptyList();
    }


    private void execReload(CommandSender sender, List<String> args) {
        checkPermissionDeniedThrow(sender);

        String name = parseFirst(args);

        Script script = plugin.getScript(name);
        if (script == null) {
            sender.sendMessage(new ComponentBuilder("そのスクリプトはロードされていません").color(ChatColor.RED).create());
            return;
        }

        if (plugin.isLoaded(script))
            plugin.unloadScript(script);

        try {
            plugin.loadScript(script);

        } catch (ScriptException e) {
            sender.sendMessage(new ComponentBuilder("ロードエラー: \n" + e.getMessage()).color(ChatColor.RED).create());
            return;
        }

        sender.sendMessage(new ComponentBuilder(script.getName() + " をリロードしました").color(ChatColor.GREEN).create());

    }

    private void execLoad(CommandSender sender, List<String> args) {
        checkPermissionDeniedThrow(sender);

        String name = parseFirst(args);

        Script script = plugin.getScript(name);
        if (script == null) {
            File file = new File(plugin.getScriptsDirectory(), name + ".js");
            if (!file.isFile()) {
                sender.sendMessage(new ComponentBuilder("そのスクリプトはロードされていません").color(ChatColor.RED).create());
                return;
            }
            try {
                script = plugin.loadScript(file);

            } catch (ScriptException e) {
                sender.sendMessage(new ComponentBuilder("ロードエラー: \n" + e.getMessage()).color(ChatColor.RED).create());
                return;
            }
            sender.sendMessage(new ComponentBuilder(script.getName() + " をロードしました").color(ChatColor.GREEN).create());
            return;
        }

        if (plugin.isLoaded(script)) {
            sender.sendMessage(new ComponentBuilder("そのスクリプトは既にロードされています").color(ChatColor.RED).create());

        } else {
            try {
                plugin.loadScript(script);

            } catch (ScriptException e) {
                sender.sendMessage(new ComponentBuilder("ロードエラー: \n" + e.getMessage()).color(ChatColor.RED).create());
                return;
            }

            sender.sendMessage(new ComponentBuilder(script.getName() + " をロードしました").color(ChatColor.GREEN).create());
        }
    }

    private void execUnload(CommandSender sender, List<String> args) {
        checkPermissionDeniedThrow(sender);

        String name = parseFirst(args);

        Script script = plugin.getScript(name);
        if (script == null || !plugin.isLoaded(script)) {
            sender.sendMessage(new ComponentBuilder("そのスクリプトはロードされていません").color(ChatColor.RED).create());
            return;
        }

        plugin.unloadScript(script);
        sender.sendMessage(new ComponentBuilder(script.getName() + " をアンロードしました").color(ChatColor.GREEN).create());

    }

    private void execList(CommandSender sender, List<String> args) {
        checkPermissionDeniedThrow(sender);

        ComponentBuilder builder = new ComponentBuilder("Scripts: ").color(ChatColor.WHITE);

        List<String> l = Lists.newArrayList();

        for (Script script : plugin.getScripts()) {
            l.add("" + (plugin.isLoaded(script) ? ChatColor.GREEN : ChatColor.WHITE) + script.getName());
        }

        for (String name : plugin.getDisabledScript()) {
            l.add(ChatColor.GRAY + name);
        }

        builder.appendLegacy(String.join(ChatColor.GRAY + ", ", l));
        sender.sendMessage(builder.create());
    }

    private void execEnable(CommandSender sender, List<String> args) {
        checkPermissionDeniedThrow(sender);

        String name = parseFirst(args);

        if (!Arrays.asList(plugin.getDisabledScript()).contains(name)) {
            sender.sendMessage(new ComponentBuilder("そのスクリプトファイルはありません").color(ChatColor.RED).create());
            return;
        }

        Script script;
        try {
            script = plugin.enableScript(name);

        } catch (ScriptException e) {
            sender.sendMessage(new ComponentBuilder("ロードエラー: \n" + e.getMessage()).color(ChatColor.RED).create());
            return;
        }

        if (script != null) {
            sender.sendMessage(new ComponentBuilder(script.getName() + " を有効化しました").color(ChatColor.GREEN).create());
        } else {
            sender.sendMessage(new ComponentBuilder("有効化できませんでした").color(ChatColor.RED).create());
        }
    }

    private void execDisable(CommandSender sender, List<String> args) {
        checkPermissionDeniedThrow(sender);

        String name = parseFirst(args);

        Script script = plugin.getScript(name);
        if (script == null) {
            sender.sendMessage(new ComponentBuilder("そのスクリプトはロードされていません").color(ChatColor.RED).create());
            return;
        }

        if (plugin.disableScript(script)) {
            sender.sendMessage(new ComponentBuilder(script.getName() + " を無効化しました").color(ChatColor.GREEN).create());
        } else {
            sender.sendMessage(new ComponentBuilder("無効化できませんでした").color(ChatColor.RED).create());
        }
    }


    private void execDebug(CommandSender s, List<String> args) {
        checkPermissionDeniedThrow(s);

        if (args.isEmpty()) {
            s.sendMessage(new ComponentBuilder("コードを入力してください").color(ChatColor.RED).create());
            return;
        }

        Object result;
        try {
            result = plugin.execute(ScriptExecutor.fromCode(String.join(" ", args)));

        } catch (Throwable e) {
            s.sendMessage(new ComponentBuilder("実行エラー: \n").color(ChatColor.RED)
                    .append(e.getMessage()).color(ChatColor.WHITE)
                    .create());
            return;
        }

        s.sendMessage(new ComponentBuilder("実行結果: ").color(ChatColor.YELLOW)
                .append(String.valueOf(result)).color(ChatColor.WHITE)
                .create());
    }


    @Override
    public void onError(@NotNull CommandSender sender, @Nullable Command command, @NotNull CommandError error) {
        String message;
        if (error instanceof PermissionCommandError) {
            message = "権限がありません";
        } else if (error instanceof NotFoundCommandError) {
            message = "不明なコマンドです";
        } else if (error instanceof InternalCommandError) {
            message = "内部エラーが発生しました";
        } else {
            super.onError(sender, command, error);
            return;
        }
        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + message));
    }


    private String parseFirst(List<String> args) {
        if (args.isEmpty())
            throw new CommandError() {
                @Override
                public @NotNull String getMessage() {
                    return "スクリプト名を入力してください";
                }
            };
        return args.get(0);
    }


    private void checkPermissionDeniedThrow(CommandSender sender) {
        if (!(sender instanceof ConsoleCommandSender))
            if (!sender.hasPermission(BukkitJSPlugin.COMMAND_PERMISSION.getName()))
                throw new PermissionCommandError();
    }

    private boolean checkPermission(CommandSender sender) {
        return (sender instanceof ConsoleCommandSender) || sender.hasPermission(BukkitJSPlugin.COMMAND_PERMISSION.getName());
    }


}
