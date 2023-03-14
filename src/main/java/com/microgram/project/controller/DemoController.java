package com.microgram.project.controller;

import com.microgram.project.service.PrimitiveDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final PrimitiveDbService primitiveDbService;

    @GetMapping("/connect")
    public ResponseEntity<String> getConnection() {
        return new ResponseEntity<>(primitiveDbService.openConnection(), HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<String> createTable() {
        return new ResponseEntity<>(primitiveDbService.shouldCreateTable(), HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<String> selectData() {
        return new ResponseEntity<>(primitiveDbService.shouldSelectData(), HttpStatus.OK);
    }

    @GetMapping("/meta")
    public ResponseEntity<String> getMetaData() {
        return new ResponseEntity<>(primitiveDbService.getMetaData(), HttpStatus.OK);
    }
}
