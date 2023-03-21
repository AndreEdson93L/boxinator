package no.accelerate.springwebpreswagger.models.dto.shipment;

import lombok.Getter;
import lombok.Setter;
import no.accelerate.springwebpreswagger.models.Shipment;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShipmentDTO {
    private Integer id;
    private Shipment.ShipmentStatus status;
    private LocalDateTime createdDate;
    private String weight;
    private String boxColor;
    private String receiver;
    private String destination;

    public enum ShipmentStatus {
        CREATED, RECIEVED, INTRANSIT, COMPLETED, CANCELLED
    }
}
