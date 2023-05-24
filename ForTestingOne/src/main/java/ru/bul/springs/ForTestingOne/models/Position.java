package ru.bul.springs.ForTestingOne.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2,max = 100)
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2,max = 100)
    private String description;


    public Position(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Position() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
