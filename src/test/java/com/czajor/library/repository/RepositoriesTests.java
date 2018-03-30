package com.czajor.library.repository;

import com.czajor.library.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoriesTests {
    @Autowired
    private BookDao bookDao;

    @Test
    public void testAddBook() {
        // Given
        Book book1 = new Book("Title 1", "Author 1", 2013);
        Book book2 = new Book("Title 2", "Author 2", 1995);
        Book book3 = new Book("Title 3", "Author 3", 1980);

        bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);

        // When
        int amountOfBooks = bookDao.findAll().size();
        String book3Author = bookDao.findByTitle(book3.getTitle()).getAuthor();

        // Then
        assertEquals(amountOfBooks, 3);
        assertEquals("Author 3", book3Author);

        // CleanUp
        bookDao.deleteAll();
    }
}
