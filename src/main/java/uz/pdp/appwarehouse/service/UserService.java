package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDTO;
import uz.pdp.appwarehouse.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public Result addUser(UserDTO userDTO) {
        boolean exists = userRepository.existsByPhoneNumberOrCode(userDTO.getPhoneNumber(), userDTO.getCode());
        if (exists)
            return new Result("User's phone number or code already exist ...", false);


        List<Warehouse> byWarehousesId = userRepository.findByWarehousesId(userDTO.getWarehouseId());
        if (byWarehousesId.size() == 0)
            return new Result("Warehouse not find ...", false);
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCode(userDTO.getCode());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.getActive());
        user.setWarehouses((Set<Warehouse>) byWarehousesId);
        User savedUser = userRepository.save(user);
        return new Result("User added ...", true, savedUser);
    }

    public Result getAllUsers() {
        List<User> all = userRepository.findAll();
        return new Result("All users ...", true, all);
    }

    public Result getOneUser(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent())
            return new Result("User not found ...", false);

        User user = byId.get();
        return new Result("User ...", true, user);
    }

    public Result deleteUser(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent())
            return new Result("User not found ...", false);

        userRepository.deleteById(id);
        return new Result("User deleted ...", true);
    }

    public Result editUser(Integer id, UserDTO userDTO) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent())
            return new Result("User not found ...", false);
        boolean exists = userRepository.existsByPhoneNumberOrCode(userDTO.getPhoneNumber(), userDTO.getCode());
        if (exists)
            return new Result("User's phone number or code already exist ...", false);


        List<Warehouse> byWarehousesId = userRepository.findByWarehousesId(userDTO.getWarehouseId());
        if (byWarehousesId.size() == 0)
            return new Result("Warehouse not find ...", false);
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCode(userDTO.getCode());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.getActive());
        user.setWarehouses((Set<Warehouse>) byWarehousesId);
        User savedUser = userRepository.save(user);
        return new Result("User added ...", true, savedUser);
    }


}
