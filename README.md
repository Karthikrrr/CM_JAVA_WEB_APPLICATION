# 📇 Contact Manager 1.0

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.3-success)](https://spring.io/projects/spring-boot)
[![Tailwind CSS](https://img.shields.io/badge/TailwindCSS-%5E3.0-38B2AC?logo=tailwindcss)](https://tailwindcss.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> A clean, responsive Contact Manager web application using Spring Boot (MVC), Tailwind CSS, Thymeleaf, and PostgreSQL.

---

## 📚 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Setup](#-setup)
- [Running the Project](#-running-the-project)
- [Tailwind Setup](#-tailwind-setup)
- [Testing](#-testing)
- [Folder Structure](#-folder-structure)
- [License](#-license)
- [Contact](#-contact)

---

## 🚀 Features

- ✅ User authentication with Spring Security  
- ✅ Full CRUD support for managing contacts  
- ✅ Tailwind CSS for responsive UI  
- ✅ Form validation via Spring Boot Validator  
- ✅ PostgreSQL integration using Spring Data JPA  
- ✅ Hot reload with Spring Boot DevTools  

---

## 🛠 Tech Stack

| Tech           | Description                                     |
|----------------|-------------------------------------------------|
| Java           | 17                                              |
| Spring Boot    | 3.3.3                                           |
| Thymeleaf      | Server-side HTML templating                     |
| Tailwind CSS   | Utility-first CSS framework                     |
| PostgreSQL     | Relational database                             |
| Maven          | Project build tool                              |
| Spring Security| Authentication & authorization framework        |

---

## 🧱 Architecture

```text
Spring Boot (MVC)
│
├── Controller              # Handles web requests
├── Service                 # Business logic
├── Repository              # JPA repositories
├── Model                   # Entity models
└── Thymeleaf + Tailwind CSS# UI templates and styling

