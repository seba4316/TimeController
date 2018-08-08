package it.Seba4316.TimeController.utils;

import org.bukkit.World;
import org.bukkit.event.Listener;

public class TimeUtils implements Listener {

	public static String getTime(World world) {
		try {
			if ((!(world.getTime() < 0) && world.getTime() < 1000) || world.getTime() > 16000) {
				return "Noon";
			}
			if (world.getTime() > 1000 && world.getTime() < 12500) {
				return "Afternoon";
			}
			if (world.getTime() >= 12500 && world.getTime() < 13000) {
				return "Evening";
			}
			if (world.getTime() > 13500 && world.getTime() < 16000) {
				return "Night";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error";
		}
		return "Error";
	}

	public static boolean setTime(String time, World world) {
		try {
			if (time == "Noon") {
				world.setTime(0);
				return true;
			}
			if (time == "Afternoon") {
				world.setTime(6000);
				return true;
			}
			if (time == "Evening") {
				world.setTime(12500);
				return true;
			}
			if (time == "Night") {
				world.setTime(13500);
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}

	public static boolean isRaining(World world) {
		if (world.hasStorm())
			return true;
		return false;
	}

	public static boolean setRaining(Boolean b, World world) {
		if (b == false) {
			try {
				world.setStorm(false);
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		if (b == true) {
			try {
				world.setStorm(true);
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static boolean isThundering(World world) {
		if (world.isThundering())
			return true;
		return false;
	}

	public static boolean setThundering(Boolean b, World world) {
		if (b == false) {
			try {
				world.setThundering(false);
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		if (b == true) {
			try {
				world.setThundering(true);
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static boolean timeBlocked(World world) {
		if (world.getGameRuleValue("doDaylightCycle") == "false") {
			return true;
		}
		return false;
	}

	public static void timeBlocker(Boolean b, World world) {
		if (b == false) {
			try {
				world.setGameRuleValue("doDaylightCycle", "false");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		try {
			world.setGameRuleValue("doDaylightCycle", "true");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}

}