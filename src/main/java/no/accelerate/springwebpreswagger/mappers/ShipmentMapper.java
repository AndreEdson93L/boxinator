package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.dto.shipment.ShipmentDTO;
import no.accelerate.springwebpreswagger.models.dto.shipment.ShipmentPostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface ShipmentMapper {
    @Mapping(source = "customer", target = "customerDTO")
    ShipmentDTO mapShipmentToShipmentDTO(Shipment shipment);
    @Mapping(source = "customerId", target = "customer.id")
    Shipment mapShipmentPostDTOToShipment(ShipmentPostDTO shipmentPostDTO);
    @Mapping(source = "customer.id", target = "customerId")
    ShipmentPostDTO mapShipmentToShipmentPostDTO(Shipment shipment);

    //ShipmentMapper INSTANCE = Mappers.getMapper(ShipmentMapper.class);
}

