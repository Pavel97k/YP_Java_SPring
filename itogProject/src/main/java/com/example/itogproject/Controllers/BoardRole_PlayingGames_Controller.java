package com.example.itogproject.Controllers;

import com.example.itogproject.Models.BoardRole_PlayingGames;
import com.example.itogproject.Services.BoardRole_PlayingGames_Service;
import com.example.itogproject.Services.Manufacturer_Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BoardRole_PlayingGames")
public class BoardRole_PlayingGames_Controller {
    private final BoardRole_PlayingGames_Service boardRolePlayingGamesService;
    private final Manufacturer_Service manufacturerService;

    @Autowired
    public BoardRole_PlayingGames_Controller(BoardRole_PlayingGames_Service boardRolePlayingGamesService, Manufacturer_Service manufacturerService) {
        this.boardRolePlayingGamesService = boardRolePlayingGamesService;
        this.manufacturerService = manufacturerService;
    }

    // Отображение всех обьектов на странице
    @GetMapping()
    public String BoardRole_PlayingGamesPage(Model model) {
        model.addAttribute("board_role_playingGames", boardRolePlayingGamesService.findAllBoardRole_PlayingGames());
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "BoardRole_PlayingGames/BoardRole_PlayingGamesPage";
    }

    // Добавление нового обьекта с отображением всех обьектов
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    @PostMapping("/add")
    public String BoardRole_PlayingGamesPageCreate(Model model, @Valid BoardRole_PlayingGames boardRolePlayingGames) {
        boardRolePlayingGamesService.saveBoardRole_PlayingGames(boardRolePlayingGames, null);
        model.addAttribute("board_role_playingGames", boardRolePlayingGamesService.findAllBoardRole_PlayingGames());
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "BoardRole_PlayingGames/BoardRole_PlayingGamesPage";
    }

    // Переход на страницу с обновлением информации о пользователе
    @GetMapping("/page/update/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ShowUpdateBoardRole_PlayingGamesPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("board_role_playingGame", boardRolePlayingGamesService.UpdateBoardRole_PlayingGamesPage(id));
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "BoardRole_PlayingGames/BoardRole_PlayingGamesPageEdit";
    }

    // Обновление информации о пользователе
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ShowUpdateBoardRole_PlayingGames(@Valid BoardRole_PlayingGames boardRolePlayingGames, Model model) {
        try {
            boardRolePlayingGamesService.saveBoardRole_PlayingGames(boardRolePlayingGames, boardRolePlayingGames.getId());
            model.addAttribute("board_role_playingGames", boardRolePlayingGamesService.findAllBoardRole_PlayingGames());
            model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
            return "BoardRole_PlayingGames/BoardRole_PlayingGamesPage";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "BoardRole_PlayingGames/BoardRole_PlayingGamesPageEdit";
        }
    }

    // Удаление пользователя
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String deleteBoardRole_PlayingGames(Model model, BoardRole_PlayingGames boardRole_playingGames) {
        try {
            boardRolePlayingGamesService.deleteBoardRole_PlayingGames(boardRole_playingGames.getId());
            model.addAttribute("board_role_playingGames", boardRolePlayingGamesService.findAllBoardRole_PlayingGames());
            model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "BoardRole_PlayingGames/BoardRole_PlayingGamesPage";
    }
}
