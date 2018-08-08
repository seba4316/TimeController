package it.Seba4316.TimeController.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import it.Seba4316.TimeController.Main;
import it.Seba4316.TimeController.commands.Messages;
import it.Seba4316.TimeController.utils.MathHelper;
import it.Seba4316.TimeController.utils.TimeUtils;

public class Interact implements Listener {

	protected static Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action ac = e.getAction();
		ItemStack heldItem = p.getItemInHand();
		World world = p.getWorld();
		if (!(ac == Action.RIGHT_CLICK_AIR) && !(ac == Action.RIGHT_CLICK_BLOCK))
			return;
		if (p.isSneaking())
			return;
		if (heldItem == null)
			return;
		if (heldItem.getType() != Material.WATCH)
			return;
		if (!(heldItem.getItemMeta().hasDisplayName()))
			return;
		if (!(heldItem.getItemMeta().getDisplayName().equalsIgnoreCase("§3§lTime Controller")))
			return;
		if (!(p.hasPermission("TimeController.Advance")) || !(p.isOp())) {
			p.sendMessage(Messages.missingPermission("TimeController.Advance"));
			return;
		}
		String time = TimeUtils.getTime(world);
		Boolean raining = TimeUtils.isRaining(world);
		Boolean thundering = TimeUtils.isThundering(world);
		try {
			if (time == "Error") {
				p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§")
						.replace("{WORLDTIME}", "" + world.getTime()).replace("{AH}", "\n"));
				return;
			}
		} catch (Exception ex) {
			p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§")
					.replace("{WORLDTIME}", "" + world.getTime()).replace("{AH}", "\n"));
			ex.printStackTrace();
		}
		try {
			if (time == "Noon") {
				TimeUtils.setTime("Afternoon", world);
				p.sendMessage(plugin.getConfig().getString("Messages.Time Advanced").replace("&", "§")
						.replace("{NOW}", "" + "Afternoon").replace("{AH}", "\n"));
			} else if (time == "Afternoon") {
				TimeUtils.setTime("Evening", world);
				p.sendMessage(plugin.getConfig().getString("Messages.Time Advanced").replace("&", "§")
						.replace("{NOW}", "" + "Evening").replace("{AH}", "\n"));
			} else if (time == "Evening") {
				TimeUtils.setTime("Night", world);
				p.sendMessage(plugin.getConfig().getString("Messages.Time Advanced").replace("&", "§")
						.replace("{NOW}", "" + "Night").replace("{AH}", "\n"));
			} else if (time == "Night") {
				TimeUtils.setTime("Noon", world);
				p.sendMessage(plugin.getConfig().getString("Messages.Time Advanced").replace("&", "§")
						.replace("{NOW}", "" + "Noon").replace("{AH}", "\n"));
			}
			p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
		} catch (Exception ex) {
			p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§"));
			ex.printStackTrace();
		}
		if (raining) {
			try {
				if (!TimeUtils.setRaining(false, world))
					p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§"));
			} catch (Exception ex) {
				p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§"));
				ex.printStackTrace();
			}
		} else {
			if (MathHelper.getPercent(20)) {
				try {
					if (!TimeUtils.setRaining(true, world))
						p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§"));
					if (!thundering) {
						if (MathHelper.getPercent(10)) {
							try {
								if (!TimeUtils.setThundering(true, world))
									p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected")
											.replace("&", "§"));
							} catch (Exception ex) {
								p.sendMessage(
										plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§"));
								ex.printStackTrace();
							}
						}
					}
				} catch (Exception ex) {
					p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§"));
					ex.printStackTrace();
				}
			}
		}
		if (thundering) {
			try {
				if (!TimeUtils.setThundering(false, world))
					p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§"));
			} catch (Exception ex) {
				p.sendMessage(plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§"));
				ex.printStackTrace();
			}
		}
	}

	@EventHandler
	public void onRightClickShift(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action ac = e.getAction();
		ItemStack heldItem = p.getItemInHand();
		World world = p.getWorld();
		if (!(ac == Action.RIGHT_CLICK_AIR) && !(ac == Action.RIGHT_CLICK_BLOCK))
			return;
		if (!(p.isSneaking()))
			return;
		if (heldItem == null)
			return;
		if (heldItem.getType() != Material.WATCH)
			return;
		if (!(heldItem.getItemMeta().hasDisplayName()))
			return;
		if (!(heldItem.getItemMeta().getDisplayName().equalsIgnoreCase("§3§lTime Controller")))
			return;
		if (!(p.hasPermission("TimeController.Block")) || !(p.isOp())) {
			p.sendMessage(Messages.missingPermission("TimeController.Block"));
			return;
		}
		if(TimeUtils.timeBlocked(world)) {
			TimeUtils.timeBlocker(true, world);
			p.sendMessage(Messages.timeBlocker(false, world.getName()));
		} else {
			TimeUtils.timeBlocker(false, world);
			p.sendMessage(Messages.timeBlocker(true, world.getName()));
		}
		
		
	}

}