# Sri-Care: Customer Experience Platform

**Course:** Service Oriented Computing (CSC 4222)
**Assignment:** Assignment 4 - Middleware Architecture Prototype
**Scenario:** Sri Tel Ltd (STL) Digital Transformation

---

## 1. Executive Summary
This repository contains the source code and documentation for **"Sri-Care,"** a comprehensive self-care solution designed for Sri Tel Ltd (STL). This project was developed to demonstrate a robust Service Oriented Architecture (SOA) that modernizes customer engagement through a web portal and mobile-ready interfaces.

The solution is engineered to address the specific business needs of a telecommunications provider, utilizing open-source middleware technologies and containerization to ensure scalability, security, and seamless integration with legacy provisioning systems.

## 2. Solution Overview
The Sri-Care platform is designed as a middleware solution that bridges the gap between customer interfaces (Web/Mobile) and backend telecommunication systems.

### Key Functional Modules
* **Unified Identity Management:** Secure, automated account registration and self-service password recovery for subscribers, eliminating the need for manual staff intervention.
* **Service Provisioning:** A centralized interface allowing customers to activate and deactivate Value Added Services (VAS), roaming capabilities, and data bundles.
* **Billing & Payments:** Real-time billing inquiry system integrated with a mock Payment Gateway to facilitate secure credit and debit card transactions.
* **Notification Engine:** An asynchronous alert system designed to handle high-volume notifications (SMS/Email) regarding bill generation and service status on a "best-effort" basis.
* **Customer Support:** Architecture supporting real-time connectivity for customer care agent chat systems.

## 3. Architecture & Technology
The system is built on a modular architecture to ensure loose coupling and high availability.

* **Deployment:** Fully containerized using **Docker** and orchestrated via **Docker Compose**.
* **Integration Pattern:** RESTful APIs are utilized for all internal and external communications, including integration with the mock Provisioning System and Payment Gateway.
* **Scalability:** The backend logic is centralized to serve multiple client types (Web & Mobile) efficiently.

## 4. Deployment Instructions
This project utilizes Docker to streamline the setup process. Ensure you have Docker and Docker Desktop installed before proceeding.

### Prerequisites
* Docker Desktop (Latest Stable Version)
* Git

### Quick Start Guide
1.  **Clone the Repository**
    ```bash
    git clone <repository-url>
    ```

2.  **Navigate to Project Directory**
    Open your terminal and move into the project folder.

3.  **Launch Application**
    Run the following command to build and start the entire service stack:
    ```bash
    docker-compose up -d --build
    ```

4.  **Verify Status**
    Check that all containers are running:
    ```bash
    docker ps
    ```

5.  **Shutdown**
    To stop the application and remove containers:
    ```bash
    docker-compose down
    ```

---
*Submitted by CSC 4222 Student Group - University of Ruhuna*
