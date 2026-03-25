# 🌐 Sri-Care: Middleware Architecture for Sri Tel Customer Experience Platform

## 📖 About

Sri-Care is a middleware-driven customer experience platform built for **Sri Tel Ltd. (STL)**, a Sri Lankan telecommunications provider. It provides a state-of-the-art internet-based Customer Care Web Portal and mobile backend, allowing customers to configure and pay for services independently — without manual staff intervention.

---

## 🚨 Problem Statement

Sri Tel's internal analysis identified several critical pain points:

- **Billing Inefficiencies** — Customers struggle to view past bills and make timely payments
- **Service Activation Delays** — Manual intervention required for VAS, data top-ups, and international roaming
- **Uninformed Disconnections** — Sudden service cuts without sufficient prior notification to the customer
- **Limited Accessibility** — No unified 24/7 self-service platform, forcing reliance on support staff for routine tasks

---

## ✨ Features

- **Automated Account Management** — Secure, manual-step-free account registration and login including password recovery
- **Service Control** — Activate/deactivate services such as International Roaming, Ring-in Tone personalization, and data top-ups
- **Billing & Payments** — View current and historical bills; pay securely via integration with an external RESTful payment gateway
- **Asynchronous Notifications** — High-volume Email, SMS, and Push alerts delivered via RabbitMQ on a best-effort basis, without hindering primary system performance
- **Customer Support Chat** *(Proposed)* — Real-time WebSocket-based instant messaging between customers and care agents
- **Third-Party Integration** — Consumes RESTful interfaces from the STL Provisioning System and external Payment Gateway

---

## 🏗️ Architecture

Sri-Care follows a **Microservices Architecture** built with Spring Boot, organized into four distinct layers:

**Presentation Tier** — React-based Web Portal and Smartphone Apps consuming backend services via JSON-RESTful APIs.

**Middleware & Orchestration Layer** — API Gateway (Port 8080) as the single entry point for all traffic, and Eureka Registry (Port 8761) for dynamic service discovery.

**Messaging Tier** — RabbitMQ Broker enabling best-effort delivery of high-volume notifications, keeping the system responsive during peak loads.

**Data Persistence Tier** — Database-per-Service pattern using isolated MySQL instances per microservice.

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Frontend** | React.js |
| **Backend** | Java Spring Boot |
| **Service Discovery** | Netflix Eureka |
| **Message Broker** | RabbitMQ |
| **Database** | MySQL (Database-per-Service) |
| **Containerization** | Docker & Docker Compose |
| **API Testing** | Insomnia |
| **Version Control** | GitHub |

---

## 🔧 Microservices & Components

| Component | Port | Responsibility |
|-----------|------|---------------|
| **API Gateway** | `8080` | Reverse proxy; routes requests, centralized authentication |
| **Eureka Registry** | `8761` | Dynamic service discovery and registration |
| **User Service** | — | Account registration, login, and profile management |
| **Billing Service** | — | Bill retrieval and payment orchestration |
| **Provisioning Service** | — | Adapts and forwards requests to the Mock Vendor System via REST |
| **Notification Service** | — | Event-driven; consumes RabbitMQ messages to deliver Email/SMS/Push alerts |
| **Mock Payment Service** | — | Simulates secure external payment gateway authorization |

---

## 🔄 Key Workflows

### Bill Payment (Hybrid Synchronous / Asynchronous)

1. React Frontend sends `POST /api/billing/1/pay` to the API Gateway
2. API Gateway queries Eureka Registry to resolve the Billing Service location
3. Billing Service retrieves bill details from the Billing Database
4. Billing Service calls Mock Payment Service synchronously via REST
5. On `SUCCESS` response — Billing DB record updated to `PAID`
6. Billing Service publishes a payment notification event to RabbitMQ and immediately returns `"Payment Successful"` to the client
7. Notification Service consumes the message from RabbitMQ and delivers the Email/SMS alert asynchronously in the background

### User Login (Synchronous Request-Response)

1. React app sends login request to API Gateway
2. API Gateway queries Eureka to resolve User Service location
3. User Service validates credentials against User DB
4. Returns authentication token / user object to the client

### Service Provisioning (External System Integration)

1. User requests a service activation (e.g. International Roaming)
2. Provisioning Service acts as a middleware adapter — translates the internal request into the format required by the external system
3. Calls REST API on the Mock Vendor System
4. Returns activation confirmation to the client

---

## 🏛️ Architectural Patterns

| Pattern | Usage in Sri-Care |
|---------|------------------|
| **Microservices** | System decomposed into autonomous, single-responsibility services (User, Billing, Provisioning, Notification) |
| **API Gateway** | Single entry point for all clients; handles routing, protocol translation, security, and rate limiting |
| **Database-per-Service** | Each microservice maintains its own private MySQL instance for strict data isolation |
| **Event-Driven Architecture** | Notification engine reacts to state changes (e.g. successful payment) without synchronous coupling |
| **Publish-Subscribe (Pub-Sub)** | RabbitMQ exchange broadcasts events; multiple subscribers process them independently |
| **Message Queue** | Best-effort delivery of high-volume alerts without affecting primary billing/provisioning functions |
| **Adapter Pattern** | Provisioning Service translates internal requests into the specific format required by the Mock Vendor System |
| **Request-Reply (Synchronous)** | Used for real-time operations requiring immediate responses — login, payment authorization |

---

## 🗄️ Database Design

Each microservice maintains its own isolated MySQL schema:

- **User DB** — User credentials, hashed passwords, and profile registration data
- **Billing DB** — Historical and current billing records, payment status, and transaction IDs
- **Provisioning DB** — Logs of service activations and deactivations for audit purposes

---

## 💬 Proposed Chat Module

The proposed real-time chat architecture uses:

- **WebSockets + STOMP** — Full-duplex, bi-directional communication over a single TCP connection
- **API Gateway** — Handles the initial HTTP Upgrade request with JWT-based authentication before handing off to the Chat Service
- **Redis Pub/Sub** — Publishes messages to a Redis topic; all Chat Service instances subscribe to ensure delivery across a distributed cluster
- **Redis Key-Value Store** — Stores session and presence (online status) data, keeping the Chat Service stateless and horizontally scalable
- **MongoDB** — Asynchronous write-behind persistence for chat transcripts and metadata, chosen for its high write-throughput and flexible schema

---

## ⚖️ Why Microservices? (Architecture Decision)

Two alternatives were evaluated:

| Alternative | Key Weakness |
|-------------|-------------|
| **Monolithic Architecture** | Cannot scale individual components independently; single point of failure across all services |
| **ESB-Centric SOA** | Becomes a performance bottleneck; introduces unnecessary complexity and cost for REST-based services |

The **Microservices + API Gateway + RabbitMQ** approach was selected for:
- **Fault Isolation** — Critical billing and provisioning remain operational even if notification or chat services fail
- **Asynchronous Resilience** — RabbitMQ satisfies the best-effort notification requirement without degrading primary functions
- **Technological Agility** — Easily accommodates future iOS and Android app expansions via the centralized API Gateway
- **Ease of Deployment** — Docker containerization ensures consistent performance across environments

---

## 📄 License

This project was developed for academic purposes as part of **CSC 4222 — Service Oriented Computing** at the University of Ruhuna, Department of Computer Science.

---

<div align="center">
Made with ❤️ by V.S. Abeynayake & R. Sharunee — Department of Computer Science, University of Ruhuna · 2025
</div>
