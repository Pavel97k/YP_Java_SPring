package com.example.itogproject.Services;

import com.example.itogproject.Models.CitadelColourPaints;
import com.example.itogproject.Repositories.CitadelColourPaints_repository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class CitadelColourPaints_Service {
    private final CitadelColourPaints_repository citadelColourPaintsRepository;

    public CitadelColourPaints_Service(CitadelColourPaints_repository citadelColourPaintsRepository) {
        this.citadelColourPaintsRepository = citadelColourPaintsRepository;
    }

    // Поиск всех обьектов модели.
    public List<CitadelColourPaints> findAllCitadelColourPaints() {
        return citadelColourPaintsRepository.findAll();
    }

    // Сохранение обьекта.
    public void saveCitadelColourPaints(@ModelAttribute("citadel_colour_paints_repository") @Valid CitadelColourPaints citadelColourPaints, Long id) {
        citadelColourPaintsRepository.save(citadelColourPaints);
    }

    // Удаление обьекта.
    public void deleteCitadelColourPaints(Long id) {
        Optional<CitadelColourPaints> citadelColourPaints = citadelColourPaintsRepository.findById(id);
        citadelColourPaints.ifPresent(citadelColourPaintsRepository::delete);
    }

    // Поиск обьекта по id
    public CitadelColourPaints UpdateCitadelColourPaintsPage(long id) {
        return citadelColourPaintsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    }
}
