# <p align="center">**Welcome to Finance Manegement App**</p>

# 📋 Table of Contents
- [Overview](#overview)
- [Team Information](#Team-Information)
- [Features](#features)
- [Project Structure](#project-structure)
- [Techology Used](#technology-used)
- [Setup & Installation](#setup-&-installation)
- [Systems Requirement](#systems-requirement)
- [Conclusion](#Conclusion )
# 📌 Overview
**The Finance Manager Application** is a Java-based console application created with the purpose of aiding users to track as well as manage all key aspects relating to their financial situation and plans—savings, income, and expenses. It provides user-friendly tools and interfaces for the users to be able to input, modify, and track their current status and data using key database and encryption functions of ArrayList as well as HashMap.
## 👥 Team Information
- **Members:**
  - Dim Ponhakvontey
  - Neth Barom Phaknit
# 🚀 Features
## 1. Login/Signup 
- Allows the user to create a new account that will be stored in the database 
- Promptly login with their current account to use daily
## 2. User Status Display: 
Fully displaying the name of the user as well as their status in terms of net balance and savings balance. They can also choose to:
- Switch accounts for different users
- Log out and exit the program
- Return to main menu
## 3. Add Transaction:
- Adding expenses with details and date
- Adding income with details and date 
## 4. View transaction:
Displays the history of all transactions (expenses and income) in 3 main forms—table, row, key chart
## 5. Built in Calculating System: 
Keeps track of the net balance and the savings balance each time there is an addition or subtraction based on transactions and savings modifications calculator.
## 6. Savings Module:
- Setting/modifying savings rate (5-20%)
- Set/modify savings goal (amount)
- Set/modify time period (in months)
- Calculate Monthly Savings
- View Savings status and table

# 📂 Project Structure
```
Finance Manager Application/
├──src/
│   ├──DraftFinanceManager.java
│   ├──Main.java
│   ├──SavingsManager.java
│   ├──SavingsRecord.java
│   ├──SessionManager.java
│   ├──Transaction.java
│   ├──TransactionManager.java
│   ├──User.java
├──README.md
```
# 🌐 Technology Used
- **Java Version:** Java 8 or higher
- **IDE:** IntelliJ IDEA, Eclipse, or any Java-supporting IDE
- **OS:** Windows, macOS, Linux
- **Disk Space:** Minimum 100MB
- **Memory:** Minimum 512MB RAM
# 🛠️ Setup & Installation
## 1. Prerequisites:
- Java 17 or higher (21 recommended)
- VSCode+Eclipse IDE
- HashMap/ArrayList
## 2. Clone the Repository
```
git clone https://github.com/Ponhakvontey/Project.git
cd Project
ls 
```
## 3. Run the Program
Compile and execute the main Java file:
```
javac src/Main.java
java src.Main
```

# 💻 Systems Requirement
- **OS:** Windows 10+ / macOS 10.15 / Linux
- **CPU:** Intel Core i3+ (or equivalent)
- **RAM:** 4GB+
- **Storage:** 500MB+ of free disk space
- **Java Version:** Java 17 or higher

# Conclusion 
**The Personal Finance Manager** is a practical and educational project demonstrating core software engineering ethics and principles. It empowers users to take control of their personal finances while showcasing object-oriented programming, modular design, and persistent data handling. This tool serves not only as a functional application but also as a foundation for future feature expansion and team collaboration.
