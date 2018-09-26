package electionshomework;

import javax.persistence.*;

@Entity
@Table(name = "zad2_glosy")
public class Vote {

    @Id
    @Column(name = "id_glosu")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_ucznia")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_kandydata")
    private Candidate candidate;

    public Vote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                '}';
    }
}
