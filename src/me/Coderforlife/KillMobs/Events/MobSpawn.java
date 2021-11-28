package me.Coderforlife.KillMobs.Events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import me.Coderforlife.KillMobs.Main;

public class MobSpawn implements Listener{

	private Main plugin;

	public MobSpawn(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent e) {
		try {
		List<String> list = plugin.getConfig().getStringList("Mob.DenySpawn");
		for(String string : list) {
			e.getEntityType();
			if(EntityType.valueOf(string) != null) {
				e.setCancelled(true);
			}
		  }
		}catch(IllegalArgumentException ex){
			Bukkit.getConsoleSender().sendMessage("Error with preventing mob spawns.");
		}
	}

}
