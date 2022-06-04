package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.OutputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping("/add")
    public Result addOutput(@RequestBody OutputDTO outputDTO) {
        return outputService.addOutput(outputDTO);
    }

    @GetMapping("/get/all")
    public Result getAllOutputs(){
        return outputService.getAllOutputs();
    }

    @GetMapping("/get/{id}")
    public Result getOneOutput(@PathVariable Integer id){
        return outputService.getOneOutput(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteOneOutput(@PathVariable Integer id){
        return outputService.getOneOutput(id);
    }

    @PutMapping("/edit/{id}")
    public Result editOutput(@PathVariable Integer id, @RequestBody OutputDTO outputDTO){
        return  outputService.editOutput(id, outputDTO);
    }
}
