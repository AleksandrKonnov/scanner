package com.example.scanner.controller;

import com.example.scanner.domain.ScanResult;
import com.example.scanner.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ScanController {

    @Autowired
    private ScanService scanService;

    @GetMapping
    public String start() {
        return "main";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam String name, Map<String, Object> model) {
        List<ScanResult> scanResults = scanService.filter(name);
        model.put("scanResults", scanResults);
        return "main";
    }

}
