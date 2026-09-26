# EduMerge Fee Management System - Backend

A Spring Boot REST API backend for managing students, fee structures, fee heads, student fees, payments, receipts, outstanding fees, reconciliation, and reports.

The backend provides secure REST APIs for the EduMerge Fee Management System and is designed to work with the React.js frontend.

---

## 📌 Project Overview

EduMerge is a Fee Management System designed to help educational institutions manage student fee-related operations from a centralized application.

The backend is responsible for:

- Admin authentication
- JWT-based authorization
- Student management
- Fee head management
- Fee structure management
- Student fee assignment
- Payment management
- Receipt management
- Outstanding fee tracking
- Reconciliation
- Dashboard data
- Reports
- Database operations
- REST API communication

The backend follows a layered architecture using Spring Boot.

---

# 🚀 Main Features

## 🔐 1. Admin Authentication

The system provides secure admin login using:

- Email
- Password
- JWT authentication
- Role-based authorization

After successful login, the backend returns a JWT token.

The frontend stores the token and sends it with subsequent API requests.

Example:

```http
Authorization: Bearer <JWT_TOKEN>
