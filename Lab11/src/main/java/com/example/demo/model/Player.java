package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Player class: contains information about a player
 */
@Getter
@Setter
@AllArgsConstructor
public class Player {
    private int id;
    private String name;
    private int game_id;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
