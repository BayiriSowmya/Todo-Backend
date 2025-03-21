package com.iguruu.mydb.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iguruu.mydb.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName); // Fixed Optional Return
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
