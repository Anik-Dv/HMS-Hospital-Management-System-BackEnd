
# HMS - This is a Hospital Management System BackEnd Application.


Welcome to the Hospital Management System! This project is a full-stack application designed to manage hospital operations, patient records, appointments, and user authentication. It's built using Spring Boot for the backend and incorporates various security features and REST APIs to support role-based access for patients, doctors, and administrators.


## 📋 Project Overview
#### This hospital management system facilitates:

- User Authentication: Role-based access control for patients, doctors, and administrators using JWT (JSON Web Token).

- Patient Management: Patient registration, login, and profile updates.

- Doctor Management: Admins can add doctors, and doctors have individual access to manage patient interactions.

- Appointment Scheduling: Patients can request appointments with specific doctors.

- Secure Access: Each API endpoint is protected based on user roles to ensure data privacy and security.
## Demo

Insert gif or link to demo


## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)


## 🔑 Key Features

- ### Role-Based Access Control : 
- Admin, Doctor, and Patient roles, with restricted access to endpoints based on roles.

- #### User Authentication & Authorization :
 - Admin: Manages doctors, system settings, and has   full control over the hospital's data.
- Doctor: Can log in to manage patient data and appointments.
- Patient: Can register, log in, request appointments, and view personal health records.

- #### Appointment System :
- Allows patients to schedule appointments with doctors and track the appointment status.

- #### Patient Health Records :
- Manages patient-specific data such as health status, blood group, urgency levels, and other health details.

## 🛠️ Technologies & Tools
The tech stack used in the back-end project :

* Backend: Java, Spring Boot, Spring Security, JPA, Hibernate
* Database: MySQL
* Security: JWT for authentication and authorization
* API Documentation: Swagger
* Others: Maven, JSON

## What did I learn while building this project? What challenges did I'm face and how did I overcome them?

While building this project, I learned several valuable lessons that helped me improve my skills as a frontend developer. Some of the key learnings include:

* React Fundamentals: I gained a deeper understanding of React fundamentals, including components, state management, and props. This allowed me to build more complex and interactive user interfaces.

* Redux State Management: I learned how to use Redux for state management, which helped me manage application state more efficiently, especially when dealing with multiple components that need access to the same data.

* API Integration: I learned how to integrate with backend APIs using fetch or axios, enabling me to fetch data from the server and update the UI accordingly.

* Authentication: I learned how to implement user authentication using JWT tokens, which enhanced the security of the application by ensuring that only authenticated users could access certain features.

* Routing: I learned how to use React Router to implement client-side routing, allowing me to create a multi-page application experience without the need for full page reloads.

* Responsive Design: I learned how to create responsive designs using CSS media queries, making the application accessible and user-friendly on a variety of devices and screen sizes.

# Challenges Faced and Overcoming Them:

* Managing State: One challenge was managing the application state, especially when dealing with complex data structures. I overcame this by using Redux for state management, which provided a centralized store for managing application state.

* Authentication: Implementing user authentication was challenging, especially ensuring that JWT tokens were handled securely. I overcame this by following best practices for token storage and validation.

* Performance Optimization: Ensuring that the application was fast and responsive, especially when dealing with large amounts of data, was a challenge. I overcame this by implementing code splitting and lazy loading to minimize initial load times.

* Cross-Origin Resource Sharing (CORS): Dealing with CORS issues when making requests to the backend API was challenging. I overcame this by configuring the backend server to allow requests from the frontend domain.


## 📂 Project Structure

```

src/
├── main/
│   ├── java/com/hms/anikdv/code
│   │   ├── controllers            # REST Controllers for API endpoints
│   │   ├── entities               # JPA Entities for database modeling
│   │   ├── repositories           # Repositories for data persistence
│   │   ├── services               # Services for business logic
│   │   ├── security               # JWT and role-based access configuration
│   │   └── payloads               # DTOs for API requests and responses
│   │   └── utils                  # All Helper Method
│   └── resources/
│       ├── application.properties # Configurations for database, JWT, etc.

```
## 🚀 Getting Started

## Prerequisites
* Java 17+
* MySQL
* Maven
* Redis 
* Postman (for testing APIs)
## Installation & Setup

### 1. Clone the repository:

```bash
git clone https://github.com/YourUsername/hospital-management-system.git
cd hospital-management-system
```
### 2. Set up the Database:

- Create a MySQL database named hospital_management.
- Update src/main/resources/application.properties with your database credentials.

### 3. Build the project:
```bash
mvn clean install
```
### 4. Run the application:
```bash
mvn spring-boot:run
```
## API Reference

The API provides secure endpoints for different roles (Admin, Doctor, Patient). Here is a brief summary of key endpoints:

#### Authentication Endpoints
#### Admin Login:
```http
POST /api/v1/auth/admin/login
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `JSON` | ***Required***. write your username. |
| `password` | `JSON` | ***Required***. write your password. |

#### Admin Registration:
```http
POST /api/v1/auth/admin/register
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `JSON` | ***Required***. write your username. |
| `dateofbirth` | `JSON` | ***Required***. write your username. |
| `email` | `JSON` | ***Required***. write your date of birth. |
| `password` | `JSON` | ***Required***. write your password. |

#### Patient Login::
```http
POST /api/v1/auth/patient/login
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `JSON` | ***Required***. write your username. |
| `password` | `JSON` | ***Required***. write your password. |

#### Patient Registration:
```http
POST /api/v1/auth/patient/register
```

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `JSON` | ***Required***. write your username. |
| `dateofbirth` | `JSON` | ***Required***. write your username. |
| `email` | `JSON` | ***Required***. write your date of birth. |
| `password` | `JSON` | ***Required***. write your password. |


### Patient Management:
#### Get Single Patient
```http
GET /api/v1/patient/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `ID` | `STRING` | ***Required***. Give Patient Id What you Looking For. |

#### Get all Patients
```http
GET /api/v1/patient/
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `NONE` | `NONE` | Required. Nothing. |

#### Update Patient
```http
PUT /api/v1/patient/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `ID` | `STRING` | ***Required***. Give Patient Id What you Looking For. |

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Patient Data` | `JSON` | ***Required***. Give Patient Data What you Update For. |

### Doctor Management (Admin Access Only):
#### Add a new doctor
```http
POST /api/v1/doctor/register-doctor
```
| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Doctor-Data` | `JSON` | ***Required***. Give Doctor Information. |

#### View doctor details
```http
GET /api/v1/doctor/{id}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `ID` | `STRING` | Required. Give Doctor Id. |

### Appointment Scheduling
####  Schedule an appointment with a doctor
```http
POST /api/v1/appointment/schedule/{patientId}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `patientId` | `STRING` | ***Required***. Give Patient Id What you Looking For. |

| Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Appointment Data` | `JSON` | ***Required***. Give Appointment Data. |

####  View appointment details
```http
POST GET /api/v1/appointments/{patientId}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `patientId` | `STRING` | ***Required***. Give Patient Id What you Looking For. |

### ~ Note: Each endpoint requires a JWT token in the Authorization header.




## Security & Authorization

This application uses JWT tokens to authenticate and authorize users. Access to each endpoint is restricted based on roles:

* Admin: Full access to manage doctors and hospital settings.
* Doctor: Limited access to manage appointments and patient data.
* Patient: Access to view and update their profile, request appointments.
## Run Locally

Clone the project

```bash
git clone https://github.com/Anik-Dv/Blog-Application-FrontEnd-React.git

```

Go to the project directory

```bash
cd Blog-Application-FrontEnd-React
```

Install dependencies

```bash
  npm install
```

Start the server

```bash
  npm run start
```

The development server should start, and the application will be accessible at http://localhost:3000 in your web browser.


## 🧪 Testing

- API Testing: Use Postman or any API testing tool to interact with the endpoints.
- JWT Authentication: Ensure you receive a JWT token upon login, which should be used in the ```Authorization``` header for subsequent requests.
## 🚧 Future Enhancements

- Enhanced Appointment Management: Automated reminders and follow-up systems.
- Health Monitoring: Integration with IoT health devices.
- Billing & Payment: Integration for managing payments and bills.
## License
#### This project is licensed under the MIT License. [MIT](https://choosealicense.com/licenses/mit/)


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://anik-dv.github.io/AnikKarmokar.github.io)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/anikkarmokar/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/)

