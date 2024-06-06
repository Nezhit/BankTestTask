package com.example.bankservice.repos;

import com.example.bankservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {
    Optional<Client> findByLogin(String login);
    @Query("SELECT c FROM Client c WHERE " +
            "(:name IS NULL OR c.name LIKE :name) AND " +
            "(:surname IS NULL OR c.surname LIKE :surname) AND " +
            "(:papaname IS NULL OR c.papaname LIKE :papaname)")
    List<Client> findByFIO(@Param("name") String name,
                           @Param("surname") String surname,
                           @Param("papaname") String papaname);
    boolean existsByLogin(String login);
}
