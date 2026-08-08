# 🏨 Cloud-Aware Hotel Management System

A multi-modular enterprise application engineered to automate frontline hospitality workflows, manage guest check-ins, track room inventories, and streamline administrative records in real time.

Built as part of the **Master of Computer Applications (MCA) Major Project** at **Bundelkhand University, Jhansi**, with architectural enhancements aligned with the **IBM Cloud** ecosystem.

---

## 🛠️ Tech Stack & Dependencies

* **Language:** Core Java (JDK 8+)
* **GUI Framework:** Java Swing, AWT
* **Database Connectivity:** JDBC (Java Database Connectivity)
* **Database Management:** MySQL Server 8.0 & MySQL Workbench
* **Ide/Environment:** Apache NetBeans IDE
* **Version Control:** Git & GitHub

---

## 🚀 Key Architectural Features

1. **Relational Data Integrity:** Designed schema structures using strict Primary Keys (Aadhar/Document IDs) and Foreign Key constraints with cascading behavior to eliminate duplicate entries and transaction collisions.
2. **Real-time State Synchronization:** Implemented atomic `autoCommit` transactional execution within the JDBC pipeline to instantly reflect room status modifications (`Available` → `Occupied`) upon customer check-in.
3. **Defensive Exception Handling:** Structured backend driver connection routines to handle connection timeouts, port mapping errors (Port 3306), and prevent runtime `NullPointerExceptions`.
4. **Cloud Migration Pipeline:** Actively transitioning localized database instances into high-availability cloud relational storage layers (IBM Cloud).

---

## 📊 Database Schema Setup

To initialize the database locally, execute the following SQL script in **MySQL Workbench**:

```sql
CREATE DATABASE IF NOT EXISTS hotelmanagementsystem;
USE hotelmanagementsystem;

CREATE TABLE login (
    username VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE room (
    room_number VARCHAR(10) PRIMARY KEY,
    availability VARCHAR(20) DEFAULT 'Available',
    cleaning_status VARCHAR(20) DEFAULT 'Cleaned',
    price VARCHAR(15) NOT NULL,
    bed_type VARCHAR(20)
);

CREATE TABLE employee (
    name VARCHAR(50) NOT NULL,
    age VARCHAR(5),
    gender VARCHAR(10),
    job VARCHAR(30),
    salary VARCHAR(15),
    phone VARCHAR(15),
    email VARCHAR(50),
    aadhar VARCHAR(20) PRIMARY KEY
);

CREATE TABLE customer (
    document_type VARCHAR(30),
    document_number VARCHAR(30) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    gender VARCHAR(10),
    country VARCHAR(30),
    room_number VARCHAR(10),
    checkintime VARCHAR(50),
    deposit VARCHAR(15),
    FOREIGN KEY (room_number) REFERENCES room(room_number) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO login VALUES ('admin', '12345');
