package com.czajor.library.repository;

import com.czajor.library.model.Book;
import com.czajor.library.model.BookCopy;
import com.czajor.library.model.Borrow;
import com.czajor.library.model.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoriesTests {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookCopyDao bookCopyDao;

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private BorrowDao borrowDao;

    @Test
    public void testAddBook() {
        try {
            // Given
            Book book1 = new Book("Title 1", "Author 1", 2013);
            Book book2 = new Book("Title 2", "Author 2", 1995);
            Book book3 = new Book("Title 3", "Author 3", 1980);

            bookDao.save(book1);
            bookDao.save(book2);
            bookDao.save(book3);

            // When
            int amountOfBooks = bookDao.findAll().size();
            String book3Author = bookDao.findByTitle(book3.getTitle()).get(0).getAuthor();

            // Then
            assertEquals(3, amountOfBooks);
            assertEquals("Author 3", book3Author);
        } finally {
            // CleanUp
            bookDao.deleteAll();
        }
    }

    @Test
    public void testAddBookUnique() {
        try {
            // Given
            Book book1 = new Book("Title 1", "Author 1", 2013);
            Book book2 = new Book("Title 1", "Author 3", 1980);
            Book book3 = new Book("Title 1", "Author 1", 2013);

            bookDao.save(book1);
            bookDao.save(book2);
            bookDao.save(book3);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // When
            int amountOfBooks = bookDao.findAll().size();

            // Then
            assertEquals(2, amountOfBooks);

            // CleanUp
            bookDao.deleteAll();
        }
    }

    @Test
    public void testAddBookCopy() {
        try {
            // Given
            Book book1 = new Book("Title 1", "Author 1", 2013);
            Book book2 = new Book("Title 2", "Author 2", 1995);
            bookDao.save(book1);
            bookDao.save(book2);
            BookCopy book1Copy = new BookCopy(bookDao.findByTitle("Title 1").get(0), BookCopy.ACTIVE);
            BookCopy book1Copy2 = new BookCopy(bookDao.findByTitle("Title 1").get(0), BookCopy.ACTIVE);
            BookCopy book2Copy = new BookCopy(bookDao.findByTitle("Title 2").get(0), BookCopy.LOST);
            bookCopyDao.save(book1Copy);
            bookCopyDao.save(book1Copy2);
            bookCopyDao.save(book2Copy);

            // When
            int amountOfBookCopies = bookCopyDao.findAll().size();
            Book book = bookDao.findByTitle("Title 1").get(0);
            int amountOfBook1Copy = bookCopyDao.findAllByBook(book).size();

            // Then
            assertEquals(3, amountOfBookCopies);
            assertEquals(2, amountOfBook1Copy);
        } finally {
            // CleanUp
            bookCopyDao.deleteAll();
            bookDao.deleteAll();
        }
    }

    @Test
    public void testAddReader() {
        try {
            // Given
            Reader reader1 = new Reader("John", "Kowalsky");
            Reader reader2 = new Reader("Michael", "Jackson");
            readerDao.save(reader1);
            readerDao.save(reader2);

            // When
            int amountOfReaders = readerDao.findAll().size();
            LocalDate reader1EstDate = readerDao.findBySurname("Kowalsky").get(0).getEstDate().toLocalDate();

            // Then
            assertEquals(2, amountOfReaders);
            assertEquals(reader1.getEstDate().toLocalDate(), reader1EstDate);
        } finally {
            // CleanUp
            readerDao.deleteAll();
        }
    }

    @Test
    public void testAddBorrow() {
        try {
            // Given
            Book book = new Book("Title 1", "Author 1", 2013);
            bookDao.save(book);
            BookCopy bookCopy = new BookCopy(bookDao.findByTitle("Title 1").get(0), BookCopy.ACTIVE);
            bookCopyDao.save(bookCopy);
            Reader reader = new Reader("John", "Kowalsky");
            readerDao.save(reader);
            Borrow borrow = new Borrow(bookCopyDao.findAll().get(0), readerDao.findAll().get(0));
            borrowDao.save(borrow);

            // When
            Borrow borrowFromDb = borrowDao.findAll().get(0);
            String readerSurname = borrowFromDb.getReader().getSurname();
            int bookYear = borrowFromDb.getBookCopy().getBook().getYear();
            int borrowedBooksAmount = borrowDao.findAll().size();

            // Then
            assertEquals(reader.getSurname(), readerSurname);
            assertEquals(book.getYear(), bookYear);
            assertEquals(1, borrowedBooksAmount);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // CleanUp
            borrowDao.deleteAll();
            readerDao.deleteAll();
            bookCopyDao.deleteAll();
            bookDao.deleteAll();
        }
    }
}
