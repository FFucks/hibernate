package com.ffucks.repositories;

import com.ffucks.entities.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findById(Long id);

    @EntityGraph(attributePaths = {"employees", "clients"})
    Optional<Store> findWithEmployeesAndClientsById(Long id);
}
