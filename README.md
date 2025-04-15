# RSA Cryptography Implementation

A Java implementation of the RSA public-key cryptography algorithm, including key generation, encryption/decryption, and Fermat's factorization attack demonstration.

## Features

- **RSA Key Generation**: Generate public and private key pairs with specified bit lengths
- **RSA Encryption/Decryption**: Encrypt and decrypt messages using RSA algorithm
- **Fermat's Factorization Attack**: Demonstrate vulnerability when RSA uses weak prime factors

## Project Structure

- `Main.java` - Main class demonstrating all features
- `RSA.java` - Core RSA implementation with encryption/decryption methods
- `RSAKey.java` - RSA key data structure
- `KeyGenerator.java` - RSA key pair generation
- `FermatDecrypt.java` - Fermat's factorization attack implementation

## Usage

Run the `Main` class to see demonstrations of:
1. RSA key generation
2. Message encryption and decryption
3. Fermat factorization attack on weak RSA keys

## Educational Purpose

This project was created for educational purposes to understand:
- How RSA cryptography works
- The importance of using strong prime factors
- Common cryptographic attacks like Fermat's factorization

## Note

This implementation is for learning purposes only and should not be used in production systems.
