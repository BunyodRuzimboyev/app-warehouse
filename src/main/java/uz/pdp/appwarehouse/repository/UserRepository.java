package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByPhoneNumberOrCode(String phoneNumber, String code);

    List<Warehouse> findByWarehousesId(Integer warehouses_id);
}
