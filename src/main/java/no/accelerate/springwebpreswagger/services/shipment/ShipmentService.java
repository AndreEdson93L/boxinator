package no.accelerate.springwebpreswagger.services.shipment;

import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.services.CrudService;

import java.util.List;

public interface ShipmentService extends CrudService<Shipment, Integer> {

    List<Shipment> findCreatedShipments(User customer);

    List<Shipment> findIntransitShipments(User customer);

    List<Shipment> findReceivedShipments(User customer);

    List<Shipment> findCompletedShipments(User customer);

    List<Shipment> findByCustomerId(Integer id);
    String getReceiverName(Integer id);

    String getWeight(Integer id);

    String getBoxColor(Integer id);

    String getDestination(Integer id);
}
