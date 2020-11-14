package br.com.cassio.hotelaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cassio.hotelaria.model.Pessoa;

@Repository

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
