package com.ssafy.a605.repository;

import com.ssafy.a605.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

        Optional<User> findByEmail(String email);
        void deleteByEmail(String email);

}
