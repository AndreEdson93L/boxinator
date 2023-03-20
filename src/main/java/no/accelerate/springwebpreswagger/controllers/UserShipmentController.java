package no.accelerate.springwebpreswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("api/v1/user/shipments")
public class UserShipmentController {

    private final ShipmentService shipmentService;
    private ShipmentMapper shipmentMapper;
    //private CustomerMapper customerMapper;
    @Autowired
    public UserShipmentController(ShipmentService shipmentService, ShipmentMapper shipmentMapper)
    {
        this.shipmentService = shipmentService;
        this.shipmentMapper = shipmentMapper;
        //this.customerMapper = customerMapper;
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
    public ResponseEntity<?> getAllShipments(HttpSession session) {

        User currentUser = (User) session.getAttribute("user");

        if(currentUser == null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("No user is currently logged in.");
        }

        List<Shipment> shipments = shipmentService.findAllShipmentsByCustomerId(currentUser.getId());
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
    public ResponseEntity<?> getCompletedShipments(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");

        if(currentUser == null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("No user is currently logged in.");
        }

        List<Shipment> completedShipments = shipmentService.findCompletedShipments(currentUser.getId());
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
    public ResponseEntity<?> getCancelledShipments(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");

        if(currentUser == null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("No user is currently logged in.");
        }

        List<Shipment> cancelledShipments = shipmentService.findCancelledShipments(currentUser.getId());
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
    public ResponseEntity<?> getShipmentById(@PathVariable("shipment_id") Integer id, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");

        if(currentUser == null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("No user is currently logged in.");
        }

        List<Shipment> shipmentsById = shipmentService.findAllShipmentsByCustomerId(currentUser.getId());

        return new ResponseEntity<>(shipmentMapper.mapShipmentToShipmentDTO(shipmentsById.get(id)), HttpStatus.OK);
    }
    @PutMapping("/{shipment_id}")
    @Operation(summary = "Update a shipment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
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
    public ResponseEntity<?> cancelShipment(@PathVariable("shipment_id") Integer id, HttpSession session) {

        User currentUser = (User) session.getAttribute("user");

        if(currentUser == null){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("No user is currently logged in.");
        }

        Shipment shipmentById = shipmentService.findAllShipmentsByCustomerId(currentUser.getId()).get(id);
        shipmentById.setStatus(Shipment.ShipmentStatus.CANCELLED);
        shipmentService.add(shipmentById);

        return new ResponseEntity<>(shipmentMapper.mapShipmentToShipmentDTO(shipmentById), HttpStatus.OK);
    }
}