package com.example.itogproject.Services;

import com.example.itogproject.Models.Manufacturer;
import com.example.itogproject.Repositories.Manufacturer_repository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class Manufacturer_Service {
    private final Manufacturer_repository manufacturerRepository;

    public Manufacturer_Service(Manufacturer_repository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    // Поиск всех обьектов модели.
    public List<Manufacturer> findAllManufacturer() {
        return manufacturerRepository.findAll();
    }

    // Сохранение обьекта.
    public void saveManufacturer(@ModelAttribute("manufacturer") @Valid Manufacturer manufacturer, Long id) {
        manufacturerRepository.save(manufacturer);
    }

    // Удаление обьекта.
    public void deleteManufacturer(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        manufacturer.ifPresent(manufacturerRepository::delete);
    }

    // Поиск обьекта по id
    public Manufacturer UpdateManufacturerPage(long id) {
        return manufacturerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    }
}
