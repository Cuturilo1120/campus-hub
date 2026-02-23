package dorm.service;

import dorm.model.entity.ApplicationStatus;
import dorm.model.entity.Dorm;
import dorm.model.entity.RoomApplication;
import dorm.repository.DormRepository;
import dorm.repository.RoomApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomApplicationService {

    @Autowired
    private RoomApplicationRepository roomApplicationRepository;

    @Autowired
    private DormRepository dormRepository;

    public List<RoomApplication> getAllRoomApplications() {
        return roomApplicationRepository.findAll();
    }

    public RoomApplication getRoomApplicationById(Long id) {
        return roomApplicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RoomApplication not found"));
    }

    public RoomApplication save(RoomApplication roomApplication) {
        return roomApplicationRepository.save(roomApplication);
    }

    public void deleteRoomApplication(Long id) {
        roomApplicationRepository.deleteById(id);
    }

    public RoomApplication apply(Long studentId, Long dormId) {
        Dorm dorm = dormRepository.findById(dormId)
                .orElseThrow(() -> new EntityNotFoundException("Dorm not found"));
        if (roomApplicationRepository.existsByStudentIdAndDormId(studentId, dormId)) {
            throw new IllegalArgumentException("You already have an application for this dorm");
        }
        RoomApplication application = new RoomApplication();
        application.setStudentId(studentId);
        application.setDorm(dorm);
        application.setStatus(ApplicationStatus.IN_PROGRESS);
        application.setDateOfApplication(new Date());
        return roomApplicationRepository.save(application);
    }

}
