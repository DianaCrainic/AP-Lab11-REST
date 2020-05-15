package com.example.demo.services;

import com.example.demo.model.Game;
import com.example.demo.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<Game> getAllGames() {
        return ((List<Game>) gameRepository.findAll())
                .stream()
                .map(game -> modelMapper.map(game, Game.class))
                .collect(Collectors.toList());
    }

    private boolean checkIfGameExists(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.isPresent();
    }

    public void addGame(Game game) {
        gameRepository.save(modelMapper.map(game, Game.class));
    }
}
