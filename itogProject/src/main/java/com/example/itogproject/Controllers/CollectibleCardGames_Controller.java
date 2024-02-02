package com.example.itogproject.Controllers;

import com.example.itogproject.Models.CollectibleCardGames;
import com.example.itogproject.Services.CollectibleCardGames_Service;
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
@RequestMapping("/CollectibleCardGames")
public class CollectibleCardGames_Controller {
    private final CollectibleCardGames_Service collectibleCardGamesService;
    private final Manufacturer_Service manufacturerService;

    @Autowired
    public CollectibleCardGames_Controller(CollectibleCardGames_Service collectibleCardGamesService, Manufacturer_Service manufacturerService) {
        this.collectibleCardGamesService = collectibleCardGamesService;
        this.manufacturerService = manufacturerService;
    }

    // Отображение всех обьектов на странице
    @GetMapping()
    public String CollectibleCardGamesPage(Model model) {
        model.addAttribute("collectible_card_games", collectibleCardGamesService.findAllCollectibleCardGames());
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "CollectibleCardGames/CollectibleCardGamesPage";
    }

    // Добавление нового обьекта с отображением всех обьектов
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String CollectibleCardGamesPageCreate(Model model, @Valid CollectibleCardGames collectibleCardGames) {
        collectibleCardGamesService.saveCollectibleCardGames(collectibleCardGames, null);
        model.addAttribute("collectible_card_games", collectibleCardGamesService.findAllCollectibleCardGames());
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "CollectibleCardGames/CollectibleCardGamesPage";
    }

    // Переход на страницу с обновлением информации о пользователе
    @GetMapping("/page/update/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ShowUpdateCollectibleCardGamesPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("collectible_card_game", collectibleCardGamesService.UpdateCollectibleCardGamesPage(id));
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "CollectibleCardGames/CollectibleCardGamesPageEdit";
    }

    // Обновление информации о пользователе
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ShowUpdateCollectibleCardGames(@Valid CollectibleCardGames collectibleCardGames, Model model) {
        try {
            collectibleCardGamesService.saveCollectibleCardGames(collectibleCardGames, collectibleCardGames.getId());
            model.addAttribute("collectible_card_games", collectibleCardGamesService.findAllCollectibleCardGames());
            model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
            return "CollectibleCardGames/CollectibleCardGamesPage";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "CollectibleCardGames/CollectibleCardGamesPageEdit";
        }
    }

    // Удаление пользователя
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String deleteCollectibleCardGames(Model model, CollectibleCardGames collectibleCardGames) {
        try {
            collectibleCardGamesService.deleteCollectibleCardGames(collectibleCardGames.getId());
            model.addAttribute("collectible_card_games", collectibleCardGamesService.findAllCollectibleCardGames());
            model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "CollectibleCardGames/CollectibleCardGamesPage";
    }
}
