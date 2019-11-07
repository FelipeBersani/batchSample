package br.com.example.testesspringboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Planet")
public class Planet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "actor_Planet", joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "planet_Id", referencedColumnName = "id"))
  private List<Ator> atores;

}
