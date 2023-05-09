package com.gmail.necnionch.myplugin.bukkitjs.bukkit.script;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.BukkitJSPlugin;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.Bukkit;



public class ScriptTask {
    public static final Multimap<Script, ScriptTask> TASKS = ArrayListMultimap.create();
    private final Handler handler;
    private final Script script;
    private int taskId = -1;
    private boolean repeating;

    public ScriptTask(Script script, ScriptTask.Handler handler) {
        this.script = script;
        this.handler = handler;
    }


    private void run() {
        try {
            handler.run(this);
        } catch (Throwable e) {
            script.getLogger().severe("Failed to schedule task process (Script: " + script.getName() + ")");
            e.printStackTrace();
            cancel();
        }
        if (!repeating)
            TASKS.remove(script, this);
    }

    public void cancel() {
        if (taskId != -1)
            Bukkit.getScheduler().cancelTask(taskId);
        taskId = -1;
        TASKS.remove(script, this);
    }

    public int getTaskId() {
        return taskId;
    }


    public void scheduleDelayedTask(BukkitJSPlugin owner, long delay) {
        if (taskId != -1)
            throw new IllegalStateException("Already running task");
        TASKS.put(script, this);
        repeating = false;
        taskId = owner.getServer().getScheduler().scheduleSyncDelayedTask(owner, this::run, delay);
    }

    public void scheduleRepeatingTask(BukkitJSPlugin owner, long period) {
        if (taskId != -1)
            throw new IllegalStateException("Already running task");
        TASKS.put(script, this);
        repeating = true;
        taskId = owner.getServer().getScheduler().scheduleSyncRepeatingTask(owner, this::run, 0, period);
    }


    public interface Handler {
        void run(ScriptTask task);
    }


}
