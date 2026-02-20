package dorm.controller;

import dorm.model.entity.DormStay;
import dorm.service.DormStayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dormStays")
@RequiredArgsConstructor
public class DormStayController {

    @Autowired
    private DormStayService dormStayService;

    @GetMapping
    public List<DormStay> getAll() {
        return dormStayService.getAllDormStay();
    }

    @GetMapping("/{id}")
    public DormStay getOne(@PathVariable Long id) {
        return dormStayService.getDormStayById(id);
    }

    @PostMapping
    public DormStay create(@RequestBody DormStay dormStay) {
        return dormStayService.save(dormStay);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dormStayService.deleteDormStay(id);
    }

}
