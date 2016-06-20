package me.lordmampf.CaravanOS;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import me.lordmampf.CaravanOS.Scenes.MainMenu;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Main extends Application {

	public static Stage mStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			mStage = primaryStage;
			mStage.setTitle("CaravanOS");

			StackPane root = new StackPane();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("color.css").toExternalForm());

			mStage.setScene(scene);

			showScene(MainMenu.class);

			mStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void showScene(Class pScene) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(pScene.getResource(pScene.getSimpleName() + ".fxml"));
			Pane pane = (Pane) fxmlLoader.load();

			if (fxmlLoader.getController() instanceof IInitializeable) {
				((IInitializeable) fxmlLoader.getController()).initialize(pane);
			}

			Pane rootpane = (Pane) mStage.getScene().getRoot();
			rootpane.getChildren().clear();
			rootpane.getChildren().add(pane);

		} catch (IOException e) {
			System.err.println("Error loading EventHandlerDemo.fxml!");
			e.printStackTrace();
		}

		mStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		mStage.setFullScreen(true);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
