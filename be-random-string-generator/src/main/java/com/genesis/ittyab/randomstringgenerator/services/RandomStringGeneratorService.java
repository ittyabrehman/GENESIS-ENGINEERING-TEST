package com.genesis.ittyab.randomstringgenerator.services;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

/**
 * This class provides a service for generating unique random strings based on given parameters.
 */
@Service
public class RandomStringGeneratorService {
  private static final String CHAR_SET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String CHAR_SET_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String CHAR_SET_NUMBERS = "0123456789";

  /**
   * Validates the target size of the random strings to be generated.
   * Throws an IllegalArgumentException if the target size is less than or equal to zero.
   *
   * @param targetSize The number of random strings to generate.
   */
  private static void validateTargetSize(int targetSize) {
    if (targetSize <= 0) {
      throw new IllegalArgumentException("Target size must be greater than zero.");
    }
  }

  /**
   * Validates the length of the random strings to be generated.
   * Throws an IllegalArgumentException if the length is less than or equal to zero.
   *
   * @param length The length of each random string.
   * @throws IllegalArgumentException If the length is less than or equal to zero.
   */
  private static void validateLength(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("Length must be greater than zero.");
    }
  }

  /**
   * Validates the inclusion of character types in the random string generation.
   * Throws an IllegalArgumentException if none of the character types (lowercase, uppercase, numbers) are included.
   *
   * @param includeLowercase Whether to include lowercase characters in the random strings.
   * @param includeUppercase Whether to include uppercase characters in the random strings.
   * @param includeNumbers   Whether to include numbers in the random strings.
   * @throws IllegalArgumentException If none of the character types are included.
   */
  private static void validateCharacterInclusion(boolean includeLowercase, boolean includeUppercase, boolean includeNumbers) {
    if (!(includeLowercase || includeUppercase || includeNumbers)) {
      throw new IllegalArgumentException("At least one of includeLowercase, includeUppercase, or includeNumbers must be True.");
    }
  }

  /**
   * This method generates an array of unique random strings based on the given parameters.
   *
   * @param targetSize       The number of random strings to generate.
   * @param length           The length of each random string.
   * @param includeLowercase Whether to include lowercase characters in the random strings.
   * @param includeUppercase Whether to include uppercase characters in the random strings.
   * @param includeNumbers   Whether to include numbers in the random strings.
   * @return An array of unique random strings.
   * @throws IllegalArgumentException If the targetSize is less than or equal to zero.
   * @throws IllegalArgumentException If the length is less than or equal to zero.
   */
  public String[] generateRandomStringArray(int targetSize, int length, boolean includeLowercase, boolean includeUppercase,
                                            boolean includeNumbers) {
    validateTargetSize(targetSize);
    validateLength(length);
    validateCharacterInclusion(includeLowercase, includeUppercase, includeNumbers);

    String[] randomStrings = new String[targetSize];
    Set<String> usedStrings = new HashSet<>();
    Random random = ThreadLocalRandom.current();

    return generateUniqueStrings(randomStrings, usedStrings, random, length, getCharacterSet(includeLowercase, includeUppercase, includeNumbers),
        targetSize);
  }

  /**
   * This method generates unique random strings based on the given parameters.
   *
   * @param randomStrings The array where the generated strings will be stored.
   * @param usedStrings   A set to keep track of the generated strings to ensure uniqueness.
   * @param random        A Random object for generating random numbers.
   * @param length        The length of each random string.
   * @param characterSet  A StringBuilder object containing the character set to be used for generating random strings.
   * @param targetSize    The number of random strings to generate.
   * @return The array of unique random strings.
   * @throws IllegalArgumentException If the targetSize is less than or equal to zero.
   * @throws IllegalArgumentException If the length is less than or equal to zero.
   */
  private String[] generateUniqueStrings(String[] randomStrings, Set<String> usedStrings, Random random, int length, StringBuilder characterSet,
                                         int targetSize) {
    int generatedCount = 0;
    while (generatedCount < targetSize) {
      StringBuilder stringBuilder = new StringBuilder(length);
      for (int j = 0; j < length; j++) {
        int index = random.nextInt(characterSet.length());
        char randomChar = characterSet.charAt(index);
        stringBuilder.append(randomChar);
      }

      String randomString = stringBuilder.toString();
      if (usedStrings.add(randomString)) {
        randomStrings[generatedCount] = randomString;
        generatedCount++;
      }
    }
    return randomStrings;
  }


  /**
   * This method constructs a character set based on the given parameters.
   *
   * @param includeLowercase Whether to include lowercase characters in the character set.
   * @param includeUppercase Whether to include uppercase characters in the character set.
   * @param includeNumbers   Whether to include numbers in the character set.
   * @return A StringBuilder object containing the constructed character set.
   */
  private StringBuilder getCharacterSet(boolean includeLowercase, boolean includeUppercase, boolean includeNumbers) {
    StringBuilder characterSet = new StringBuilder();
    if (includeLowercase) {
      characterSet.append(CHAR_SET_LOWERCASE);
    }
    if (includeUppercase) {
      characterSet.append(CHAR_SET_UPPERCASE);
    }
    if (includeNumbers) {
      characterSet.append(CHAR_SET_NUMBERS);
    }
    return characterSet;
  }
}
