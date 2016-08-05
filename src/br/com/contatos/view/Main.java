package br.com.contatos.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent p = FXMLLoader.load(getClass().getResource("Contatos.fxml"));

		primaryStage.setScene(new Scene(p));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
