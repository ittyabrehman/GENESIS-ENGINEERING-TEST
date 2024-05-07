package com.genesis.ittyab.randomstringgenerator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "RANDOM_STRING")
@Table(name = "RANDOM_STRING")
@Getter
@Setter
@NoArgsConstructor
public class RandomString {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "RANDOM_STRING", columnDefinition = "VARCHAR(100)", nullable = false)
  private String randomString;

  @Column(name = "CREATE_DATE", columnDefinition = "timestamp", nullable = false)
  private LocalDate createdData;

  public RandomString(String randomString) {
    this.randomString = randomString;
    this.createdData = LocalDate.now();
  }

}
