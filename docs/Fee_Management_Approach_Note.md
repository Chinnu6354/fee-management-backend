# Fee Management & Reconciliation System

## 1. Problem Understanding

The application is a backend system for managing student fees and payments.

The main purpose of the system is to manage:

- Students
- Fee heads
- Fee structures
- Student fee assignments
- Discounts
- Paid amounts
- Outstanding amounts
- Payments
- Receipts
- Payment history
- Reconciliation
- Dashboard and fee summaries
- User authentication and authorization

The system calculates the final payable amount and outstanding amount based on the
total fee, discount, and paid amount.

---

## 2. Technology Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Spring Security
- JWT Authentication
- Maven
- Lombok
- IntelliJ IDEA
- Postman
- Git and GitHub

---

## 3. Architecture

The application follows a layered backend architecture.

```text
Client / Postman
       |
       v
REST Controllers
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
MySQL Database


Security is handled before the request reaches protected controllers.

Client Request
      |
      v
JWT Authentication Filter
      |
      v
Spring Security
      |
      v
Controller
      |
      v
Service
      |
      v
Repository
      |
      v
MySQL

This separation keeps API handling, business logic, security, and database
operations organized independently.


4. Main Modules
Authentication

The authentication module provides:

User signup
User login
JWT token generation
JWT token validation
Role-based access

The JWT contains the user's email and role.

Protected APIs require a valid Bearer token.

Student Management

The student module manages student information such as:

Name
Email
Phone
Student ID
Course
Department
Admission year
Active status
Fee Head Management

Fee heads represent different types of fees.

Examples:

Tuition Fee
Examination Fee
Library Fee
Laboratory Fee
Fee Structure

Fee structures define the fee information associated with a course or
department and a fee head.

Student Fee

A student fee connects a student with a fee structure.

The system maintains:

Total amount
Discount amount
Final amount
Paid amount
Due amount
Payment status
Payment

The payment module records payments against a student fee.

Payment information includes:

Amount
Payment method
Transaction ID
Payment date
Remarks
Payment status
Receipt

The receipt module maintains receipt information associated with payments.

Reconciliation

The reconciliation module provides information that can be used to compare
and verify collected payment amounts and fee records.

Dashboard and Summary

Dashboard and summary APIs provide aggregated information about fees and
payments.

5. Fee Calculation

The system calculates the final payable amount using:

Final Amount = Total Amount - Discount Amount

The outstanding amount is calculated using:

Due Amount = Final Amount - Paid Amount

For example:

Total Amount  = ₹50,000
Discount      = ₹5,000

Final Amount  = ₹45,000

Paid Amount   = ₹30,000

Due Amount    = ₹15,000

The backend validates that:

Total amount is provided.
Discount cannot be greater than the total amount.
Paid amount cannot be greater than the final payable amount.
Amount values cannot be negative where validation applies.
6. Payment Status

The student fee status is calculated from the payment information.

Paid Amount = 0
      |
      v
PENDING

Paid Amount > 0
and Due Amount > 0
      |
      v
PARTIAL

Due Amount = 0
      |
      v
PAID

This keeps the fee status consistent with the financial values stored for the
student fee.

7. Database Design

The application uses MySQL with JPA/Hibernate.

Important entities include:

User
Student
Role
FeeHead
FeeStructure
StudentFee
Payment
Receipt

The main relationships include:

Student
   |
   +---- StudentFee
              |
              +---- FeeStructure
              |
              +---- Payment
              |
              +---- Receipt

JPA relationships are used to maintain relationships between the entities.

Repositories extend Spring Data JPA repository interfaces to perform database
operations.

8. Payment Flow

The general payment flow is:

Student
   |
   v
Student Fee
   |
   v
Check Outstanding Amount
   |
   v
Create Payment
   |
   v
Update Fee Amounts / Status
   |
   v
Generate / Maintain Receipt Information
   |
   v
Available for History and Reconciliation

Before accepting a payment, the backend validates that the payment does not
exceed the outstanding payable amount.

9. Authentication and Authorization

The application uses JWT-based authentication.

After successful login:

Email + Password
      |
      v
Authentication
      |
      v
JWT Token

The client sends the token for protected APIs:

Authorization: Bearer <JWT_TOKEN>

The JWT authentication filter extracts and validates the token.

The application also uses user roles to control access to protected
administrative APIs.

10. Validation and Error Handling

Validation is implemented at the entity and service levels.

Examples include:

Required values cannot be null.
Fee amounts must be greater than or equal to the allowed minimum.
Paid amounts cannot be negative.
Discount cannot exceed the total fee.
Paid amount cannot exceed the final payable amount.
Requested student fee records must exist.
Authentication is required for protected APIs.

A global exception handler is used to provide consistent API error responses.

11. Important Edge Cases

The backend considers the following cases:

No payment

If the paid amount is zero:

Status = PENDING
Partial payment

If the student has paid only part of the payable amount:

Status = PARTIAL
Complete payment

If the complete payable amount has been paid:

Status = PAID
Discount equal to total fee

If:

Discount = Total Amount

then:

Final Amount = 0

The business rules should ensure that the resulting payment and status
handling remain consistent.

Payment greater than payable amount

The backend rejects the operation instead of allowing the fee to become
negative.

Missing student fee

If the requested student fee does not exist, the service returns an error
instead of creating an invalid payment relationship.

12. Reconciliation

Reconciliation is used to compare fee and payment information and provide
visibility into collected and outstanding amounts.

The system keeps payment information linked to the corresponding student fee
using the StudentFee relationship.

This makes it possible to trace:

Student
   |
   v
Student Fee
   |
   v
Payment
   |
   v
Transaction ID

The transaction ID provides a reference for identifying an individual payment.

13. Security Considerations

Sensitive configuration values are not stored directly in the source code.

For example:

spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}

The actual values are supplied through environment variables.

This prevents database passwords and JWT secrets from being committed to the
Git repository.

The project also excludes generated files such as the Maven target directory
and IntelliJ project files using .gitignore.

14. Assumptions

The following assumptions were made during implementation:

MySQL is used as the primary relational database.
A student can have multiple fee records.
A student fee is associated with one fee structure.
A student fee can have payment records.
Transaction IDs are unique.
Fee calculations are handled by the backend rather than trusting calculated
values supplied by the client.
Protected APIs require JWT authentication.
User roles are used for authorization.
Payment information is stored for history and reconciliation.
15. Design Trade-offs
Layered Architecture

A layered architecture was selected because it is simple to understand and
maintain for a business application.

The main trade-off is that larger applications can eventually require more
advanced patterns, but for this system the layered approach keeps the code
clear.

JPA / Hibernate

Spring Data JPA was used to reduce boilerplate database code and simplify
entity relationships.

The trade-off is that developers need to understand JPA relationships,
transactions, and generated SQL to avoid performance problems in larger
systems.

JWT Authentication

JWT was selected for stateless API authentication.

The trade-off is that token expiration, refresh-token handling, and token
revocation would need additional implementation for a larger production
system.

16. Testing

The APIs were tested using Postman.

Important scenarios tested include:

User login
JWT-protected APIs
Student creation and retrieval
Fee creation
Student fee creation
Discount calculation
Partial payment
Complete payment
Outstanding fee calculation
Payment APIs
Receipt APIs
Dashboard APIs
Reconciliation APIs
Invalid request handling
Unauthorized access

Successful API responses were verified during development.

17. Future Improvements

For a production-scale system, the following improvements could be added:

Payment gateway integration
Automated payment callbacks/webhooks
Refresh tokens
More detailed audit logs
Database transactions for payment operations
Pagination for large datasets
Advanced reporting
Automated unit and integration tests
API documentation using OpenAPI/Swagger
Docker-based deployment
Centralized application logging
Production database configuration
CI/CD pipeline
18. Conclusion

The backend provides a structured fee management system using Spring Boot,
Spring Data JPA, MySQL, and JWT-based security.

The application separates controllers, services, repositories, entities, and
security concerns.

The main focus is maintaining consistency between:

Total Fee
    -
Discount
    =
Final Payable Amount

Final Payable Amount
    -
Paid Amount
    =
Outstanding Amount

This approach provides a clear foundation for fee collection, payment
tracking, receipts, outstanding fee management, dashboard information, and
reconciliation.