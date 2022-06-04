package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.InputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping("/add")
    public Result addInput(@RequestBody InputDTO inputDTO) {
        return inputService.addInput(inputDTO);
    }

    @GetMapping("/get/all")
    public Result getAllInputs() {
        return inputService.getAllInputs();
    }

    @GetMapping("/get/{id}")
    public Result getOneInput(@PathVariable Integer id) {
        return inputService.getOneInput(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteInput(@PathVariable Integer id) {
        return inputService.deleteInput(id);
    }

    @PutMapping("/edit/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDTO inputDTO) {
        return inputService.editInput(id, inputDTO);
    }
}
