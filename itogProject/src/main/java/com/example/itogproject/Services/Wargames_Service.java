package com.example.itogproject.Services;

import com.example.itogproject.Models.Wargames;
import com.example.itogproject.Repositories.Wargames_repository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class Wargames_Service {
    private final Wargames_repository wargamesRepository;

    public Wargames_Service(Wargames_repository wargamesRepository) {
        this.wargamesRepository = wargamesRepository;
    }

    // Поиск всех обьектов модели.
    public List<Wargames> findAllWargames() {
        return wargamesRepository.findAll();
    }

    // Сохранение обьекта.
    public void saveWargames(@ModelAttribute("wargames") @Valid Wargames wargames, Long id) {
        wargamesRepository.save(wargames);
    }

    // Удаление обьекта.
    public void deleteWargames(Long id) {
        Optional<Wargames> wargames = wargamesRepository.findById(id);
        wargames.ifPresent(wargamesRepository::delete);
    }

    // Поиск обьекта по id
    public Wargames UpdateWargamesPage(long id) {
        return wargamesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    }
}
