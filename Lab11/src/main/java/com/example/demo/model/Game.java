package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Game class: contains information about a game
 */
@Getter
@Setter
@AllArgsConstructor
public class Game {
    private int id;
    private String name;

}
