package com.example.scanner.repository;

import com.example.scanner.domain.ScanResult;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScanResultRepository extends CrudRepository<ScanResult, Integer> {

    List<ScanResult> findFirst10ByNameContaining(String name);

    @Modifying
    @Query(value = "TRUNCATE TABLE scan_result RESTART IDENTITY", nativeQuery = true)
    void deleteAll();

}
