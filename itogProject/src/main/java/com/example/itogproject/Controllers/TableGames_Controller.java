package com.example.itogproject.Controllers;

import com.example.itogproject.Models.TableGames;
import com.example.itogproject.Services.Manufacturer_Service;
import com.example.itogproject.Services.TableGames_Service;
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
@RequestMapping("/TableGames")
public class TableGames_Controller {
    private final TableGames_Service tableGamesService;
    private final Manufacturer_Service manufacturerService;

    @Autowired
    public TableGames_Controller(TableGames_Service tableGamesService, Manufacturer_Service manufacturerService) {
        this.tableGamesService = tableGamesService;
        this.manufacturerService = manufacturerService;
    }


    // Отображение всех обьектов на странице
    @GetMapping()
    public String TableGamesPage(Model model) {
        model.addAttribute("table_games", tableGamesService.findAllTableGame());
        model.addAttribute("primaryKeyValues",manufacturerService.findAllManufacturer());
        return "TableGames/TableGamesPage";
    }

    // Добавление нового обьекта с отображением всех обьектов
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String TableGamesPageCreate(Model model, @Valid TableGames tableGames) {
        tableGamesService.saveTableGames(tableGames, null);
        model.addAttribute("table_games", tableGamesService.findAllTableGame());
        model.addAttribute("primaryKeyValues",manufacturerService.findAllManufacturer());
        return "TableGames/TableGamesPage";
    }

    // Переход на страницу с обновлением информации о пользователе
    @GetMapping("/page/update/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ShowUpdateTableGamesPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("table_game", tableGamesService.UpdateTableGamesPage(id));
        model.addAttribute("primaryKeyValues",manufacturerService.findAllManufacturer());
        return "TableGames/TableGamesPageEdit";
    }

    // Обновление информации о пользователе
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ShowUpdateTableGames(@Valid TableGames tableGames, Model model) {
        try {
            tableGamesService.saveTableGames(tableGames, tableGames.getId());
            model.addAttribute("table_games", tableGamesService.findAllTableGame());
            model.addAttribute("primaryKeyValues",manufacturerService.findAllManufacturer());
            return "TableGames/TableGamesPage";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "TableGames/TableGamesPageEdit";
        }
    }

    // Удаление пользователя
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String deleteTableGames(Model model, TableGames tableGames) {
        try {
            tableGamesService.deleteTableGames(tableGames.getId());
            model.addAttribute("table_games", tableGamesService.findAllTableGame());
            model.addAttribute("primaryKeyValues",manufacturerService.findAllManufacturer());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "TableGames/TableGamesPage";
    }
}
