package br.com.example.testesspringboot.controller;

import br.com.example.testesspringboot.domain.Dominio;
import br.com.example.testesspringboot.dto.DominioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/teste")
public class POCController {

  @Autowired
  private ModelMapper mapper;

  @PostMapping
  public ResponseEntity<Void> teste(@RequestBody Dominio dom) {
    System.out.println(dom.toString());

    DominioDTO dto = mapper.map(dom, DominioDTO.class);

    System.out.println(dto.toString());

    return ok().build();
  }

}
