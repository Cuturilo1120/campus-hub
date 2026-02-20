package dorm.service;

import dorm.model.entity.DormStay;
import dorm.repository.DormStayRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormStayService {

    @Autowired
    private DormStayRepository dormStayRepository;


    public List<DormStay> getAllDormStay() {
        return dormStayRepository.findAll();
    }

    public DormStay getDormStayById(Long id) {
        return dormStayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DormStay not found"));
    }

    public DormStay save(DormStay dormStay) {
        return dormStayRepository.save(dormStay);
    }

    public void deleteDormStay(Long id) {
        dormStayRepository.deleteById(id);
    }

}
