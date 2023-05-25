package ru.bul.springs.ForTestingOne.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "dateofbirth")
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Поле не может быть пустым")
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "phonenumber")
    private String phoneNumber;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "position_id",referencedColumnName = "id")
    private Position position;

    public User() {
    }

    public User(int id, String fullName, LocalDate dateOfBirth, String email, String phoneNumber, Position position) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
