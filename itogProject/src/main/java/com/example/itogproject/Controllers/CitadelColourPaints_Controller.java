package com.example.itogproject.Controllers;

import com.example.itogproject.Models.CitadelColourPaints;
import com.example.itogproject.Services.CitadelColourPaints_Service;
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
@RequestMapping("/CitadelColourPaints")
public class CitadelColourPaints_Controller {
    private final CitadelColourPaints_Service citadelColourPaintsService;
    private final Manufacturer_Service manufacturerService;

    @Autowired
    public CitadelColourPaints_Controller(CitadelColourPaints_Service citadelColourPaintsService, Manufacturer_Service manufacturerService) {
        this.citadelColourPaintsService = citadelColourPaintsService;
        this.manufacturerService = manufacturerService;
    }

    // Отображение всех обьектов на странице
    @GetMapping()
    public String CitadelColourPaintsPage(Model model) {
        model.addAttribute("citadel_colour_paints", citadelColourPaintsService.findAllCitadelColourPaints());
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "CitadelColourPaints/CitadelColourPaintsPage";
    }

    // Добавление нового обьекта с отображением всех обьектов
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String CitadelColourPaintsPageCreate(Model model, @Valid CitadelColourPaints citadelColourPaints) {
        citadelColourPaintsService.saveCitadelColourPaints(citadelColourPaints, null);
        model.addAttribute("citadel_colour_paints", citadelColourPaintsService.findAllCitadelColourPaints());
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "CitadelColourPaints/CitadelColourPaintsPage";
    }

    // Переход на страницу с обновлением информации о пользователе
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    @GetMapping("/page/update/{id}")
    public String ShowUpdateCitadelColourPaintsPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("citadel_colour_paint", citadelColourPaintsService.UpdateCitadelColourPaintsPage(id));
        model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        return "CitadelColourPaints/CitadelColourPaintsPageEdit";
    }

    // Обновление информации о пользователе
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    @PostMapping(value = "/update/{id}")
    public String ShowUpdateCitadelColourPaints(@Valid CitadelColourPaints citadelColourPaints, Model model) {
        try {
            citadelColourPaintsService.saveCitadelColourPaints(citadelColourPaints, citadelColourPaints.getId());
            model.addAttribute("citadel_colour_paints", citadelColourPaintsService.findAllCitadelColourPaints());
            model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
            return "CitadelColourPaints/CitadelColourPaintsPage";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "CitadelColourPaints/CitadelColourPaintsPageEdit";
        }
    }

    // Удаление пользователя
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String deleteCitadelColourPaints(Model model, CitadelColourPaints citadelColourPaints) {
        try {
            citadelColourPaintsService.deleteCitadelColourPaints(citadelColourPaints.getId());
            model.addAttribute("citadel_colour_paints", citadelColourPaintsService.findAllCitadelColourPaints());
            model.addAttribute("primaryKeyValues",  manufacturerService.findAllManufacturer());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "CitadelColourPaints/CitadelColourPaintsPage";
    }
}
