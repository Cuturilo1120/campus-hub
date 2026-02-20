package dorm.service;

import dorm.model.entity.RoomApplication;
import dorm.repository.RoomApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomApplicationService {

    @Autowired
    private RoomApplicationRepository roomApplicationRepository;

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

}
