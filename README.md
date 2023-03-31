# My Awesome API (Spring Boot Application)

My Awesome API is a RESTful API built using Spring Boot with Java and PostgreSQL, providing functionality to manage users and admins. This README provides a guide on how to set up and use the API.

## Table of Contents

- [Requirements](#requirements)
- [Installation and Setup](#installation-and-setup)
- [API Endpoints](#api-endpoints)
- [Contributors](#contributors)
- [License](#license)

## Requirements

- Java 17 or later
- Gradle 7.0 or later
- PostgreSQL 10 or later

## Installation and Setup

1. Clone the repository
2. git clone https://github.com/AndreEdson93L/boxinator.git
3. Change the directory to the project folder
4. Update `application.properties` file in the `src/main/resources` folder with your PostgreSQL credentials.
5. Build the project using Gradle
6. Run the application


By default, the API will be available at http://localhost:8080

## API Endpoints

The API exposes the following endpoints:

### AdminController

- `POST /api/v1/admin/register`: Register an admin.
- `secret to register an admin available in the application properties`: It can be personalized by the user.

### AdminShipmentController

- `GET /api/v1/admin/shipments`: Get all shipments.
- `GET /api/v1/admin/shipments/complete`: Get all completed shipments.
- `GET /api/v1/admin/shipments/cancelled`: Get all cancelled shipments.
- `POST /api/v1/admin/shipments`: Post a shipment.
- `GET /api/v1/admin/shipments/{shipment_id}`: Get a shipment by id.
- `GET /api/v1/admin/shipments/customer/{customer_id}`: Get all shipments by customer id.
- `PUT /api/v1/admin/shipments/{shipment_id}`: Update a shipment.
- `DELETE /api/v1/admin/shipments/shipment/{shipment_id}`: Delete shipment by id.
- `DELETE /api/v1/admin/shipments/{account_id}`: Delete an account by id.
- `PUT /api/v1/admin/shipments/account/{userId}`: Update account by id.

### AuthenticationController

- `POST /api/v1/auth/login`: Log in.
- `GET /api/v1/auth/logout`: This endpoint logs out the current user by invalidating the session. No input is required for this endpoint.
- `POST /api/v1/auth/register`: This endpoint allows a new user to register.
- `GET /api/v1/auth/current-user`: This endpoint returns the currently logged-in user.

### CountryController

- `GET /api/v1/settings/countries`: Find all countries.
- `POST /api/v1/settings/countries`: Add a new country.
- `PUT /api/v1/settings/countries/{countryId}`: Update a country by id.

### UserShipmentController

- `GET /api/v1/user/shipments`: Get all shipments.
- `GET /api/v1/user/shipments/complete`: Get all completed shipments.
- `GET /api/v1/user/shipments/cancelled`: Get all cancelled shipments.
- `POST /api/v1/user/shipments`: Post a shipment.
- `GET /api/v1/user/shipments/{shipment_id}`: Get a shipment by id.
- `PUT /api/v1/user/shipments/{shipment_id}`: Update a shipment (cancel it).
- `PUT /api/v1/user/shipments/account/update`: Update account.


## Contributors

- Andrea Edson Lorenzoni
- Pedro Ferreira

## License

This project is licensed under the [MIT License](LICENSE.md).
