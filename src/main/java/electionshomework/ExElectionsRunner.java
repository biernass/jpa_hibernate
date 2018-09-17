package electionshomework;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExElectionsRunner {

    static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("mysql");
    static EntityManager em = managerFactory.createEntityManager();

    public static void main(String[] args) {


        System.out.println(getStudentsTest(em));

        em.close();
        managerFactory.close();
    }

    public static List<Student> getStudentsTest(EntityManager em) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> from = query.from(Student.class);
        query.select(from);
        return em.createQuery(query).getResultList();
    }




}