package com.example.itogproject.Controllers;

import com.example.itogproject.Models.Manufacturer;
import com.example.itogproject.Services.Manufacturer_Service;
import com.example.itogproject.Services.Role_Service;
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
@RequestMapping("/Manufacturer")
public class Manufacturer_Controller {
    private final Manufacturer_Service manufacturerService;
    private final Role_Service roleService;

    @Autowired
    public Manufacturer_Controller(Manufacturer_Service manufacturerService, Role_Service roleService) {
        this.manufacturerService = manufacturerService;
        this.roleService = roleService;
    }

    // Отображение всех обьектов на странице
    @GetMapping()
    public String ManufacturerPage(Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturer());
        model.addAttribute("primaryKeyValues", roleService.findAllRole());
        return "Manufacturer/ManufacturerPage";
    }

    // Добавление нового обьекта с отображением всех обьектов
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ManufacturerPageCreate(Model model, @Valid Manufacturer manufacturer) {
        manufacturerService.saveManufacturer(manufacturer, null);
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturer());
        model.addAttribute("primaryKeyValues", roleService.findAllRole());
        return "Manufacturer/ManufacturerPage";
    }

    // Переход на страницу с обновлением информации о пользователе
    @GetMapping("/page/update/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ShowUpdateManufacturerPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("manufacturer", manufacturerService.UpdateManufacturerPage(id));
        model.addAttribute("primaryKeyValues", roleService.findAllRole());
        return "Manufacturer/ManufacturerPageEdit";
    }

    // Обновление информации о пользователе
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String ShowUpdateManufacturer(@Valid Manufacturer manufacturer, Model model) {
        try {
            manufacturerService.saveManufacturer(manufacturer, manufacturer.getId());
            model.addAttribute("manufacturers", manufacturerService.findAllManufacturer());
            model.addAttribute("primaryKeyValues", roleService.findAllRole());
            return "Manufacturer/ManufacturerPage";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "Manufacturer/ManufacturerPageEdit";
        }
    }

    // Удаление пользователя
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Администратор') or hasAuthority('Продавец')")
    public String deleteManufacturer(Model model, Manufacturer manufacturer) {
        try {
            manufacturerService.deleteManufacturer(manufacturer.getId());
            model.addAttribute("manufacturers", manufacturerService.findAllManufacturer());
            model.addAttribute("primaryKeyValues", roleService.findAllRole());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "Manufacturer/ManufacturerPage";
    }
}
