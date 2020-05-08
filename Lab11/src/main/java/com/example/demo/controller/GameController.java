package com.example.demo.controller;

import com.example.demo.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {
    private final List<Game> games = new ArrayList<>();

    public GameController() {
        games.add(new Game(1, "Game1"));
        games.add(new Game(2, "Game2"));
        games.add(new Game(3, "Game3"));
    }

    @GetMapping
    public List<Game> getGames() {
        return games;
    }

    @GetMapping("/count")
    public int countGames() {
        return games.size();
    }

    public Game getGames(@PathVariable("id") int id) {
        return games.stream()
                .filter(p -> p.getId() == id).findFirst()
                .orElse(null);
    }


    @PostMapping
    public int createGame(@RequestParam String name) {
        int id = 1 + games.size();
        games.add(new Game(id, name));
        return id;
    }

    @PostMapping(value = "/obj", consumes = "application/json")
    public ResponseEntity<String> createGame(@RequestBody Game game) {
        games.add(game);
        return new ResponseEntity<>("Game created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGame(
            @PathVariable int id, @RequestParam String name) {
        Game game = findById(id);
        if (game == null) {
            return new ResponseEntity<>("Game not found", HttpStatus.NOT_FOUND);
        }
        game.setName(name);
        return new ResponseEntity<>("Game updated successfully", HttpStatus.OK);
    }


    private Game findById(int id) {
        return getGames(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable int id) {
        Game game = findById(id);
        if (game == null) {
            return new ResponseEntity<>("Game not found", HttpStatus.GONE);
        }
        games.remove(game);
        return new ResponseEntity<>("Game removed", HttpStatus.OK);
    }

}
