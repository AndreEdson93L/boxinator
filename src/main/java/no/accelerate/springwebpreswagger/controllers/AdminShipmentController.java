package no.accelerate.springwebpreswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import no.accelerate.springwebpreswagger.mappers.CustomerMapper;
import no.accelerate.springwebpreswagger.mappers.ShipmentMapper;
import no.accelerate.springwebpreswagger.models.Shipment;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.shipment.ShipmentDTO;
import no.accelerate.springwebpreswagger.models.dto.shipment.ShipmentPostDTO;
import no.accelerate.springwebpreswagger.services.shipment.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin/shipments")
public class AdminShipmentController {

    private final ShipmentService shipmentService;
    private ShipmentMapper shipmentMapper;
    private CustomerMapper customerMapper;
    @Autowired
    public AdminShipmentController(ShipmentService shipmentService, ShipmentMapper shipmentMapper, CustomerMapper customerMapper)
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
        return new ResponseEntity<>(shipmentDTOs, HttpStatus.OK);
    }

    @GetMapping("/complete")
    @Operation(summary = "Get all completed shipments")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Completed shipments has been retrieved successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No shipment found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            )
    })
    public ResponseEntity<List<ShipmentDTO>> getCompletedShipments() {
        List<Shipment> completedShipments = shipmentService.findCompletedShipments();
        List<ShipmentDTO> shipmentDTOs = completedShipments.stream()
                .map(shipmentMapper::mapShipmentToShipmentDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(shipmentDTOs, HttpStatus.OK);
    }

    @GetMapping("/cancelled")
    @Operation(summary = "Get all cancelled shipments")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cancelled shipment has been retrieved successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No shipment found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            )
    })
    public ResponseEntity<List<ShipmentDTO>> getCancelledShipments() {
        List<Shipment> cancelledShipments = shipmentService.findCancelledShipments();
        List<ShipmentDTO> shipmentDTOs = cancelledShipments.stream()
                .map(shipmentMapper::mapShipmentToShipmentDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(shipmentDTOs, HttpStatus.OK);
    }
    @PostMapping
    @Operation(summary = "Post a shipment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Shipment has been posted successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentPostDTO.class))
                    }
            ),
    })
    public ResponseEntity<?> createShipment(@RequestBody ShipmentPostDTO shipmentPostDTO, HttpSession session) {

        User currentUser = (User) session.getAttribute("user");

        if(currentUser == null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("No user is currently logged in.");
        }

        if (currentUser != null) {
            // Set the customer ID to the current user's ID
            shipmentPostDTO.setCustomerId(currentUser.getId());

        }

        // Convert the ShipmentDTO object to a Shipment object using the mapper
        Shipment shipment = shipmentMapper.mapShipmentPostDTOToShipment(shipmentPostDTO);

        // Set date of creation automatically
        shipment.setCreatedDate(LocalDateTime.now());

        // Save the shipment and convert the saved shipment to ShipmentPostDTO
        Shipment savedShipment = shipmentService.add(shipment);
        ShipmentPostDTO savedShipmentPostDTO = shipmentMapper.mapShipmentToShipmentPostDTO(savedShipment);

        return new ResponseEntity<>(savedShipmentPostDTO, HttpStatus.CREATED);
    }



    @GetMapping("/{shipment_id}")
    @Operation(summary = "Get a shipment by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Shipment (by id) has been retrieved successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No shipment found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            )
    })
    public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable("shipment_id") Integer id) {
        shipmentMapper.mapShipmentToShipmentDTO(shipmentService.findById(id));

        return new ResponseEntity<>(shipmentMapper.mapShipmentToShipmentDTO(shipmentService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/customer/{customer_id}")
    @Operation(summary = "Get all shipments by customer id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All shipments by customer id has been retrieved successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No shipment found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentDTO.class))
                    }
            )
    })
    public ResponseEntity<List<ShipmentDTO>> getAllShipmentsByCustomerId(@PathVariable("customer_id") Integer id) {

        List<Shipment> shipments = shipmentService.findAllShipmentsByCustomerId(id);
        List<ShipmentDTO> shipmentDTOs = shipments.stream()
                .map(shipmentMapper::mapShipmentToShipmentDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(shipmentDTOs, HttpStatus.OK);
    }
    @PutMapping("/{shipment_id}")
    @Operation(summary = "Update a shipment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Shipment has been updated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ShipmentPostDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    public ResponseEntity<ShipmentPostDTO> updateShipment(@PathVariable("shipment_id") Integer id,
                                                   @RequestBody ShipmentPostDTO updatedShipment) {
        Shipment existingShipment = shipmentService.findById(id);
        updatedShipment.setId(existingShipment.getId());
        updatedShipment.setCustomerId(existingShipment.getCustomer().getId());
        updatedShipment.setCreatedDate(existingShipment.getCreatedDate());
        return new ResponseEntity<>(updatedShipment, HttpStatus.OK);
    }
    @DeleteMapping("/{shipment_id}")
    @Operation(summary = "Delete shipment by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Shipment has been deleted successfully"
            ),
    })
    public ResponseEntity<Void> deleteShipment(@PathVariable("shipment_id") Integer id) {
        shipmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
