package com.example.scanner.service.impl;

import com.example.scanner.domain.ScanResult;
import com.example.scanner.repository.ScanResultRepository;
import com.example.scanner.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanServiceImpl implements ScanService {

    @Autowired
    private ScanResultRepository scanResultRepository;

    @Override
    public List<ScanResult> filter(String name) {
        List<ScanResult> scanResults;
        if (name != null && !name.isEmpty()) {
            scanResults = scanResultRepository.findFirst10ByNameContaining(name);
        } else {
            scanResults = (List<ScanResult>) scanResultRepository.findAll();
        }
        return scanResults;
    }

    @Override
    public void deleteAll() {
        scanResultRepository.deleteAll();
    }

    @Override
    public void saveAll(List<ScanResult> scanResults) {
        scanResultRepository.saveAll(scanResults);
    }

}
