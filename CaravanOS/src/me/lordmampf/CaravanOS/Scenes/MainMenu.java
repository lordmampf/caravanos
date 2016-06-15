package me.lordmampf.CaravanOS.Scenes;

import javafx.application.Platform;
import javafx.fxml.FXML;
import me.lordmampf.CaravanOS.Main;

public class MainMenu {

	@FXML
	private void onExitButton() {
		Platform.exit();
	}

	@FXML
	private void onLightButton() {
		Main.showScene(LightMenu.class);
	}

}
