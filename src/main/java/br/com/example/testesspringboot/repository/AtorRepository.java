package br.com.example.testesspringboot.repository;

import br.com.example.testesspringboot.domain.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {

  @Query(value = "SELECT nome FROM ator where id=:id", nativeQuery = true)
  String findByName(@Param("id") Long id);
}
