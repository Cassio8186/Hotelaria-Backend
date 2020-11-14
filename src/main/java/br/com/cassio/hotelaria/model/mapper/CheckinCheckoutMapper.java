package br.com.cassio.hotelaria.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.hotelaria.model.CheckinCheckout;
import br.com.cassio.hotelaria.model.dto.CheckinCheckoutDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface CheckinCheckoutMapper extends EntityMapper<CheckinCheckout, CheckinCheckoutDTO> {
	CheckinCheckoutMapper INSTANCE = Mappers.getMapper(CheckinCheckoutMapper.class);

	@Override
	@Mapping(source = "pessoa.id", target = "idPessoa")
	CheckinCheckoutDTO toDto(CheckinCheckout checkinCheckout);

	@Override
	@InheritInverseConfiguration
	CheckinCheckout toEntity(CheckinCheckoutDTO checkinCheckoutDto);
}
