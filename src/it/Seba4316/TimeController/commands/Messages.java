package it.Seba4316.TimeController.commands;

import org.bukkit.event.Listener;

import it.Seba4316.TimeController.Main;

public class Messages implements Listener {

	protected static Main plugin = Main.getPlugin(Main.class);

	public static String helpPage(int i) {
		if (i == 1) {
			return "§6§m+---------------+§f Help §7(§b1§8/§b1§7) §6§m+---------------+§f\n §7+ §b/"
					+ TimeController.helpMessage + "\n §7+ §b/" + Help.helpMessage + "\n §7+ §b/" + Clock.helpMessage
					+ "\n§6§m+---------------+§f Help §7(§b1§8/§b1§7) §6§m+---------------+";
		} else {

		}
		return error("Missing Help Page", i);
	}

	public static String pluginInfo() {
		String author = "Seba4316";
		String downloadLink = "";
		try {
			return "§6§m+---------------+§f TimeController §7(§b" + Main.version
					+ "§7) §6§m+---------------+§f\n§7 + §bAuthor §7→ §a" + author + "\n§7 + §bVersion §7→ §a"
					+ Main.version + "\n§7 + §bDownload §7→ " + downloadLink
					+ " \n\n§6§m+---------------+§f TimeController §7(§b" + Main.version
					+ "§7) §6§m+---------------+§f";
		} catch (Exception ex) {

		}
		return error("Unexpected", 0);
	}

	public static String error(String type, int helpPage) {
		if (type == "Unexpected") {
			return plugin.getConfig().getString("Messages.Errors.Unexpected").replace("&", "§").replace("{AH}", "\n");
		}
		if (type == "Missing Help Page") {
			return plugin.getConfig().getString("Messages.Errors.Help.Missing Page").replace("&", "§")
					.replace("{PAGE}", "" + helpPage).replace("{AH}", "\n");
		}
		return error("Unexpected", 0);
	}

	public static String missingPermission(String permission) {
		try {
			return plugin.getConfig().getString("Messages.Errors.Missing Permissions")
					.replace("{PERMISSION}", permission).replace("&", "§");
		} catch (Exception ex) {
			ex.printStackTrace();
			return error("Unexpected", 0);
		}
	}

	public static String timeBlocker(Boolean b, String world) {
		try {
			if (b == true) {
				return plugin.getConfig().getString("Messages.Time Blocked").replace("{WORLD}", world).replace("&",
						"§");
			} else {
				return plugin.getConfig().getString("Messages.Time Unblocked").replace("{WORLD}", world).replace("&",
						"§");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return error("Unexpected", 0);
		}
	}

}