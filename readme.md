# Employee Leave Management System

A full-stack Employee Leave Management System to streamline leave requests, approvals, and tracking for organizations.  
This repository contains:
- **Frontend**: React + Vite UI
- **Backend**: Spring Boot REST API (with Spring Security, JWT, JPA)

## Problem Statement / What it Solves

Managing employee leave via email/spreadsheets is error-prone and lacks visibility. This system helps:
- Employees submit leave requests in a structured way
- Managers/admins review, approve/reject, and track leave status
- Organizations keep an auditable history of leave activity

## Key Features

- Employee leave request creation and tracking
- Approval / rejection workflow
- Authentication & authorization (Spring Security + JWT)
- RESTful backend API
- Modern responsive frontend UI (Tailwind/Flowbite)
- Database persistence via Spring Data JPA (supports **H2** for local dev and **MySQL** for production)

## Tech Stack

### Frontend
- React 18
- Vite
- React Router
- Axios
- Tailwind CSS
- Flowbite UI components
- ESLint

### Backend
- Java 17
- Spring Boot 3
- Spring Web (REST APIs)
- Spring Security
- Spring Data JPA
- JWT (jjwt)
- H2 (runtime option)
- MySQL Connector (runtime option)
- Lombok

## Project Structure

```text
Employee-Leave-management-System/
├── Backend/                # Spring Boot backend
│   ├── pom.xml
│   └── src/...
└── Frontend/               # React (Vite) frontend
    ├── package.json
    └── src/...
```

## Setup / Installation

### Prerequisites
- **Node.js** (LTS recommended) + npm
- **Java 17**
- **Maven**
- (Optional) **MySQL** if you want to run with MySQL instead of H2

### 1) Clone the repository
```bash
git clone https://github.com/radhepipaliya/Employee-Leave-management-System.git
cd Employee-Leave-management-System
```

### 2) Frontend setup
```bash
cd Frontend
npm install
```

### 3) Backend setup
```bash
cd ../Backend
mvn clean install
```

## How to Run the Project

### Run Backend (Spring Boot)
From the `Backend` directory:
```bash
mvn spring-boot:run
```

Backend will start on a configured port (commonly `8080` unless changed).

### Run Frontend (Vite Dev Server)
From the `Frontend` directory:
```bash
npm run dev
```

Vite will print the local URL (commonly `http://localhost:5173`).

## Example Usage

### Typical flow
1. Open the frontend in your browser.
2. Register/Login (depending on how authentication is implemented in the UI).
3. Create a leave request (select dates, reason, type).
4. View request status (Pending/Approved/Rejected).
5. Manager/Admin reviews requests and approves/rejects.

### API usage example (illustrative)
If your backend exposes a login endpoint returning a JWT, you’d typically:
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"employee1","password":"password"}'
```

Then call protected endpoints with:
```bash
curl http://localhost:8080/api/leaves \
  -H "Authorization: Bearer <YOUR_JWT_TOKEN>"
```

> Update the endpoint paths above to match your actual controllers/routes in `Backend/src`.

## Configuration Notes

### Database
The backend includes dependencies for both **H2** and **MySQL**.
- Use **H2** for quick local development
- Use **MySQL** for production-like environments

To run with MySQL, configure your Spring `application.properties`/`application.yml` (typically under `Backend/src/main/resources/`) with your MySQL connection URL, username, and password.

## Contribution Guidelines

Contributions are welcome.

1. Fork the repository
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit changes with clear messages
4. Push to your fork and open a Pull Request

### Suggested standards
- Keep PRs small and focused
- Add/update documentation where relevant
- Add tests for backend changes when feasible

## License

No license file was detected in the repository.  
To make usage clear for others, consider adding a `LICENSE` file (e.g., MIT, Apache-2.0, GPL-3.0).

---

## Maintainers

- **radhepipaliya**