package it.Seba4316.TimeController;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import it.Seba4316.TimeController.commands.Clock;
import it.Seba4316.TimeController.commands.Help;
import it.Seba4316.TimeController.commands.Messages;
import it.Seba4316.TimeController.commands.TimeController;
import it.Seba4316.TimeController.events.Interact;
import it.Seba4316.TimeController.utils.TimeUtils;

public class Main extends JavaPlugin {
	
	
	public static double version = 0.0;
	
	public void onEnable() {
		
		
		
		version = 0.1;
		
		//Register the classes
		Bukkit.getPluginManager().registerEvents(new Interact(), this); // Interact (event) class
		Bukkit.getPluginManager().registerEvents(new TimeUtils(), this); // TimeUtils (utility) class
		Bukkit.getPluginManager().registerEvents(new Messages(), this); // Messages (utility) class
		Bukkit.getPluginManager().registerEvents(new Help(), this); // Help (command) class
		Bukkit.getPluginManager().registerEvents(new TimeController(), this); // TimeController (command) class
		Bukkit.getPluginManager().registerEvents(new Clock(), this); // Clock (command) class
		
		//Save the configs file
		saveDefaultConfig();
		getConfig().options().copyDefaults(false);
		saveConfig();
		
		Bukkit.getConsoleSender().sendMessage(("§7[§3TimeController&7] §bv" + version + " §aenabled succesfully").replace("&", "§"));
		
	}
	
	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage("§7[§3TimeController§7] §bv" + version + " §cdisabled succesfully");
		
	}
	
}