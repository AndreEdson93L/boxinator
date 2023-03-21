package no.accelerate.springwebpreswagger.services.shipment;

import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.services.CrudService;

import java.util.List;

public interface ShipmentService extends CrudService<Shipment, Integer> {

    List<Shipment> findCreatedShipments(Integer id);
    List<Shipment> findIntransitShipments(Integer id);
    List<Shipment> findReceivedShipments(Integer id);
    List<Shipment> findCompletedShipments(Integer id);
    List<Shipment> findCompletedShipments();
    List<Shipment> findCancelledShipments(Integer id);
    List<Shipment> findCancelledShipments();
    List<Shipment> findByCustomerId(Integer id);
    String getReceiverName(Integer id);
    String getWeight(Integer id);
    String getBoxColor(Integer id);
    String getDestination(Integer id);
    Shipment findByIdAndCustomerId(Integer id, Integer customerId);
    List<Shipment> findAllShipmentsByCustomerId(int id);
}
