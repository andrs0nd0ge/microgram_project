package com.microgram.project.controller;

import com.microgram.project.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@RequiredArgsConstructor
public class MainController {
    private final DbService dbService;

    @GetMapping("/connect")
    public ResponseEntity<String> getConnection() {
        return new ResponseEntity<>(dbService.openConnection(), HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<String> createTable() {
        return new ResponseEntity<>(dbService.shouldCreateTable(), HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<String> selectData() {
        return new ResponseEntity<>(dbService.shouldSelectData(), HttpStatus.OK);
    }

    @GetMapping("/meta")
    public ResponseEntity<String> getMetaData() {
        return new ResponseEntity<>(dbService.getMetaData(), HttpStatus.OK);
    }
}
