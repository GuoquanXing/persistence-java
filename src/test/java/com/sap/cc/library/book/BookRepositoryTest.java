package com.sap.cc.library.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void clearDb(){
        bookRepository.deleteAll();
    }

    @Test
    void findAllThings_should_be_empty() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).isEmpty();
    }

    @Test
    void saveOneBook_should_returnOneBook(){
        Book cleanCode = BookFixtures.cleanCode();
        bookRepository.save(cleanCode);
        List<Book> books = bookRepository.findAll();

        assertThat(books).hasSize(1);
        Book retrievedBook = books.get(0);
        assertThat(retrievedBook.getTitle()).isEqualTo(cleanCode.getTitle());

        assertThat(retrievedBook.getAuthor().getName()).isEqualTo(cleanCode.getAuthor().getName());
    }

    @Test
    void findBookByTitle(){
        Book cleanCode, designPatterns;
        cleanCode= BookFixtures.cleanCode();
        designPatterns = BookFixtures.designPatterns();

        bookRepository.save(cleanCode);
        bookRepository.save(designPatterns);

        Book book = bookRepository.findByTitle(cleanCode.getTitle());

        assertThat(book.getTitle()).isEqualTo(cleanCode.getTitle());
    }


}
