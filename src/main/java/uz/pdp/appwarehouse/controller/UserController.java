package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDTO;
import uz.pdp.appwarehouse.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @GetMapping("/get/list/{id}")
    public Result getOneUser(@PathVariable Integer id) {
        return userService.getOneUser(id);
    }

    @GetMapping("/get/list")
    public Result getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/edit/{id}")
    public Result editUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }


}
