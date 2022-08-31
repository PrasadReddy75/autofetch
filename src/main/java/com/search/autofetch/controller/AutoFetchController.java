package com.search.autofetch.controller;

import com.search.autofetch.service.AutoFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AutoFetchController {

    @Autowired
    AutoFetchService autoFetchService;

    @GetMapping(value = "/search")
    public ResponseEntity<List<String>> search(@RequestParam String searchKey) {
        List<String> top5Results = null;
        if (searchKey == null || searchKey.length() < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid search input");
        }
        top5Results = autoFetchService.getSearchResults(searchKey);
        return new ResponseEntity<>(top5Results, HttpStatus.OK);
    }
}
