package com.bancoeconomico.repository;

import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByCliente(Cliente cliente);
}
