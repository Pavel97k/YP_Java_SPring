package com.example.itogproject.Controllers;

import com.example.itogproject.Models.Role;
import com.example.itogproject.Services.Role_Service;
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
@RequestMapping("/Role")
public class Role_Controller {
    private final Role_Service roleService;

    @Autowired
    public Role_Controller(Role_Service roleService) {
        this.roleService = roleService;
    }

    // Отображение всех обьектов на странице
    @GetMapping()
    public String RolePage(Model model) {
        model.addAttribute("roles", roleService.findAllRole());
        return "Role/RolePage";
    }

    // Добавление нового обьекта с отображением всех обьектов
    @PreAuthorize("hasAuthority('Администратор')")
    @PostMapping("/add")
    public String RolePageCreate(Model model, @Valid Role role) {
        roleService.saveRole(role, null);
        model.addAttribute("roles", roleService.findAllRole());
        return "Role/RolePage";
    }

    // Переход на страницу с обновлением информации о пользователе
    @PreAuthorize("hasAuthority('Администратор')")
    @GetMapping("/page/update/{id}")
    public String ShowUpdateRolePage(@PathVariable("id") long id, Model model) {
        model.addAttribute("role", roleService.UpdateRolePage(id));
        return "Role/RolePageEdit";
    }

    // Обновление информации о пользователе
    @PreAuthorize("hasAuthority('Администратор')")
    @PostMapping(value = "/update/{id}")
    public String ShowUpdateRole(@Valid Role role, Model model) {
        try {
            roleService.saveRole(role, role.getId());
            model.addAttribute("roles", roleService.findAllRole());
            return "Role/RolePage";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "Role/RolePageEdit";
        }
    }

    // Удаление пользователя
    @PreAuthorize("hasAuthority('Администратор')")
    @GetMapping("/delete/{id}")
    public String deleteRole(Model model, Role role) {
        try {
            roleService.deleteRole(role.getId());
            model.addAttribute("roles", roleService.findAllRole());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "Role/RolePage";
    }
}
