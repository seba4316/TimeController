package it.Seba4316.TimeController.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import it.Seba4316.TimeController.Main;

public class TimeController implements Listener {
	
	private static final String cmd = "tc";
	private static final String args = "";
	private static final String desc = "Returns infos about the plugin";

	public static final String helpMessage = cmd + "" + args + " §7→ §f" + desc;

	protected static Main plugin = Main.getPlugin(Main.class);

	@EventHandler(priority = EventPriority.HIGH)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String[] args = msg.split(" ");
		if (!(args[0].equalsIgnoreCase("/tc")))
			return;
		if (!(args.length == 1))
			return;
		e.setCancelled(true);
		p.sendMessage(Messages.pluginInfo());
	}
	
}