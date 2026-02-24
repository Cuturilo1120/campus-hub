package dorm.controller;

import dorm.model.dto.RoomApplicationRequest;
import dorm.model.entity.DormStay;
import dorm.model.entity.RoomApplication;
import dorm.service.RoomApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/roomApplications")
@RequiredArgsConstructor
public class RoomApplicationController {

    @Autowired
    private RoomApplicationService roomApplicationService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PRINCIPAL')")
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

    @PostMapping("/apply")
    @PreAuthorize("hasRole('STUDENT')")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomApplication apply(@RequestBody RoomApplicationRequest request) {
        Long studentId = (Long) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (studentId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Student identity not found in token");
        }
        return roomApplicationService.apply(studentId, request.dormId());
    }

    @PatchMapping("/{id}/accept")
    @PreAuthorize("hasRole('PRINCIPAL')")
    public DormStay accept(@PathVariable Long id) {
        return roomApplicationService.accept(id);
    }

    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasRole('PRINCIPAL')")
    public RoomApplication reject(@PathVariable Long id) {
        return roomApplicationService.reject(id);
    }

    @GetMapping("/mine")
    @PreAuthorize("hasRole('STUDENT')")
    public List<RoomApplication> getMyApplications() {
        Long studentId = (Long) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (studentId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Student identity not found in token");
        }
        return roomApplicationService.getMyApplications(studentId);
    }

    @GetMapping("/mine/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public RoomApplication getMyApplicationById(@PathVariable Long id) {
        Long studentId = (Long) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (studentId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Student identity not found in token");
        }
        return roomApplicationService.getMyApplicationById(studentId, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomApplicationService.deleteRoomApplication(id);
    }

}
