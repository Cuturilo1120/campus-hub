package dorm.service;

import dorm.model.entity.Pavilion;
import dorm.repository.PavilionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PavilionService {

    @Autowired
    private PavilionRepository pavilionRepository;

    public List<Pavilion> getAllPavilions() {
        return pavilionRepository.findAll();
    }

    public Pavilion getPavilionById(Long id) {
        return pavilionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pavilion not found"));
    }

    public Pavilion save(Pavilion pavilion) {
        return pavilionRepository.save(pavilion);
    }

    public void deletePavilion(Long id) {
        pavilionRepository.deleteById(id);
    }

}
