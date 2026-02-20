package dorm.controller;

import dorm.model.entity.RoomApplication;
import dorm.service.RoomApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomApplications")
@RequiredArgsConstructor
public class RoomApplicationController {

    @Autowired
    private RoomApplicationService roomApplicationService;

    @GetMapping
    public List<RoomApplication> getAll() {
        return roomApplicationService.getAllRoomApplications();
    }

    @GetMapping("/{id}")
    public RoomApplication getOne(@PathVariable Long id) {
        return roomApplicationService.getRoomApplicationById(id);
    }

    @PostMapping
    public RoomApplication create(@RequestBody RoomApplication roomApplication) {
        return roomApplicationService.save(roomApplication);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomApplicationService.deleteRoomApplication(id);
    }

}
