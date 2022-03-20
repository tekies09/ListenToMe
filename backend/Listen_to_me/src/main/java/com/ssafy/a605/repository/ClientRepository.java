package com.ssafy.a605.repository;

import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByEmail(String email);
    void deleteByEmail(String email);

}
