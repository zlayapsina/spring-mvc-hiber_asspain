package web.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Component
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Поле 'name' не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина имени от 2 до 20 букв")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Поле 'surname' не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина фамилии от 2 до 20 букв")
    @Column(name = "surname")
    private String surname;

    @Min(value = 1, message = "Возраст должен быть больше одного")
    @Column(name = "age")
    private int age;

    public User() {
    }

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User Info: " +
                "name = " + name +
                ", surname = " + surname +
                ", age = " + age;
    }
}
