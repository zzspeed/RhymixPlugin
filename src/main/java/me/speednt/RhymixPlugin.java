package me.speednt;

import org.bukkit.plugin.java.JavaPlugin;

public class RhymixPlugin extends JavaPlugin {

    private static RhymixPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        new CommandManager(this);

        getLogger().info("RhymixPlugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("RhymixPlugin disabled.");
    }

    public static RhymixPlugin get() {
        return instance;
    }
}