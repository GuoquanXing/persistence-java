package com.sap.cc.library.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DataDurabilityTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void populateDb() {
        bookRepository.save(BookFixtures.cleanCode());
        bookRepository.save(BookFixtures.designPatterns());

        List<Book> bookList = bookRepository.findAll();

        assertThat(bookList).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void isDbPopulated() {
        List<Book> bookList = bookRepository.findAll();

        assertThat(bookList).hasSizeGreaterThanOrEqualTo(2);
    }
}