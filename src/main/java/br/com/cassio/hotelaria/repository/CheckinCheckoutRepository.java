package br.com.cassio.hotelaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cassio.hotelaria.model.CheckinCheckout;

@Repository

public interface CheckinCheckoutRepository extends JpaRepository<CheckinCheckout, Long> {

	Optional<CheckinCheckout> findByPessoaIdAndPessoaExcluirFalse(Long pessoaId);

	void deleteByPessoaId(Long id);

}
