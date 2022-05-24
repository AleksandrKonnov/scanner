package com.example.scanner.before_start;

import com.example.scanner.domain.ScanResult;
import com.example.scanner.service.ScanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.concurrent.*;

@Component
@Slf4j
public class BeforeStart {

    @Autowired
    private ScanService scanService;

    @Value("${scanner.startup-scan-path}")
    private String scanPath;

    @Value("${scanner.parallel-thread-count}")
    private int threadCount;

    private ExecutorService threadPool;

    private List<ScanResult> scanResults = new CopyOnWriteArrayList<>();

    private Phaser phaser = new Phaser(1);


    @PostConstruct
    public void runScanning() {
        log.info("Scanning starting...");

        deletePreviousScanResults();
        log.info("Previous scan results deleted");

        findAllFiles();
        log.info("Files found: " + scanResults.size());

        saveNewScanResults();
        log.info("Scanning finished");
    }

    private void deletePreviousScanResults() {
        scanService.deleteAll();
    }

    private void findAllFiles() {
        threadPool = Executors.newFixedThreadPool(threadCount);
        findFilesInPath(scanPath);
        phaser.awaitAdvance(0);
        threadPool.shutdown();
    }

    void findFilesInPath(String path) {
        File root = new File(path);

        if (root.exists()) {
            File[] files = root.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        phaser.register();
                        threadPool.execute(() -> findFilesInPath(file.getAbsolutePath()));
                    } else {
                        ScanResult scanResult = new ScanResult(file.getName(), file.getAbsolutePath());
                        scanResults.add(scanResult);
                    }
                }
            }
        } else {
            log.warn("Path " + path + " does not exist!");
        }
        phaser.arriveAndDeregister();
    }

    private void saveNewScanResults() {
        scanService.saveAll(scanResults);
    }

}