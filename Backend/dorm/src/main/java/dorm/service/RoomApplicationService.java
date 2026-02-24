package dorm.service;

import dorm.model.entity.ApplicationStatus;
import dorm.model.entity.Dorm;
import dorm.model.entity.DormStay;
import dorm.model.entity.Room;
import dorm.model.entity.RoomApplication;
import dorm.model.entity.StayStatus;
import dorm.repository.DormRepository;
import dorm.repository.DormStayRepository;
import dorm.repository.RoomApplicationRepository;
import dorm.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RoomApplicationService {

    @Autowired
    private RoomApplicationRepository roomApplicationRepository;

    @Autowired
    private DormRepository dormRepository;

    @Autowired
    private DormStayRepository dormStayRepository;

    @Autowired
    private RoomRepository roomRepository;

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

    public DormStay accept(Long id) {
        RoomApplication application = roomApplicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RoomApplication not found"));
        if (application.getStatus() != ApplicationStatus.IN_PROGRESS) {
            throw new IllegalArgumentException("Only IN_PROGRESS applications can be accepted");
        }

        Dorm dorm = application.getDorm();
        Room availableRoom = dorm.getPavilions().stream()
                .flatMap(p -> p.getRoomList().stream())
                .filter(r -> r.getCapacity() != null && r.getCapacity() > 0)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No available rooms in this dorm"));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date moveInDate = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date moveOutDate = cal.getTime();

        DormStay dormStay = new DormStay();
        dormStay.setStudentId(application.getStudentId());
        dormStay.setMoveInDate(moveInDate);
        dormStay.setMoveOutDate(moveOutDate);
        dormStay.setStayStatus(StayStatus.WAITING);
        dormStay.setRoom(availableRoom);

        availableRoom.setCapacity(availableRoom.getCapacity() - 1);
        roomRepository.save(availableRoom);

        application.setStatus(ApplicationStatus.ACCEPTED);
        roomApplicationRepository.save(application);

        return dormStayRepository.save(dormStay);
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
