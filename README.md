# Dynamic CV Backend Server

Spring Boot microservice backend for Sultan Md Aslam's Dynamic CV & Portfolio, connected to Neon PostgreSQL.

---

## 🛠 Tech Stack
- **Java**: 21 (LTS)
- **Framework**: Spring Boot 4.1.1
- **Persistence**: Spring Data JPA / Hibernate 7
- **Database**: PostgreSQL (Neon Serverless PostgreSQL)
- **API Documentation**: SpringDoc OpenAPI / Swagger UI
- **Build Tool**: Gradle 9.7.1 Wrapper

---

## 🚀 Getting Started

### Database Configuration
The application connects to Neon PostgreSQL using environment variables:
- `SPRING_DATASOURCE_URL`: PostgreSQL JDBC connection URL (with SSL enabled)
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password

Copy `.env.example` to `.env` and fill in your credentials for local development:
```bash
cp .env.example .env
```

### Build & Run
```bash
# Build executable JAR
./gradlew build -x test

# Run application
java -jar build/libs/dynamic-cv-backend-1.0.0.jar
# or via Gradle
./gradlew bootRun
```

Server starts on port **`8080`**.

---

## 📡 API Endpoints

### 1. Dynamic CV
- `GET /api/cv` - Retrieves full composite CV dataset (Personal info, stats, experiences, skill categories, showcases, education).
- `POST /api/cv/seed?overwrite=true` - Reseeds the database with default CV data.

### 2. Personal Profile
- `GET /api/cv/personal` - Get profile info
- `PUT /api/cv/personal` - Update profile info

### 3. Statistics Cards
- `GET /api/cv/stats` - List stats metrics
- `POST /api/cv/stats` - Create stat metric
- `PUT /api/cv/stats/{id}` - Update stat metric
- `DELETE /api/cv/stats/{id}` - Delete stat metric

### 4. Work Experience
- `GET /api/cv/experiences` - List all experiences
- `POST /api/cv/experiences` - Add new experience
- `PUT /api/cv/experiences/{id}` - Update experience & highlights
- `DELETE /api/cv/experiences/{id}` - Delete experience

### 5. Skills & Categories
- `GET /api/cv/skills` - List all skill categories with proficiency
- `POST /api/cv/skills/categories` - Create new skill category
- `PUT /api/cv/skills/categories/{id}` - Update category & skills
- `DELETE /api/cv/skills/categories/{id}` - Delete skill category
- `DELETE /api/cv/skills/items/{id}` - Delete specific skill

### 6. System Architecture Showcases
- `GET /api/cv/showcases` - List architecture showcases
- `POST /api/cv/showcases` - Add new showcase
- `PUT /api/cv/showcases/{id}` - Update showcase
- `DELETE /api/cv/showcases/{id}` - Delete showcase

### 7. Education
- `GET /api/cv/education` - List academic qualifications
- `POST /api/cv/education` - Add qualification
- `PUT /api/cv/education/{id}` - Update qualification
- `DELETE /api/cv/education/{id}` - Delete qualification

### 8. Contact & Inquiries
- `POST /api/contact` - Submit contact inquiry (stored in DB)
- `GET /api/contact` - List inbox messages (Admin)
- `PATCH /api/contact/{id}/read` - Mark message as read
- `DELETE /api/contact/{id}` - Delete inquiry

---

## 📖 Swagger / OpenAPI Docs
When running, open:
`http://localhost:8080/swagger-ui.html`
