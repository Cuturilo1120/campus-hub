package dorm.service;

import dorm.model.entity.Dorm;
import dorm.repository.DormRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormService {

    @Autowired
    private DormRepository dormRepository;

    public List<Dorm> getAllDorms() {
        return dormRepository.findAll();
    }

    public Dorm getDormById(Long id) {
        return dormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dorm not found"));
    }

    public Dorm save(Dorm dorm) {
        return dormRepository.save(dorm);
    }

    public void deleteDorm(Long id) {
        dormRepository.deleteById(id);
    }

}
