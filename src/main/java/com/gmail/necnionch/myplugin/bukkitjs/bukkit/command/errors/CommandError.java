package com.gmail.necnionch.myplugin.bukkitjs.bukkit.command.errors;

import org.jetbrains.annotations.NotNull;

public abstract class CommandError extends Error {
    public abstract @NotNull String getMessage();
}
