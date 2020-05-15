package com.example.demo.controllers;

import com.example.demo.model.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerController: a REST controller containing methods for:
 * obtaining the list of the players, via a HTTP GET request.
 * adding a new player in the database, via a HTTP POST request.
 * modifying the name of a player, via a HTTP PUT request.
 * deleting a player, via a HTTP DELETE request.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
        return new ResponseEntity<>("Player created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayerName(@PathVariable @Valid Integer id, @RequestParam String name) {
        playerService.updatePlayerName(id, name);
        return new ResponseEntity<>("Player updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<String> deletePlayer(@PathVariable @Valid Integer id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>("Player deleted", HttpStatus.OK);
    }
}
