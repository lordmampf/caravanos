package me.lordmampf.CaravanOS.Scenes;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import me.lordmampf.CaravanOS.I2CHelper;
import me.lordmampf.CaravanOS.IInitializeable;
import me.lordmampf.CaravanOS.Main;
import me.lordmampf.CaravanOS.MyCustomColorPicker;

public class LedStripes implements IInitializeable {

	@FXML
	private Slider slider;

	@FXML
	private void onChangeColor() {

	}

	@FXML
	private void onChangeBlink() {

	}

	@FXML
	private void onBackButton() {
		Main.showScene(MainMenu.class);
	}

	@Override
	public void initialize(Pane pPane) {
		MyCustomColorPicker myCustomColorPicker = new MyCustomColorPicker();

		Node n = pPane.getChildren().get(1);
		StackPane sp = (StackPane) n;
		sp.getChildren().add(myCustomColorPicker);

		myCustomColorPicker.customColorProperty().addListener((obs, pOldValue, pNewValue) -> {
			System.out.println(pNewValue);
			Background b = new Background(new BackgroundFill(pNewValue, CornerRadii.EMPTY, Insets.EMPTY));
			pPane.setBackground(b);
			sp.setBackground(b);
			myCustomColorPicker.setBackground(b);

			I2CHelper.setColor(3, 5, 6, pNewValue);
		});

		slider.valueProperty().addListener((obs, pOldValue, pNewValue) -> {
			System.out.println(pNewValue);

		});

	}

}
