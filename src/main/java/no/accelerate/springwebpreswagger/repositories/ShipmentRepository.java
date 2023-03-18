package no.accelerate.springwebpreswagger.repositories;

import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    @Query("SELECT s FROM Shipment s WHERE s.status = 'CREATED' AND s.customer = ?1")
    List<Shipment> findCreatedShipments(User customer);

    @Query("SELECT s FROM Shipment s WHERE s.status = 'RECIEVED' AND s.customer = ?1")
    List<Shipment> findReceivedShipments(User customer);

    @Query("SELECT s FROM Shipment s WHERE s.status = 'INTRANSIT' AND s.customer = ?1")
    List<Shipment> findIntransitShipments(User customer);
    @Query("SELECT s FROM Shipment s WHERE s.status = 'COMPLETED' AND s.customer = ?1")
    List<Shipment> findCompletedShipments(User customer);

    List<Shipment> findByCustomerId(Integer id);

}
