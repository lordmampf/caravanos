package me.lordmampf.CaravanOS;

public class CurrentData {

	public static int mCurrentLight = -1;

	public static int[][] mConfigPins = { { 1, 2, 3, 4 }, { 13, 14, 15, 12 } };

	public static String getCurrentTitle() {
		return getTitle(mCurrentLight);
	}

	public static String getTitle(int pLightNumber) {
		switch (pLightNumber) {
		default:
			return "";
		case 0:
			return "Beleuchtung 1";
		case 1:
			return "Beleuchtung 2";

		}

	}

}
