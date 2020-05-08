package com.example.demo.controller;

import com.example.demo.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("players")
public class PlayerController {
    private final List<Player> players = new ArrayList<>();

    public PlayerController() {
        players.add(new Player(1, "Player1"));
        players.add(new Player(2, "Player2"));
    }

    @GetMapping
    public List<Player> getPlayers() {
        return players;
    }

    @GetMapping("/count")
    public int countPlayers() {
        return players.size();
    }

    public Player getPlayer(@PathVariable("id") int id) {
        return players.stream()
                .filter(p -> p.getId() == id).findFirst()
                .orElse(null);
    }


    @PostMapping
    public int createPlayer(@RequestParam String name) {
        int id = 1 + players.size();
        players.add(new Player(id, name));
        return id;
    }

    @PostMapping(value = "/obj", consumes = "application/json")
    public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        players.add(player);
        return new ResponseEntity<>("Player created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(
            @PathVariable int id, @RequestParam String name) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
        player.setName(name);
        return new ResponseEntity<>("Player updated successfully", HttpStatus.OK);
    }


    private Player findById(int id) {
        return getPlayer(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.GONE);
        }
        players.remove(player);
        return new ResponseEntity<>("Player removed", HttpStatus.OK);
    }


}
