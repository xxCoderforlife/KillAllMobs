package me.Coderforlife.KillMobs.TabCommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.Coderforlife.KillMobs.Main;

public class Tabs implements TabCompleter {

	private Main plugin;

	public Tabs(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> tab = new ArrayList<>();
		if (args.length == 0) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				tab.remove(all.getName());
			}
		}
		if(args.length == 1) {
			tab.add("mobs");
			tab.add("killall");
		}
		if (args.length == 2) {
			if(args[0].equalsIgnoreCase("mobs")) {
				for (LivingEntity en : Bukkit.getWorld("world").getLivingEntities()) {
					if(!(en instanceof Player)) {
						tab.add(en.getType().toString());
					}
				}	
			}

		}
		return tab;
	}

}
