package no.accelerate.springwebpreswagger.models.dto.shipment;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Cost of the shipment", required = true)
    private Double cost;

    public enum ShipmentStatus {
        CREATED, RECIEVED, INTRANSIT, COMPLETED, CANCELLED
    }
}
