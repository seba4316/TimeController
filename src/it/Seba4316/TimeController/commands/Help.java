package it.Seba4316.TimeController.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import it.Seba4316.TimeController.Main;

public class Help implements Listener {

	private static final String cmd = "tc ?";
	private static final String args = "[page]";
	private static final String desc = "Returns the help page";

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
		if (!(args[1]).equalsIgnoreCase("?") && !(args[1]).equalsIgnoreCase("help"))
			return;
		e.setCancelled(true);
		if (!(p.hasPermission("TimeController.Help")) && !(p.hasPermission("TimeController.*")) && !(p.isOp())) {
			p.sendMessage(Messages.missingPermission("TimeController.Help"));
			return;
		}
		if (args.length == 2) {
			p.sendMessage(Messages.helpPage(1));
		} else if(args.length >= 3) {
			int i = 0;
			try {
				i = Integer.parseInt(args[2].replace("-", "").replace(" ", ""));
			} catch (Exception ex) {
				i = 1;
			}
			p.sendMessage(Messages.helpPage(i));
		}
	}

}