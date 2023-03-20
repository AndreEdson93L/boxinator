package no.accelerate.springwebpreswagger.models.dto.shipment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import no.accelerate.springwebpreswagger.models.Shipment;
import java.time.LocalDateTime;

@Getter
@Setter
public class ShipmentPostDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer customerId;
    private Shipment.ShipmentStatus status;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdDate;
    private String weight;
    private String boxColor;
    private String receiver;
    private String destination;

    public enum ShipmentStatus {
        CREATED, RECEIVED, INTRANSIT, COMPLETED, CANCELLED
    }
}
