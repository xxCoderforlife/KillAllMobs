package me.Coderforlife.KillMobs.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitScheduler;

import me.Coderforlife.KillMobs.Main;

public class PlayerQuit implements Listener {

	private Main plugin;

	public PlayerQuit(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void playerquitevent(PlayerQuitEvent e) {
		final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				if (Bukkit.getServer().getOnlinePlayers().isEmpty()) {
					ConsoleCommandSender server = Bukkit.getConsoleSender();
					for(World worlds : Bukkit.getWorlds()) {
						for(Entity en : worlds.getEntities()) {
							if(!(en instanceof Player)) {
								en.remove();
							}
						}
					}
					server.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
							"&aAll Players Have Left"));
					server.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
							"&4Removed all Entites."));

				} else {
					scheduler.cancelTasks(plugin);
				}
			}
		}, 60l);

	}
}
