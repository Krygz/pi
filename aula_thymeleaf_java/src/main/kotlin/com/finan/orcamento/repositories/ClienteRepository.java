package com.finan.orcamento.repositories;

import com.finan.orcamento.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    List<ClienteModel> findByNomeContainingIgnoreCase(String nome);
    List<ClienteModel> findByCpfContaining(String cpf);
    Optional<ClienteModel> findByCpf(String cpf);
}


