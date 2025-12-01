package br.com.cdb.bancodigital.repository;

import br.com.cdb.bancodigital.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository <Cartao, Long> {
}
