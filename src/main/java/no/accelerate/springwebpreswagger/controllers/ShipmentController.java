package no.accelerate.springwebpreswagger.controllers;

import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.services.shipment.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        return new ResponseEntity<List<Shipment>>((List<Shipment>) shipmentService.findAll(), HttpStatus.OK);
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
