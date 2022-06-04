package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.ClientDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;
import uz.pdp.appwarehouse.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/add")
    public Result addClient(@RequestBody ClientDTO clientDTO) {
        return clientService.addClient(clientDTO);
    }

    @GetMapping("/list")
    public Result getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/list/{id}")
    public Result getOneClient(@PathVariable Integer id) {
        return clientService.getOneClient(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteClient(@PathVariable Integer id) {
        return clientService.deleteClient(id);
    }

    @PutMapping("/edit/{id}")
    public Result editClient(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        return clientService.editClient(id, clientDTO);
    }


}
