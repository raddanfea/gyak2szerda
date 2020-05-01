package legoset;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.List;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import person.Address;
import person.Person;
import legoset.model.LegoSet;

@Log4j2
public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");

    private static void RandomPerson(){
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();

        Date date = faker.date().birthday();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        try {
            em.getTransaction().begin();
            Address address = new Address(faker.address().country(),faker.address().state(),faker.address().cityName(),faker.address().streetAddress(),faker.address().zipCode());
            em.persist(new Person(null, faker.name().fullName(), localDate, faker.options().option(Person.Gender.values()),address,faker.internet().emailAddress(),faker.company().profession()));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static List<Person> getPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Person l ORDER BY l.id", Person.class).getResultList();
        } finally {
            em.close();
        }
    }

    /*
    private static List<LegoSet> getLegoSets() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM LegoSet l ORDER BY l.number", LegoSet.class).getResultList();
        } finally {
            em.close();
        }
    }

    private static Long getTotalPieces() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT SUM(pieces) FROM LegoSet l", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    private static void deleteLegoSets() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            long count = em.createQuery("DELETE FROM LegoSet").executeUpdate();
            log.info("Deleted {} LEGO set(s)", count);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

     */

    public static void main(String[] args) {

        /* createLegoSets();
        log.info("All LEGO sets:");
        getLegoSets().forEach(log::info);
        log.info("Total number of LEGO pieces: {}", getTotalPieces());
        deleteLegoSets(); */

        log.info("Generating random people.");

        int NumberOfRandomPeople = 50;

        for (int i = 0; i < NumberOfRandomPeople; i++) {
            RandomPerson();
        }
        log.info("Random people generated.");
        getPersons().forEach(log::info);
        log.info("Random people ead from database.");
        emf.close();
        log.info("Pipeline force closed and ending script.");
    }

}
