package com.genesis.ittyab.randomstringgenerator.dtos;

import lombok.Data;

/**
 * This class represents a request DTO for generating random strings.
 * It contains parameters to control the generation process.
 */
@Data
public class GenerateRandomStringsRequestDto {

  /**
   * The target size of the generated random strings.
   */
  int targetSize;

  /**
   * Indicates whether uppercase letters should be included in the generated strings.
   */
  boolean includeUppercase;

  /**
   * The length of each generated random string.
   */
  int length;

  /**
   * Indicates whether lowercase letters should be included in the generated strings.
   */
  boolean includeLowercase;

  /**
   * Indicates whether numbers should be included in the generated strings.
   */
  boolean includeNumbers;

  /**
   * Indicates whether strings should be uniquely generated.
   */
  boolean uniqueStrings;
}
