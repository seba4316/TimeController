package it.Seba4316.TimeController.utils;

import java.util.Random;

public class MathHelper {
	
	protected static Random rand = new Random();
	
	
	public static boolean getPercent(int percent) {
		return rand.nextInt(100) <= percent;
	}
	
}