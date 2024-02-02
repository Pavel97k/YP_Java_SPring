package com.example.itogproject.Services;

import com.example.itogproject.Models.TableGames;
import com.example.itogproject.Repositories.TableGames_repository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class TableGames_Service {
    private final TableGames_repository tableGamesRepository;

    public TableGames_Service(TableGames_repository tableGamesRepository) {
        this.tableGamesRepository = tableGamesRepository;
    }

    // Поиск всех обьектов модели.
    public List<TableGames> findAllTableGame() {
        return tableGamesRepository.findAll();
    }

    // Сохранение обьекта.
    public void saveTableGames(@ModelAttribute("table_games") @Valid TableGames tableGames, Long id) {
        tableGamesRepository.save(tableGames);
    }

    // Удаление обьекта.
    public void deleteTableGames(Long id) {
        Optional<TableGames> tableGames = tableGamesRepository.findById(id);
        tableGames.ifPresent(tableGamesRepository::delete);
    }

    // Поиск обьекта по id
    public TableGames UpdateTableGamesPage(long id) {
        return tableGamesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    }
}
