package com.example.itogproject.Controllers;

import com.example.itogproject.Models.Employee;
import com.example.itogproject.Services.Employee_Service;
import com.example.itogproject.Services.Manufacturer_Service;
import com.example.itogproject.Services.Role_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {
    private final Employee_Service employeeService;
    private final Role_Service roleService;
    private final Manufacturer_Service manufacturerService;

    @Autowired
    public AuthController(Employee_Service employeeService, Role_Service roleService, Manufacturer_Service manufacturerService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.manufacturerService = manufacturerService;
    }

    // Страница регистрации.
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("primaryKeyValues", roleService.findAllRole());
        model.addAttribute("Manufacturers_emp", manufacturerService.findAllManufacturer());
        return "Employee/EmployeeRegistrationPage";
    }

    // Регистрация пользователя
    @PostMapping("register/new")
    public String registerUser(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/login";
    }

    // Обрабатывает запрос на отображение профиля пользователя
    @GetMapping("profile")
    public String showProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Employee employee = employeeService.findByUserName(username);
        model.addAttribute("ProfileUser", employee);
        // TODO: Добавьте код для отображения профиля пользователя
        return "home";
    }
}
