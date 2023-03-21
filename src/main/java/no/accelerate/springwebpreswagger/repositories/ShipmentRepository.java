package no.accelerate.springwebpreswagger.repositories;

import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.shipment.ShipmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    @Query("SELECT s FROM Shipment s WHERE s.status = 'CREATED' AND s.customer.id = ?1")
    List<Shipment> findCreatedShipments(Integer id);
    @Query("SELECT s FROM Shipment s WHERE s.status = 'RECIEVED' AND s.customer.id = ?1")
    List<Shipment> findReceivedShipments(Integer id);
    @Query("SELECT s FROM Shipment s WHERE s.status = 'INTRANSIT' AND s.customer.id = ?1")
    List<Shipment> findIntransitShipments(Integer id);
    @Query("SELECT s FROM Shipment s WHERE s.status = 'COMPLETED' AND s.customer.id = ?1")
    List<Shipment> findCompletedShipments(Integer id);
    @Query("SELECT s FROM Shipment s WHERE s.status = 'COMPLETED'")
    List<Shipment> findCompletedShipments();

    @Query("SELECT s FROM Shipment s WHERE s.status = 'CANCELLED' AND s.customer.id = ?1")
    List<Shipment> findCancelledShipments(Integer id);
    @Query("SELECT s FROM Shipment s WHERE s.status = 'CANCELLED'")
    List<Shipment> findCancelledShipments();
    List<Shipment> findByCustomerId(Integer id);
    @Query("SELECT s FROM Shipment s WHERE s.customer.id = ?1")
    List<Shipment> findAllByCustomerId(Integer id);
    @Query("SELECT s FROM Shipment s WHERE s.id = ?1 AND s.customer.id = ?2")
    Shipment findByIdAndCustomerId(Integer id, Integer customerId);
}
