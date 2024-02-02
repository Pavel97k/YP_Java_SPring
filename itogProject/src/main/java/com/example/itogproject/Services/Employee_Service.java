package com.example.itogproject.Services;

import com.example.itogproject.Models.Employee;
import com.example.itogproject.Models.Role;
import com.example.itogproject.Repositories.Employee_repository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class Employee_Service {
    private final Employee_repository employeeRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Employee_Service(Employee_repository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Поиск всех обьектов модели.
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    // Сохранение обьекта.
    public void saveEmployee(Employee employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setRole(employee.getRole());
        employeeRepository.save(employee);
    }

    // Удаление обьекта.
    public void deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.ifPresent(employeeRepository::delete);
    }

    // Поиск обьекта по id
    public Employee UpdateEmployeePage(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    }

    public Employee findByUserName(String value) {
        return employeeRepository.findUserByUsername(value);
    }
}
