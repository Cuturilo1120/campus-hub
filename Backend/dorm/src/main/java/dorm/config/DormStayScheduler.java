package dorm.config;

import dorm.model.entity.DormStay;
import dorm.model.entity.Room;
import dorm.model.entity.StayStatus;
import dorm.repository.DormStayRepository;
import dorm.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DormStayScheduler {

    @Autowired
    private DormStayRepository dormStayRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void activateStays() {
        List<DormStay> stays = dormStayRepository
                .findByStayStatusAndMoveInDateLessThanEqual(StayStatus.WAITING, new Date());
        for (DormStay stay : stays) {
            stay.setStayStatus(StayStatus.ACTIVE);
            dormStayRepository.save(stay);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void finishStays() {
        List<DormStay> stays = dormStayRepository
                .findByStayStatusAndMoveOutDateLessThanEqual(StayStatus.ACTIVE, new Date());
        for (DormStay stay : stays) {
            stay.setStayStatus(StayStatus.FINISHED);
            dormStayRepository.save(stay);

            Room room = stay.getRoom();
            if (room != null) {
                room.setCapacity(room.getCapacity() + 1);
                roomRepository.save(room);
            }
        }
    }

}
