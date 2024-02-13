import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        //author ekleme
        Author a1 = new Author();
        a1.setName("Jack London");
        a1.setCountry("ABD");
        a1.setBirthDate(LocalDate.parse("12/01/1876", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        entityManager.persist(a1);

        //Categry ekleme
        Category c1 = new Category();
        c1.setName("Otobiyografi");
        c1.setDescription("Yaşam Öyküsü");
        entityManager.persist(c1);

        //Publisher ekleme
        Publisher p1 = new Publisher();
        p1.setName("TT Yayınları");
        p1.setAddress("İstanbul");
        p1.setEstablishmentYear(1999);
        entityManager.persist(p1);

        //Book ekleme
        Book b1 = new Book();
        b1.setName("Martin Eden");
        b1.setBookStock(10);
        b1.setPublicationYear(2017);
        b1.setPublisher(p1);
        b1.setAuthor(a1);
        entityManager.persist(b1);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(c1);
        b1.setCategoryList(categoryList);

        //bookBorrowing ekleme
        BookBorrowing bb1 = new BookBorrowing();
        bb1.setName("Ahmet Uzun");
        bb1.setBorrowingDate(LocalDate.now());
        bb1.setReturnDate(LocalDate.parse("28/02/2024" , DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        bb1.setBook(b1);
        entityManager.persist(bb1);

        //2.book ekleme
        Book b2 = new Book();
        b2.setName("Vahşetin Çağrısı");
        b2.setBookStock(20);
        b2.setPublicationYear(2012);
        b2.setPublisher(p1);
        b2.setAuthor(a1);
        entityManager.persist(b2);
        b2.setCategoryList(categoryList);

        Book book = entityManager.find(Book.class,1);
        System.out.println(book.toString());
        transaction.commit();
    }
}