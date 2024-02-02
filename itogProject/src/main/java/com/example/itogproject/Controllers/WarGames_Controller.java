package com.example.itogproject.Controllers;

import com.example.itogproject.Models.Wargames;
import com.example.itogproject.Services.Manufacturer_Service;
import com.example.itogproject.Services.Wargames_Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/WarGames")
public class WarGames_Controller {
    private final Wargames_Service war_gamesService;
    private final Manufacturer_Service manufacturerService;

    @Autowired
    public WarGames_Controller(Wargames_Service war_gamesService, Manufacturer_Service manufacturerService) {
        this.war_gamesService = war_gamesService;
        this.manufacturerService = manufacturerService;
    }

    // Отображение всех обьектов на странице
    @GetMapping()
    public String WarGamesPage(Model model) {
        model.addAttribute("war_games", war_gamesService.findAllWargames());
        model.addAttribute("primaryKeyValues", manufacturerService.findAllManufacturer());
        return "WarGames/WarGamesPage";
    }

    // Добавление нового обьекта с отображением всех обьектов
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    @PostMapping("/add")
    public String WarGamesPageCreate(Model model, @Valid Wargames war_game) {
        war_gamesService.saveWargames(war_game, null);
        model.addAttribute("war_games", war_gamesService.findAllWargames());
        model.addAttribute("primaryKeyValues", manufacturerService.findAllManufacturer());
        return "WarGames/WarGamesPage";
    }

    // Переход на страницу с обновлением информации о пользователе
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    @GetMapping("/page/update/{id}")
    public String ShowUpdateWarGamesPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("war_game", war_gamesService.UpdateWargamesPage(id));
        model.addAttribute("primaryKeyValues", manufacturerService.findAllManufacturer());
        return "WarGames/WarGamesPageEdit";
    }

    // Обновление информации о пользователе
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    @PostMapping(value = "/update/{id}")
    public String ShowUpdateWarGames(@Valid Wargames war_game, Model model) {
        try {
            war_gamesService.saveWargames(war_game, war_game.getId());
            model.addAttribute("war_games", war_gamesService.findAllWargames());
            model.addAttribute("primaryKeyValues", manufacturerService.findAllManufacturer());
            return "WarGames/WarGamesPage";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "WarGames/WarGamesPageEdit";
        }
    }

    // Удаление пользователя
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    @GetMapping("/delete/{id}")
    public String deleteWarGames(Model model, Wargames war_game) {
        try {
            war_gamesService.deleteWargames(war_game.getId());
            model.addAttribute("war_games", war_gamesService.findAllWargames());
            model.addAttribute("primaryKeyValues", manufacturerService.findAllManufacturer());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "WarGames/WarGamesPage";
    }
}
