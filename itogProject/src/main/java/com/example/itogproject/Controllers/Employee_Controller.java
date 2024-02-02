package com.example.itogproject.Controllers;

import com.example.itogproject.Models.Employee;
import com.example.itogproject.Services.Employee_Service;
import com.example.itogproject.Services.Manufacturer_Service;
import com.example.itogproject.Services.Role_Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Employee")
public class Employee_Controller {
    private final Employee_Service employeeService;
    private final Role_Service roleService;
    private final Manufacturer_Service manufacturerService;

    @Autowired
    public Employee_Controller(Employee_Service employeeService, Role_Service roleService, Manufacturer_Service manufacturerService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.manufacturerService = manufacturerService;
    }

    // Отображение всех обьектов на странице
    @GetMapping()
    public String EmployeePage(Model model) {
        model.addAttribute("employees", employeeService.findAllEmployee());
        model.addAttribute("primaryKeyValues", roleService.findAllRole());
        model.addAttribute("Manufacturers_emp", manufacturerService.findAllManufacturer());
        return "Employee/EmployeePage";
    }

    // Добавление нового обьекта с отображением всех обьектов
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Администратор')")
    public String EmployeePageCreate(Model model, @Valid Employee employee) {
        employeeService.saveEmployee(employee);
        model.addAttribute("employees", employeeService.findAllEmployee());
        model.addAttribute("primaryKeyValues", roleService.findAllRole());
        model.addAttribute("Manufacturers_emp", manufacturerService.findAllManufacturer());
        return "Employee/EmployeePage";
    }

    // Переход на страницу с обновлением информации о пользователе
    @PreAuthorize("hasAuthority('Администратор')")
    @GetMapping("/page/update/{id}")
    public String ShowUpdateEmployeePage(@PathVariable("id") long id, Model model) {
        model.addAttribute("employee", employeeService.UpdateEmployeePage(id));
        model.addAttribute("primaryKeyValues", roleService.findAllRole());
        model.addAttribute("Manufacturers_emp", manufacturerService.findAllManufacturer());
        return "Employee/EmployeePageEdit";
    }

    // Обновление информации о пользователе
    @PreAuthorize("hasAuthority('Администратор')")
    @PostMapping(value = "/update/{id}")
    public String ShowUpdateEmployee(@Valid Employee employee, Model model) {
        try {
            employeeService.saveEmployee(employee);
            model.addAttribute("employees", employeeService.findAllEmployee());
            model.addAttribute("primaryKeyValues", roleService.findAllRole());
            model.addAttribute("Manufacturers_emp", manufacturerService.findAllManufacturer());
            return "Employee/EmployeePage";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "Employee/EmployeePageEdit";
        }
    }

    // Удаление пользователя
    @PreAuthorize("hasAuthority('Администратор')")
    @GetMapping("/delete/{id}")
    public String deleteEmployee(Model model, Employee employee) {
        try {
            employeeService.deleteEmployee(employee.getId());
            model.addAttribute("employees", employeeService.findAllEmployee());
            model.addAttribute("primaryKeyValues", roleService.findAllRole());
            model.addAttribute("Manufacturers_emp", manufacturerService.findAllManufacturer());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "Employee/EmployeePage";
    }
}
