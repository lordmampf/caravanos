package me.lordmampf.CaravanOS.Scenes;

import javafx.fxml.FXML;
import me.lordmampf.CaravanOS.Main;

public class LightMenu {

	@FXML
	private void onLedStripes() {
		Main.showScene(LedStripes.class);
	}

	@FXML
	private void onBackButton() {
		Main.showScene(MainMenu.class);
	}

}
