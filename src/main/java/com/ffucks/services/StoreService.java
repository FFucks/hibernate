package com.ffucks.services;

import com.ffucks.entities.Store;
import com.ffucks.repositories.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    @Transactional
    public Optional<Store> getStoreWithRelations(Long id) {
        return storeRepository.findWithEmployeesAndClientsById(id);
    }

    @Transactional
    public Store save(Store store) {
        return storeRepository.save(store);
    }
}
