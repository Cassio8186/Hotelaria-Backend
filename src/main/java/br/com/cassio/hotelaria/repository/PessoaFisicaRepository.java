package br.com.cassio.hotelaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cassio.hotelaria.model.PessoaFisica;

@Repository

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
	Boolean existsByCpfAndExcluirFalse(String cpf);

	List<PessoaFisica> findAllByExcluirFalse();

	Optional<PessoaFisica> findByCpfAndExcluirFalse(String cpf);

	Optional<PessoaFisica> findByIdAndExcluirFalse(Long id);

}
