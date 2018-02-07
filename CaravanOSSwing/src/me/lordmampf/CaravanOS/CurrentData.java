package me.lordmampf.CaravanOS;

public class CurrentData {

	public static int mCurrentLight = -1;

	public static int[][] mConfigPins = { { 1, 2, 3, 4 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 } };

	public static String getCurrentTitle() {
		return getTitle(mCurrentLight);
	}

	public static String getTitle(int pLightNumber) {
		switch (pLightNumber) {
		default:
			return "";
		case 0:
			return "Schlafbereich";
		case 1:
			return "Essbereich oben";
		case 2:
			return "Essbereich unten";

		}

	}

}
