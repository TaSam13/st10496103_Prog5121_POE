# PROG5121 POE Part 1 - Registration and Login System

## Student Details
- **Name**: Samuel Damas
- **Student Number**: ST10496103
- **Course**: PROG5121
- **Project**: POE Part 1

## Project Description
A Java application that implements user registration and login functionality with validation for:
- Username format (must contain underscore and be ≤5 characters)
- Password complexity (≥8 chars, capital letter, number, special character)
- South African cell phone number validation

## Features
- User registration with validation
- Login authentication
- Unit testing with JUnit
- Object-oriented design

## Technologies Used
- Java
- JUnit for testing
- Regular expressions for validation
- IntelliJ IDEA

## How to Run
1. Clone the repository
2. Open in IntelliJ IDEA
3. Run `Main.java` for the application
4. Run `LoginTest.java` for unit tests

## Validation Rules
- **Username**: Must contain '_' and be ≤5 characters
- **Password**: ≥8 chars, 1 capital, 1 number, 1 special character
- **Phone**: South African format with +27 international code

## Project Structure
src/
├── Main.java # Main application
├── Login.java # Login class with validation methods
└── LoginTest.java # Unit tests
