package com.example.itogproject.Services;

import com.example.itogproject.Models.CitadelColourPaints;
import com.example.itogproject.Models.CollectibleCardGames;
import com.example.itogproject.Repositories.CollectibleCardGames_repository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class CollectibleCardGames_Service {
    private final CollectibleCardGames_repository collectibleCardGamesRepository;

    public CollectibleCardGames_Service(CollectibleCardGames_repository collectibleCardGamesRepository) {
        this.collectibleCardGamesRepository = collectibleCardGamesRepository;
    }

    // Поиск всех обьектов модели.
    public List<CollectibleCardGames> findAllCollectibleCardGames() {
        return collectibleCardGamesRepository.findAll();
    }

    // Сохранение обьекта.
    public void saveCollectibleCardGames(@ModelAttribute("collectible_card_games") @Valid CollectibleCardGames collectibleCardGames, Long id) {
        collectibleCardGamesRepository.save(collectibleCardGames);
    }

    // Удаление обьекта.
    public void deleteCollectibleCardGames(Long id) {
        Optional<CollectibleCardGames> collectibleCardGames = collectibleCardGamesRepository.findById(id);
        collectibleCardGames.ifPresent(collectibleCardGamesRepository::delete);
    }

    // Поиск обьекта по id
    public CollectibleCardGames UpdateCollectibleCardGamesPage(long id) {
        return collectibleCardGamesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    }
}
