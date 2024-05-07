package com.genesis.ittyab.randomstringgenerator.controllers;

import com.genesis.ittyab.randomstringgenerator.dtos.GenerateRandomStringsRequestDto;
import com.genesis.ittyab.randomstringgenerator.entities.RandomString;
import com.genesis.ittyab.randomstringgenerator.services.RandomStringGeneratorService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for generating random strings.
 */
@RestController
@RequiredArgsConstructor
public class RandomStringGeneratorController {

  /**
   * Service for generating random strings.
   */
  private final RandomStringGeneratorService randomStringGeneratorService;

  /**
   * Endpoint for generating an array of random strings based on the provided request parameters.
   *
   * @param request The request object containing parameters for generating random strings.
   * @return ResponseEntity with HTTP status 200 and an array of random strings.
   */
  @PostMapping("/api/v1/randomsStringGenerator/generate")
  public ResponseEntity<String[]> generateRandomString(@RequestBody GenerateRandomStringsRequestDto request) {
    // Generate random string array using the provided request parameters
    var randomStringArray =
        randomStringGeneratorService.generateRandomStringArray(request.getTargetSize(), request.getLength(), request.isIncludeLowercase(),
            request.isIncludeUppercase(), request.isIncludeNumbers());
    // Return the generated random string array as a response
    return ResponseEntity.ok(randomStringArray);
  }

  @GetMapping("/api/v1/randomsStringGenerator/generate/findAll")
  public ResponseEntity<List<RandomString>> findAll(){
    return ResponseEntity.ok(randomStringGeneratorService.findAll());
  }

  @GetMapping("/api/v1/randomsStringGenerator/generate/findByCreatedDate/{createdDate}")
  public ResponseEntity<List<RandomString>> finAllByCreatedDate(@PathVariable("createdDate") LocalDate createdDate){
    return ResponseEntity.ok(randomStringGeneratorService.findAllByCreatedData(createdDate));
  }

}
