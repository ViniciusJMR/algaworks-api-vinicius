package com.algaworks.algalog.apivinicius.domain.repository;

import com.algaworks.algalog.apivinicius.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega,Long> {
}
