package me.Coderforlife.KillMobs;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.Coderforlife.KillMobs.Commands.KillMobs;
import me.Coderforlife.KillMobs.Commands.KillZombies;
import me.Coderforlife.KillMobs.Events.PlayerQuit;
import me.Coderforlife.KillMobs.TabCommands.Tabs;

public class Main extends JavaPlugin {

	public static String prefix = ChatColor.translateAlternateColorCodes('&', "&4&l[&6&lKillAllMobs&4&l]&r ");
	public static String perm = "KAM.";
	public static String header = ChatColor.translateAlternateColorCodes('&', 
			"&4&l========&7&l[&6KillAllMobs&7&l]&4&l========");

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		this.getCommand("killzombies").setExecutor(new KillZombies(this));
		this.getCommand("killmobs").setExecutor(new KillMobs(this));
		this.getCommand("killmobs").setTabCompleter(new Tabs(this));

	}

	@Override
	public void onDisable() {

	}

}
