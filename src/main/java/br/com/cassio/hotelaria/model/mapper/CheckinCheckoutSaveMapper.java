package br.com.cassio.hotelaria.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.cassio.hotelaria.model.CheckinCheckout;
import br.com.cassio.hotelaria.model.dto.CheckinCheckoutSaveDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {})
public interface CheckinCheckoutSaveMapper extends EntityMapper<CheckinCheckout, CheckinCheckoutSaveDTO> {
	CheckinCheckoutSaveMapper INSTANCE = Mappers.getMapper(CheckinCheckoutSaveMapper.class);

	@Override
	@Mapping(source = "pessoa.id", target = "idPessoa")
	CheckinCheckoutSaveDTO toDto(CheckinCheckout checkinCheckout);

	@Override
	@InheritInverseConfiguration
	CheckinCheckout toEntity(CheckinCheckoutSaveDTO checkinCheckoutDto);
}
