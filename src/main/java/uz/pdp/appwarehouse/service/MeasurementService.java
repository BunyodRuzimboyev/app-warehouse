package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.MeasurementDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("This measurement is already exist!", false);
        measurementRepository.save(measurement);
        return new Result("Measurement is added.", true);
    }

    public List<Measurement> getMeasurementList() {
        List<Measurement> all = measurementRepository.findAll();
        return all;
    }

    public Result getOneMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement is not found!", false);
        return new Result("Measurement...", true, optionalMeasurement.get());

    }

    public Result editMeasurement(MeasurementDTO measurementDTO, Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement is not found!", false);
        Measurement measurement = new Measurement();
        measurement.setName(measurementDTO.getName());
        measurement.setActive(measurementDTO.getActive());
        Measurement savedMeasurement = measurementRepository.save(measurement);
        return new Result("Measurement is edited.", true);
    }

    public Result deleteMeasurement(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement is not found!", false);
        measurementRepository.deleteById(id);
        return new Result("Measurement is deleted.", true);
    }
}
