package electionshomework;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zad2_kandydaci")
public class Candidate {

    @Id
    @Column(name = "id_kandydata")
    private int id;
    @Column(name = "imie")
    private String name;
    @Column(name = "nazwisko")
    private String surname;

    public Candidate() {
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
}
