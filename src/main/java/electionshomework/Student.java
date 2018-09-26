package electionshomework;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zad2_uczniowie")
public class Student {

    @Id
    @Column(name= "id_ucznia")
    private int id;
    @Column(name = "imie")
    private String name;
    @Column(name = "nazwisko")
    private String surname;
    @Column(name = "klasa")
    private String className;
    @Column(name = "rok_nauki")
    private int yearOfStudy;
    @OneToMany(mappedBy = "student")
    private List<Vote> votes;

    public Student() {
    }

    public Student(int id, String name, String surname, String className, int yearOfStudy) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.className = className;
        this.yearOfStudy = yearOfStudy;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", className='" + className + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                '}';
    }
}
