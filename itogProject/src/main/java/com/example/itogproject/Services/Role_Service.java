package com.example.itogproject.Services;

import com.example.itogproject.Models.Role;
import com.example.itogproject.Repositories.Role_repository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class Role_Service {
    private final Role_repository roleRepository;

    public Role_Service(Role_repository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Поиск всех обьектов модели.
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    // Сохранение обьекта.
    public void saveRole(@ModelAttribute("role") @Valid Role role, Long id) {
        roleRepository.save(role);
    }

    // Удаление обьекта.
    public void deleteRole(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        role.ifPresent(roleRepository::delete);
    }

    // Поиск обьекта по id
    public Role UpdateRolePage(long id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    }
}
