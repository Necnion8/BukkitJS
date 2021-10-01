package com.gmail.necnionch.myplugin.bukkitjs.bukkit.api;

import com.gmail.necnionch.myplugin.bukkitjs.bukkit.script.Script;
import com.google.common.base.Charsets;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ScriptConfig {
    private YamlConfiguration config = new YamlConfiguration();
    private final Script script;
    private final File file;

    public ScriptConfig(File parent, Script script) {
        this.script = script;
        this.file = new File(parent, script.getName() + ".yml");
    }

    public File getFile() {
        return file;
    }

    public boolean load() {
        if (file.exists()) {
            try (InputStreamReader stream = new InputStreamReader(new FileInputStream(file), Charsets.UTF_8)) {
                config = YamlConfiguration.loadConfiguration(stream);

            } catch (Throwable e) {
                script.getLogger().severe("Failed to load " + script.getName() + " script configuration: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    public boolean save() {
        try (OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8)) {
            stream.write(config.saveToString());

        } catch (Throwable e) {
            script.getLogger().severe("Failed to save " + script.getName() + " script configuration: " + e.getMessage());
            return false;
        }
        return true;
    }


    public String getString(String key) {
        return config.getString(key);
    }

    public String getString(String key, String def) {
        return config.getString(key, def);
    }

    public int getInt(String key) {
        return config.getInt(key);
    }

    public int getInt(String key, int def) {
        return config.getInt(key, def);
    }

    public boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    public boolean getBoolean(String key, boolean def) {
        return config.getBoolean(key, def);
    }

    public double getDouble(String key) {
        return config.getDouble(key);
    }

    public double getDouble(String key, double def) {
        return config.getDouble(key, def);
    }

    public Object get(String key) {
        return config.get(key);
    }

    public Object get(String key, Object def) {
        return config.get(key, def);
    }

    public void set(String key, Object obj) {
        config.set(key, obj);
    }

}
