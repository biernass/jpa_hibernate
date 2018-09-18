package electionshomework;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExElectionsRunner {

    static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("mysql");
    static EntityManager em = managerFactory.createEntityManager();

    public static void main(String[] args) {


        //System.out.println(getStudentsTest(em));
        //genderCount(em);
        //PPU(em);
        getNumberOfAbsentVote(em);

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

    //2.1
    private static void genderCount(EntityManager em) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Student> from = criteriaQuery.from(Student.class);

        List<String> resultList = em.createQuery(criteriaQuery.select(from.get("name"))).getResultList();

        int male = 0;
        int female = 0;
        for (String string : resultList) {
            if (string.endsWith("a")) {
                female++;
            } else {
                male++;
            }
        }
        System.out.println("Kobiety: " + female + ". Mężczyźni: " + male);
    }

    // 2.2
    private static void PPU(EntityManager em) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Vote> fromVote = criteriaQuery.from(Vote.class);
        Root<Candidate> fromCandidate = criteriaQuery.from(Candidate.class);

        criteriaQuery.multiselect(fromCandidate.get("name").alias("name"), fromCandidate.get("surname").alias("surname"),
                criteriaBuilder.count(fromCandidate.get("name")).alias("count"))
                .where(criteriaBuilder.equal(fromVote.get("candidate"), fromCandidate.get("id")))
                .groupBy(fromCandidate.get("name"), fromCandidate.get("surname"))
                .orderBy(criteriaBuilder.desc(criteriaBuilder.count(fromCandidate.get("name"))));

        TypedQuery<Tuple> typedQuery = em.createQuery(criteriaQuery);
        List<Tuple> resultList = typedQuery.setMaxResults(10).getResultList();
        for (Tuple t : resultList) {
            System.out.println(t.get("name") + ", " + t.get("surname") + ", " + t.get("count"));
        }
    }

    // 2.3
    private static void getNumberOfAbsentVote(EntityManager em) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        CriteriaQuery<Long> cqAllStudents = criteriaBuilder.createQuery(Long.class);
        Root<Vote> voteRoot = criteriaQuery.from(Vote.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        criteriaQuery.select(criteriaBuilder.countDistinct(studentRoot.get("id")).alias("count"))
                .where(criteriaBuilder.equal(voteRoot.get("student"), studentRoot.get("id")));

        TypedQuery<Long> allVotes = em.createQuery(criteriaQuery);

        cqAllStudents.select(criteriaBuilder.countDistinct(studentRoot.get("id"))).from(Student.class);

        TypedQuery<Long> allStudents = em.createQuery(cqAllStudents);

        System.out.println(allStudents.getSingleResult() - allVotes.getSingleResult());

    }


}
