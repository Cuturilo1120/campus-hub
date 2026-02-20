package dorm.controller;

import dorm.model.entity.Pavilion;
import dorm.service.PavilionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pavilions")
@RequiredArgsConstructor
public class PavilionController {

    @Autowired
    private PavilionService pavilionService;

    @GetMapping
    public List<Pavilion> getAll() {
        return pavilionService.getAllPavilions();
    }

    @GetMapping("/{id}")
    public Pavilion getOne(@PathVariable Long id) {
        return pavilionService.getPavilionById(id);
    }

    @PostMapping
    public Pavilion create(@RequestBody Pavilion pavilion) {
        return pavilionService.save(pavilion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pavilionService.deletePavilion(id);
    }

}
