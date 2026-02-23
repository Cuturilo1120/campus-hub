package dorm.controller;

import dorm.model.entity.Dorm;
import dorm.service.DormService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dorms")
@RequiredArgsConstructor
public class DormController {

    @Autowired
    private DormService dormService;

    @GetMapping
    public List<Dorm> getAll() {
        return dormService.getAllDorms();
    }

    @GetMapping("/{id}")
    public Dorm getOne(@PathVariable Long id) {
        return dormService.getDormById(id);
    }

    @PostMapping
    public Dorm create(@RequestBody Dorm dorm) {
        return dormService.save(dorm);
    }

    @GetMapping("/{id}/capacity")
    public int getCapacity(@PathVariable Long id) {
        return dormService.getDormCapacity(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dormService.deleteDorm(id);
    }

}
