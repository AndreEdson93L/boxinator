package no.accelerate.springwebpreswagger.services.shipment;

import no.accelerate.springwebpreswagger.exceptions.ShipmentNotFoundException;
import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;


    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment findById(Integer id) {
        return shipmentRepository
                .findById(id)
                .orElseThrow(()
                        -> new ShipmentNotFoundException(id));
    }

    @Override
    public Collection<Shipment> findAll() {
        return shipmentRepository
                .findAll();
    }

    @Override
    public Shipment add(Shipment entity) {
        return shipmentRepository.save(entity);
    }

    @Override
    public Shipment update(Shipment entity) {
        return shipmentRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        shipmentRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return shipmentRepository.existsById(id);
    }

    @Override
    public List<Shipment> findCreatedShipments(Integer id) {
        return shipmentRepository.findCreatedShipments(id);
    }

    @Override
    public List<Shipment> findIntransitShipments(Integer id) {
        return shipmentRepository.findIntransitShipments(id);
    }

    @Override
    public List<Shipment> findReceivedShipments(Integer id) {
        return shipmentRepository.findReceivedShipments(id);
    }

    @Override
    public List<Shipment> findCompletedShipments(Integer id) {
        return shipmentRepository.findCompletedShipments(id);
    }

    @Override
    public List<Shipment> findCompletedShipments() {
        return shipmentRepository.findCompletedShipments();
    }

    @Override
    public List<Shipment> findCancelledShipments(Integer id) {
        return shipmentRepository.findCancelledShipments(id);
    }

    @Override
    public List<Shipment> findCancelledShipments() {
        return shipmentRepository.findCancelledShipments();
    }

    @Override
    public List<Shipment> findByCustomerId(Integer id) {
        return shipmentRepository.findByCustomerId(id);
    }

    @Override
    public String getReceiverName(Integer id) {
        return shipmentRepository.findById(id).get().getReceiver();
    }

    @Override
    public String getWeight(Integer id) {
        return shipmentRepository.findById(id).get().getWeight();
    }

    @Override
    public String getBoxColor(Integer id) {
        return shipmentRepository.findById(id).get().getBoxColor();
    }

    @Override
    public String getDestination(Integer id) {
        return shipmentRepository.findById(id).get().getDestination();
    }

    @Override
    public List<Shipment> findAllShipmentsByCustomerId(Integer id) {
        return shipmentRepository.findAllByCustomerId(id);
    }

}
