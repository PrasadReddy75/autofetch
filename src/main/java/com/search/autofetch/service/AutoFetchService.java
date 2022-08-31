package com.search.autofetch.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoFetchService {

    @Value("${search.data.url}")
    private String url;
    List<String> searchedWords = null;

    @PostConstruct
    public void loadData() {
        RestTemplate restTemplate = new RestTemplate();
        String searchData = restTemplate.getForObject(url, String.class);
        searchedWords = Arrays.asList(searchData.split("\r\n"));
    }

    public List<String> getSearchResults(String searchKey) {
        Comparator<String> compByLength = Comparator.comparingInt(String::length).reversed();
        return searchedWords.stream().filter(word -> (word.startsWith(searchKey))).sorted(compByLength).limit(5).collect(Collectors.toList());
    }
}
