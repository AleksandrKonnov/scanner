package com.example.scanner.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ScanResult {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(columnDefinition = "text")
    private String absolutePath;

    public ScanResult(String name, String absolutePath) {
        this.name = name;
        this.absolutePath = absolutePath;
    }
}
