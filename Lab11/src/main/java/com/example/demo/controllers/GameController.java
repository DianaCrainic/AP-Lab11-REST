package com.example.demo.controllers;

import com.example.demo.model.Game;
import com.example.demo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GameController: a REST controller containing methods for:
 * obtaining the list of the games, via a HTTP GET request.
 * adding a new game in the database, via a HTTP POST request.
 * modifying the name of a game, via a HTTP PUT request.
 * deleting a game, via a HTTP DELETE request.
 */

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping
    public ResponseEntity<String> addGame(@RequestBody Game game) {
        gameService.addGame(game);
        return new ResponseEntity<>("Game created", HttpStatus.CREATED);
    }
}
