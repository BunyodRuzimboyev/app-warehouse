package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.MeasurementDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        return measurementService.addMeasurementService(measurement);
    }

    // GET LIST, GET ONE, EDIT, DELETE

    @GetMapping
    public List<Measurement> getMeasurements() {
        return measurementService.getMeasurementList();
    }

    @GetMapping("/{id}")
    public Result getMeasurement(@PathVariable Integer id) {
        return measurementService.getOneMeasurement(id);
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@RequestBody MeasurementDTO measurementDTO, @PathVariable Integer id) {
        return measurementService.editMeasurement(measurementDTO, id);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        return measurementService.deleteMeasurement(id);
    }

}
