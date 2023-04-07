package dev.stephenpearson.model;

public class GUIValues {
	
	private static Integer placedBetAmount;
	private static Integer currentBetAmount = 0;
	private static boolean betJustPlaced = false;

	public static Integer getPlacedBetAmount() {
		return placedBetAmount;
	}

	public static void setPlacedBetAmount(Integer placedBetAmount) {
		GUIValues.placedBetAmount = placedBetAmount;
	}

	public static Integer getCurrentBetAmount() {
		return currentBetAmount;
	}
	
	public static void increaseCurrentBetAmount(Integer amount) {
		currentBetAmount += amount;
	}

	public static void setCurrentBetAmount(Integer currentBetAmount) {
		GUIValues.currentBetAmount = currentBetAmount;
	}

	public static boolean isBetJustPlaced() {
		return betJustPlaced;
	}

	public static void setBetJustPlaced(boolean betJustPlaced) {
		GUIValues.betJustPlaced = betJustPlaced;
	}

	


	
	

}
