package com.example.scanner.service;

import com.example.scanner.domain.ScanResult;

import java.util.List;

public interface ScanService {

    List<ScanResult> filter(String name);

    void deleteAll();

    void saveAll(List<ScanResult> scanResults);

}
