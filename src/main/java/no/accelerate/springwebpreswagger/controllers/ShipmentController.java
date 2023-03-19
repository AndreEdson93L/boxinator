package no.accelerate.springwebpreswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.springwebpreswagger.mappers.CustomerMapper;
import no.accelerate.springwebpreswagger.mappers.ShipmentMapper;
import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.dto.user.ShipmentDTO;
import no.accelerate.springwebpreswagger.services.shipment.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;
    private ShipmentMapper shipmentMapper;
    private CustomerMapper customerMapper;
    @Autowired
    public ShipmentController(ShipmentService shipmentService, ShipmentMapper shipmentMapper, CustomerMapper customerMapper)
    {
        this.shipmentService = shipmentService;
        this.shipmentMapper = shipmentMapper;
        this.customerMapper = customerMapper;
    }
    @GetMapping
    @Operation(summary = "Get all shipments")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Shipments retrieved successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "No shipment found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            )
    })
    public ResponseEntity<List<ShipmentDTO>> getAllShipments() {
        List<Shipment> shipments = (List<Shipment>) shipmentService.findAll();
        List<ShipmentDTO> shipmentDTOs = shipments.stream()
                .map(shipmentMapper::mapShipmentToShipmentDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<List<ShipmentDTO>>(shipmentDTOs, HttpStatus.OK);
    }


    @GetMapping("/complete")
    public ResponseEntity<List<Shipment>> getCompletedShipments(@PathVariable("customer_id") Integer id) {
        List<Shipment> completedShipments = shipmentService.findCompletedShipments(id);
        return new ResponseEntity<List<Shipment>>(completedShipments, HttpStatus.OK);
    }

    @GetMapping("/cancelled")
    public ResponseEntity<?> getCancelledShipments(@PathVariable("customer_id") Integer id) {
        List<Shipment> cancelledShipments = shipmentService.findCancelledShipments(id);
        return new ResponseEntity<>(cancelledShipments, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        return new ResponseEntity<>(shipmentService.add(shipment), HttpStatus.CREATED);
    }

    @GetMapping("/{shipment_id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable("shipment_id") Integer id) {
        return new ResponseEntity<>(shipmentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<List<Shipment>> getAllShipmentsByCustomerId(@PathVariable("customer_id") Integer id) {

        List<Shipment> shipments = shipmentService.findAllShipmentsByCustomerId(id);
        return new ResponseEntity<List<Shipment>>(shipments, HttpStatus.OK);
    }
    @PutMapping("/{shipment_id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable("shipment_id") Integer id,
                                                   @RequestBody Shipment updatedShipment) {
        Shipment existingShipment = shipmentService.findById(id);
        updatedShipment.setId(existingShipment.getId());
        return new ResponseEntity<>(shipmentService.update(updatedShipment), HttpStatus.OK);
    }
    @DeleteMapping("/{shipment_id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable("shipment_id") Integer id) {
        shipmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
