package br.com.cassio.hotelaria.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.hotelaria.model.PessoaFisica;
import br.com.cassio.hotelaria.model.dto.PessoaFisicaSaveDTO;
import br.com.cassio.hotelaria.model.enumeration.TipoPessoa;

@Mapper(
		componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		uses = {},
		imports = {
				TipoPessoa.class })
public interface PessoaFisicaSaveMapper extends EntityMapper<PessoaFisica, PessoaFisicaSaveDTO> {
	PessoaFisicaSaveMapper INSTANCE = Mappers.getMapper(PessoaFisicaSaveMapper.class);

	@Mapping(target = "tipoPessoa", expression = "java(TipoPessoa.FISICA)")
	@Mapping(target = "excluir", expression = "java(false)")
	PessoaFisica toEntity(PessoaFisicaSaveDTO dto);

}
