package com.thepracticaldeveloper.devgame.modules.retriever.controller;

import com.thepracticaldeveloper.devgame.modules.retriever.service.SonarDataRetriever;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retriever")
public class RetrieverController {

    private final SonarDataRetriever sonarDataRetriever;

    public RetrieverController(final SonarDataRetriever retriever) {
        this.sonarDataRetriever = retriever;
    }

    @PostMapping("/now")
    public ResponseEntity<String> retrieveNow() {
        sonarDataRetriever.retrieveData();
        return ResponseEntity.ok().body("requested");
    }
}
