
# 🧠 Kaiburr Assessment – Task 1: Java Backend and REST API

This project implements a Java Spring Boot REST API to manage and execute "Task" objects representing shell commands. It stores task data in MongoDB and allows CRUD operations and command execution tracking.

---

## 📌 Features

- Create, retrieve, search, delete "tasks"
- Run shell commands and record executions
- Store all task data in MongoDB
- RESTful endpoints with JSON input/output
- Safe command validation (prevents dangerous commands like `rm`, `shutdown`)

---

## 🏗️ Tech Stack

- Java 17
- Spring Boot
- Spring Data MongoDB
- Maven
- MongoDB (local)
- Postman (for testing)

---

## 🛠️ How to Run the Project

### 1️⃣ Prerequisites

- Java 17 or higher
- MongoDB running on `localhost:27017`
- Maven installed
- Postman for API testing

### 2️⃣ Clone the Repository

```bash
git clone https://github.com/Parkavan2003/kaiburr-task-1-backend.git
cd kaiburr-task-1-backend
```

### 3️⃣ Configure MongoDB

In `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/taskdb
server.port=8080
```

### 4️⃣ Run the Project

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🔌 API Endpoints

| Method | Endpoint                    | Description                          |
|--------|-----------------------------|--------------------------------------|
| PUT    | `/tasks`                    | Create a new task                    |
| PUT    | `/tasks/{id}/run`           | Run the command in the task          |
| GET    | `/tasks`                    | Get all tasks                        |
| GET    | `/tasks/{id}`               | Get task by ID                       |
| GET    | `/tasks/search?name=value`  | Search task(s) by name (partial match) |
| DELETE | `/tasks/{id}`               | Delete a task by ID                  |

---

## 📥 Sample Request & Response

### ▶️ Create Task

**Request**
```
PUT /tasks
```
```json
{
  "id": "123",
  "name": "Print Hello",
  "owner": "John Smith",
  "command": "echo Hello World!"
}
```

---

### ▶️ Run Task

**Request**
```
PUT /tasks/123/run
```

**Response**
```json
{
  "taskExecutions": [
    {
      "startTime": "2025-06-10T12:00:00Z",
      "endTime": "2025-06-10T12:00:01Z",
      "output": "Hello World!"
    }
  ]
}
```

---

## 🧪 Testing with Postman

You can test all endpoints using Postman.

### 💾 Import Predefined Collection

Import this Postman collection for all test cases:

📁 [Download Kaiburr_Task_API_Postman_Collection.json](Kaiburr_Task_API_Postman_Collection.json)

---

## 📸 Screenshots

Each screenshot must include:

- Postman request and response
- System date and time (e.g., taskbar visible)
- Your name visible (can be in Notepad or Terminal window)

> 💡 Tip: You can use Windows Snipping Tool or Snip & Sketch.

### 🖼️ Example

- `screenshot-create-task.png`
- `screenshot-run-task.png`
- `screenshot-search-task.png`
- `screenshot-delete-task.png`

Embed screenshots below:

```markdown
### ✅ Create Task
![Create Task](screenshots/screenshot-create-task.png)

### ✅ Run Task
![Run Task](screenshots/screenshot-run-task.png)
```

---

## 📚 Folder Structure

```
src/main/java/com/kaiburr/taskmanger/
├── controller/        # REST Controller
├── model/             # Task & TaskExecution classes
├── repository/        # MongoDB Repository
├── service/           # Command execution logic
└── TaskmangerApplication.java
```

---

## 👨‍💻 Author

**Parkavan Kumar**  
Kaiburr Assessment 2025 – Backend Task

---

## 📄 License

This project is developed for assessment purposes only.
