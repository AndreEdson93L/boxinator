package no.accelerate.springwebpreswagger.exceptions;

public class ShipmentNotFoundException extends EntityNotFoundException{
    public ShipmentNotFoundException(Integer id) {
        super("Shipment does not exist with ID: " + id);
    }
}
