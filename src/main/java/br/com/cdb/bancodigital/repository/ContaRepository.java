package br.com.cdb.bancodigital.repository;

import br.com.cdb.bancodigital.entity.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository <ContaBancaria, Long> {
}
