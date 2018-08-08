package it.Seba4316.TimeController.commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import it.Seba4316.TimeController.Main;

public class Clock implements Listener {

	private static final String cmd = "tc clock";
	private static final String args = "";
	private static final String desc = "Gives you a clock for Time Controlling";

	public static final String helpMessage = cmd + " " + args + " §7→ §f" + desc;

	protected static Main plugin = Main.getPlugin(Main.class);

	@EventHandler(priority = EventPriority.HIGH)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String[] args = msg.split(" ");
		if (!(args[0].equalsIgnoreCase("/tc")))
			return;
		if (!(args.length > 1))
			return;
		if (!(args[1].equalsIgnoreCase("clock")))
			return;
		e.setCancelled(true);
		if (!(p.hasPermission("TimeController.Clock")) && !(p.hasPermission("TimeController.*")) && !(p.isOp())) {
			p.sendMessage(Messages.missingPermission("TimeController.Clock"));
			return;
		}
		ItemStack clockItem = new ItemStack(Material.WATCH);
		ItemMeta meta = clockItem.getItemMeta();
		meta.setDisplayName("§3§lTime Controller");
		clockItem.setItemMeta(meta);
		p.sendMessage(plugin.getConfig().getString("Messages.Clock Received").replace("&", "§").replace("{AH}", "\n"));
		if (!(p.getInventory().contains(clockItem))) {
			if (p.getItemInHand() == null) {
				p.setItemInHand(clockItem);
				return;
			}
			p.getInventory().addItem(clockItem);
		} else {
			p.getInventory().removeItem(clockItem);
			p.setItemInHand(clockItem);
			return;
		}
		
	}

}