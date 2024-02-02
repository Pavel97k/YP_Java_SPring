package com.example.itogproject.Services;

import com.example.itogproject.Models.BoardRole_PlayingGames;
import com.example.itogproject.Repositories.BoardRole_PlayingGames_repository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class BoardRole_PlayingGames_Service {
    private final BoardRole_PlayingGames_repository boardRolePlayingGamesRepository;

    public BoardRole_PlayingGames_Service(BoardRole_PlayingGames_repository boardRolePlayingGamesRepository) {
        this.boardRolePlayingGamesRepository = boardRolePlayingGamesRepository;
    }

    // Поиск всех обьектов модели.
    public List<BoardRole_PlayingGames> findAllBoardRole_PlayingGames() {
        return boardRolePlayingGamesRepository.findAll();
    }

    // Сохранение обьекта.
    public void saveBoardRole_PlayingGames(@ModelAttribute("board_role_playing_games") @Valid BoardRole_PlayingGames boardRolePlayingGames, Long id) {
        boardRolePlayingGamesRepository.save(boardRolePlayingGames);
    }

    // Удаление обьекта.
    public void deleteBoardRole_PlayingGames(Long id) {
        Optional<BoardRole_PlayingGames> boardRolePlayingGames = boardRolePlayingGamesRepository.findById(id);
        boardRolePlayingGames.ifPresent(boardRolePlayingGamesRepository::delete);
    }

    // Поиск обьекта по id
    public BoardRole_PlayingGames UpdateBoardRole_PlayingGamesPage(long id) {
        return boardRolePlayingGamesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
    }
}
