package com.genesis.ittyab.randomstringgenerator.repositories;

import com.genesis.ittyab.randomstringgenerator.entities.RandomString;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomStringGeneratorRepository extends JpaRepository<RandomString, Long> {

  List<RandomString> findAllByCreatedData(LocalDate localDate);
}
