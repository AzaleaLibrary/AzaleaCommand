package net.azalealibrary.command;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

import java.io.File;

@SuppressWarnings("unused")
@Plugin(name = "AzaleaCommand", version = "1.0")
public final class AzaleaCommand extends JavaPlugin {

    public AzaleaCommand() { }

    public AzaleaCommand(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onLoad() {
        CommandNode.register(this, ConfigureCommand.class);
    }

    @Override
    public void onEnable() { }

    @Override
    public void onDisable() { }
}
