package br.com.example.testesspringboot.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Ator {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "ator_filme",
  joinColumns = @JoinColumn(name = "ator_id", referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "filme_id", referencedColumnName = "id"))
  private List<Filme> filmes = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atores")
  private List<Planet> planetas = new ArrayList<>();

}
