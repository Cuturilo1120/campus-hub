package dorm.controller;

import dorm.model.entity.Room;
import dorm.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getAll() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getOne(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public Room create(@RequestBody Room room) {
        return roomService.save(room);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
