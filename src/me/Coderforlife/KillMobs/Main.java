package me.Coderforlife.KillMobs;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.Coderforlife.KillMobs.Commands.KillMobs;
import me.Coderforlife.KillMobs.Commands.KillZombies;
import me.Coderforlife.KillMobs.Events.PlayerQuit;
import me.Coderforlife.KillMobs.TabCommands.Tabs;

public class Main extends JavaPlugin {

	public File mobConfigFile;
	public FileConfiguration mobConfig;
	
	public static final String prefix = ChatColor.translateAlternateColorCodes('&', "&4&l[&6&lKillAllMobs&4&l]&r ");
	public static final String perm = "KAM.";
	public static final String header = ChatColor.translateAlternateColorCodes('&', 
			"&4&l██████&7&l[&6&l&oKILLALLMOBS&7&l]&4&l██████");
	public static final String dash = ChatColor.translateAlternateColorCodes('&', 
			"&7&o&l- ");

	@Override
	public void onEnable() {
		createCustomConfig();
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		this.getCommand("killzombies").setExecutor(new KillZombies(this));
		this.getCommand("killmobs").setExecutor(new KillMobs(this));
		this.getCommand("killmobs").setTabCompleter(new Tabs(this));

	}

	@Override
	public void onDisable() {

	}
	
	private void createCustomConfig() {
		mobConfigFile = new File(getDataFolder(), "config.yml");
		if (!mobConfigFile.exists()) {
			mobConfigFile.getParentFile().mkdir();

			saveResource("config.yml", false);
		}

		mobConfig = new YamlConfiguration();
		try {
			mobConfig.load(mobConfigFile);

		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();

		}
	}

	public FileConfiguration getCustomConfig() {
		return mobConfig;
	}

}
