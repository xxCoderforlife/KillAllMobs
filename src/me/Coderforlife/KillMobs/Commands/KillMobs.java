package me.Coderforlife.KillMobs.Commands;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.Coderforlife.KillMobs.Main;

public class KillMobs implements CommandExecutor {

	private final String maincmd = "killmobs";
	private final String killall = "killall";
	private Main plugin;

	public KillMobs(Main plugin) {
		this.setPlugin(plugin);
	}

	public Main getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String Commandlabel, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				if (command.getName().equalsIgnoreCase(maincmd)) {
					if (p.hasPermission(Main.perm + "main")) {
						p.sendMessage(Main.header);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', 
								"&c&oUse the following commands:"));
						p.sendMessage(Main.dash + 
								ChatColor.translateAlternateColorCodes('&', 
										"&b&o/killmobs &amobs &4&lMOBNAME"));
						p.sendMessage(Main.dash + 
								ChatColor.translateAlternateColorCodes('&', 
										"&b&o/killmobs &akillall &7&o(&f&lRemoves all unnamed mobs)"));
						p.sendMessage(Main.dash + 
								ChatColor.translateAlternateColorCodes('&', 
										"&b&o/killmobs &akillall unsafe &7&o(&f&lRemoves all Mobs,Drops, and Entites even if named&7&o)"));
					} else {
						p.sendMessage(Main.prefix + 
								ChatColor.translateAlternateColorCodes('&', 
								        "&cYou don't have permission to use that command."));
						p.sendMessage(Main.prefix + 
								ChatColor.translateAlternateColorCodes('&', 
										"&7&oPermission:&b KAM.main"));
					}
				}

			}
			if (args.length == 1) {
				if(args[0].equalsIgnoreCase("mobs")) {
					p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
							" Use &e&o/killmobs mobs &c&o<MobName>"));
					
				}else if(args[0].equalsIgnoreCase(killall)){
					if(p.hasPermission(Main.perm + "killmobs.killall")) {
						final int dead = p.getWorld().getEntities().size();
						String deadS = Integer.toString(dead);
						
						for(Entity en : p.getWorld().getEntities()) {
						    boolean hasCustomName = en.getCustomName() != null;
							if(!(en instanceof Player)) {
								if (!hasCustomName) {
									en.remove();
								}
							}
						}
						p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
								"Wow you are a &4&lMonster&r you just killed &a&l" + deadS + "&r Mobs"));
						
					}else {
						//TODO Add Perm Message
					}
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("mobs")) {
					if (p.hasPermission(Main.perm + "killmobs")) {
						try {
							EntityType ent = EntityType.valueOf((String) args[1].toUpperCase());
							for (World worlds : Bukkit.getWorlds()) {
								for (Entity en : worlds.getEntities()) {
									if (en.getType() == ent) {
										en.remove();
									}
								}
							}
							p.sendMessage(Main.prefix
									+ ChatColor.translateAlternateColorCodes('&', "&aKilled all &e" + args[1] + "S"));
						} catch (IllegalArgumentException e) {
							p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&',
									"&c" + args[1] + " is not a vaild Entity"));
						}
					}else {
						//TODO Add Perm
					}
				}else if(args[0].equalsIgnoreCase(killall)) {
					if(args[1].equalsIgnoreCase("unsafe")) {
						if(p.hasPermission(Main.perm + "killall.unsafe")) {
							final int dead = p.getWorld().getEntities().size();
							String deadS = Integer.toString(dead);
							for(World worlds : Bukkit.getWorlds()) {
								for(Entity en : worlds.getEntities()) {
									if(!(en instanceof Player)) {
										en.remove();
									}
								}
							}
							p.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
									"Wow you are a &4&lMonster&r you just killed &a&l" + deadS + "&r Mobs"));
						}else {
							//TODO Add Perm Message
						}
					}
				}
			}
		}
		if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			if(args.length == 0) {
				if(command.getName().equalsIgnoreCase(maincmd)) {
					console.sendMessage(Main.header);
					console.sendMessage("Use the following commands");
					console.sendMessage("- /killmobs mobs <mobname>");
					console.sendMessage("- /killmobs killall");
				}
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase(killall)){
					for(World worlds : Bukkit.getWorlds()) {
						for(Entity en : worlds.getEntities()) {
							if(!(en instanceof Player)) {
								en.remove();
							}
						}
					}
						sender.sendMessage(Main.prefix + ChatColor.translateAlternateColorCodes('&', 
								"Wow you are a &4&lMonster&r you just killed &a&l"));
				}
			} 
			if(args.length == 2) {
				
			}
		}
		return true;

	}
}
