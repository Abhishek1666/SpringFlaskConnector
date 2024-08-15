package com.diseasepredictor.detection.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.diseasepredictor.detection.dto.DiseasePredictionResponseDTO;
import com.diseasepredictor.detection.dto.SymptomsDTO;

@RestController
@RequestMapping("/api")
public class DiseasePredictionController {

    private static final Logger logger = LoggerFactory.getLogger(DiseasePredictionController.class);

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    private final RestTemplate restTemplate;

    public DiseasePredictionController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/predict")
    public ResponseEntity<DiseasePredictionResponseDTO> predictDisease(@RequestBody SymptomsDTO symptomsDTO) {
        logger.info("Received request to predict disease with symptoms: {}", symptomsDTO);

        try {
            // Forward the request to the Flask API and receive the response
            ResponseEntity<DiseasePredictionResponseDTO> response = restTemplate.postForEntity(flaskApiUrl, symptomsDTO, DiseasePredictionResponseDTO.class);

            logger.info("Received response from Flask API: {}", response);

            // Return the Flask API's response
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            logger.error("Error occurred while predicting disease", e);

            // Handle errors and return a 500 status with a message
            DiseasePredictionResponseDTO errorResponse = new DiseasePredictionResponseDTO();
            errorResponse.setPredicted_disease("Error: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
