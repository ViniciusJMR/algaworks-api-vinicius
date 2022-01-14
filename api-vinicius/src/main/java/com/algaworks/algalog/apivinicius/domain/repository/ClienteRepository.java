package com.algaworks.algalog.apivinicius.domain.repository;

import com.algaworks.algalog.apivinicius.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /*Conhecido como Query method e a implementação será feita automaticamente pelo framework
        <Sufixovalido>By<Atributo>
     */
    List<Cliente> findByName(String name);
    List<Cliente> findByNameContaining(String partialName);
}
