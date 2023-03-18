package no.accelerate.springwebpreswagger.services.shipment;

import no.accelerate.springwebpreswagger.exceptions.ShipmentNotFoundException;
import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.repositories.ShipmentRepository;
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
    public List<Shipment> findCreatedShipments(User customer) {
        return shipmentRepository.findCreatedShipments(customer);
    }

    @Override
    public List<Shipment> findIntransitShipments(User customer) {
        return shipmentRepository.findIntransitShipments(customer);
    }

    @Override
    public List<Shipment> findReceivedShipments(User customer) {
        return shipmentRepository.findReceivedShipments(customer);
    }

    @Override
    public List<Shipment> findCompletedShipments(User customer) {
        return shipmentRepository.findCompletedShipments(customer);
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
}
