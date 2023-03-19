package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.dto.user.ShipmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface ShipmentMapper {
    @Mapping(source = "customer", target = "customerDTO")
    ShipmentDTO mapShipmentToShipmentDTO(Shipment shipment);
}
