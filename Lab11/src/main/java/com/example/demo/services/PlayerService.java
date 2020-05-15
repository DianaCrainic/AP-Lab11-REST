package com.example.demo.services;

import com.example.demo.exceptions.DuplicateException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Player;
import com.example.demo.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<Player> getAllPlayers() {
        return ((List<Player>) playerRepository.findAll())
                .stream()
                .map(player -> modelMapper.map(player, Player.class))
                .collect(Collectors.toList());
    }

    public void addPlayer(Player player) {
        if (player.getId() != null && checkIfPlayerExists(player.getId())) {
            throw new DuplicateException("Player with id " + player.getId() + " already exists");
        }
        playerRepository.save(modelMapper.map(player, Player.class));
    }

    private boolean checkIfPlayerExists(Integer id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.isPresent();
    }

    public void updatePlayerName(Integer id, String name) {
        if (!checkIfPlayerExists(id)) {
            throw new NotFoundException("Player with id " + id + " not found");
        }
        playerRepository.updateName(id, name);
    }

    public void deletePlayer(Integer id) {
        if (!checkIfPlayerExists(id)) {
            throw new NotFoundException("Player with id " + id + " not found");
        }
        playerRepository.deleteById(id);
    }
}
