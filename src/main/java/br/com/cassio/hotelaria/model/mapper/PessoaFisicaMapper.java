package br.com.cassio.hotelaria.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.hotelaria.model.PessoaFisica;
import br.com.cassio.hotelaria.model.dto.PessoaFisicaDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})

public interface PessoaFisicaMapper extends EntityMapper<PessoaFisica, PessoaFisicaDTO> {
	PessoaFisicaMapper INSTANCE = Mappers.getMapper(PessoaFisicaMapper.class);

	@Mapping(target = "excluir", expression = "java(false)")
	PessoaFisica toEntity(PessoaFisicaDTO dto);
}
