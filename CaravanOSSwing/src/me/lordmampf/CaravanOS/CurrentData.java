package me.lordmampf.CaravanOS;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CurrentData {

	public static int mCurrentLight = -1;

	public static int[][] mConfigPins = { { 1, 2, 3, 4 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 }, { 401, 402, 403, 404 } };

	public static int[] mSliderValues = new int[4];

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
		case 3:
			return "Küche";
		}
	}

	public static void loadConfig() {
		try {

			String content = new String(Files.readAllBytes(Paths.get("config.txt")));

			String[] values = content.split("\n");

			int index = 0;

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					mConfigPins[i][j] = Integer.parseInt(values[index].trim());
					index++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
