package com.example.demo;

import com.example.demo.controller.GameController;
import com.example.demo.controller.PlayerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main Application
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		PlayerController playerController = new PlayerController();
		playerController.getPlayers();
		playerController.countPlayers();
		playerController.updatePlayer(1,"Diana");
		playerController.deletePlayer(1);

		GameController gameController = new GameController();
		gameController.getGames();
		gameController.countGames();
		gameController.updateGame(1,"Gomoku");
		gameController.deleteGame(2);
	}

}
