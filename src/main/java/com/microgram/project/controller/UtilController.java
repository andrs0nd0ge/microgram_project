package com.microgram.project.controller;

import com.microgram.project.util.UtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
@RequiredArgsConstructor
public class UtilController {
    private final UtilityClass util;
    @PostMapping("/users/create")
    public ResponseEntity<String> createUsersTable() {
        return new ResponseEntity<>(util.createUsersTable(), HttpStatus.OK);
    }
    @PostMapping("/users/insert")
    public ResponseEntity<String> insertIntoUsersTable() {
        return new ResponseEntity<>(util.insertIntoUsers(), HttpStatus.OK);
    }
    @PostMapping("/users/update")
    public ResponseEntity<String> updateUsersTable() {
        return new ResponseEntity<>(util.updateUsers(), HttpStatus.OK);
    }
    @PostMapping("/subs/create")
    public ResponseEntity<String> createSubsTable() {
        return new ResponseEntity<>(util.createSubscriptionsTable(), HttpStatus.OK);
    }
    @PostMapping("/subs/insert")
    public ResponseEntity<String> insertIntoSubsTable() {
        return new ResponseEntity<>(util.insertIntoSubs(), HttpStatus.OK);
    }
    @PostMapping("/posts/create")
    public ResponseEntity<String> createPostsTable() {
        return new ResponseEntity<>(util.createPostsTable(), HttpStatus.OK);
    }
    @PostMapping("/posts/insert")
    public ResponseEntity<String> insertIntoPostsTable() {
        return new ResponseEntity<>(util.insertIntoPosts(), HttpStatus.OK);
    }
    @PostMapping("/likes/create")
    public ResponseEntity<String> createLikesTable() {
        return new ResponseEntity<>(util.createLikesTable(), HttpStatus.OK);
    }
    @PostMapping("/likes/insert")
    public ResponseEntity<String> insertIntoLikesTable() {
        return new ResponseEntity<>(util.insertIntoLikes(), HttpStatus.OK);
    }
    @PostMapping("/comments/create")
    public ResponseEntity<String> createCommentsTable() {
        return new ResponseEntity<>(util.createCommentsTable(), HttpStatus.OK);
    }
    @PostMapping("/comments/insert")
    public ResponseEntity<String> insertIntoCommentsTable() {
        return new ResponseEntity<>(util.insertIntoComments(), HttpStatus.OK);
    }
}
