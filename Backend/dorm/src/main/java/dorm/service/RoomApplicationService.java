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

    public RoomApplication reject(Long id) {
        RoomApplication application = roomApplicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RoomApplication not found"));
        if (application.getStatus() != ApplicationStatus.IN_PROGRESS) {
            throw new IllegalArgumentException("Only IN_PROGRESS applications can be rejected");
        }
        application.setStatus(ApplicationStatus.REJECTED);
        return roomApplicationRepository.save(application);
    }

    public List<RoomApplication> getMyApplications(Long studentId) {
        return roomApplicationRepository.findByStudentId(studentId);
    }

    public RoomApplication getMyApplicationById(Long studentId, Long id) {
        RoomApplication application = roomApplicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RoomApplication not found"));
        if (!application.getStudentId().equals(studentId)) {
            throw new IllegalArgumentException("Access denied");
        }
        return application;
    }

    public RoomApplication apply(Long studentId, Long dormId) {
        Dorm dorm = dormRepository.findById(dormId)
                .orElseThrow(() -> new EntityNotFoundException("Dorm not found"));
        if (roomApplicationRepository.existsByStudentIdAndDormIdAndStatusIn(
                studentId, dormId, List.of(ApplicationStatus.IN_PROGRESS, ApplicationStatus.ACCEPTED))) {
            throw new IllegalArgumentException("You already have an active application for this dorm");
        }
        RoomApplication application = new RoomApplication();
        application.setStudentId(studentId);
        application.setDorm(dorm);
        application.setStatus(ApplicationStatus.IN_PROGRESS);
        application.setDateOfApplication(new Date());
        return roomApplicationRepository.save(application);
    }

}
