package com.capgemini.employeeassets.repository;

import com.capgemini.employeeassets.entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Assets,Long> {
    List<Assets> findAllByEmployeeUserId(long userId);
    void deleteByEmployeeUserId(Long userId);
    Optional<Assets> findByEmployeeUserId(long userId);
    Optional<Assets> findBySerialNumber(long serialNumber);
}
