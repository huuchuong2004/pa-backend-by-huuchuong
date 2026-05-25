package vn.huuchuong.pabackbyhuuchuong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.huuchuong.pabackbyhuuchuong.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);
    User findByUsername(String username);


}
