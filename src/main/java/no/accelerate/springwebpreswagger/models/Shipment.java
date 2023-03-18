package no.accelerate.springwebpreswagger.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    /*
      Receiver name
      Weight option (kg)
      Box colour (colour picker)
      Destination Country

      CREATED: This is the ‘new‘ package state
      RECIEVED: This represents when the package has been physically collected for transport, but has not been routed for delivery yet.
      INTRANSIT: Represents a package that is being transported.
      COMPLETED: Represents a package that has been delivered
    */

@Entity
@Table(name = "shipments")
@Getter
@Setter
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private String weight;

    @Column(nullable = false)
    private String boxColor;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String destination;
    public enum ShipmentStatus {
        CREATED, RECIEVED, INTRANSIT, COMPLETED
    }
}
