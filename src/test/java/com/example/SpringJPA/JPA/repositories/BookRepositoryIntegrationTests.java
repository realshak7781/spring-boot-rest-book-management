package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.TestDataUtil;
import com.example.SpringJPA.JPA.domain.Author;
import com.example.SpringJPA.JPA.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class BookRepositoryIntegrationTests {
    private BookRepository underTest;
    private AuthorRepository authorRepository;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest,AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookuthorCanBeCreatedAndRecalled(){
        // 1. Create a test Author (ensure ID is null so DB generates a new one)
        Author author=TestDataUtil.createAuthorC();
        Book bookA=TestDataUtil.createBookA(author);
        // 3. Save the book. Because of CascadeType.ALL, this saves the author too!
        bookA=underTest.save(bookA);

        // 4. Recall the book from the database
        Optional<Book> result = underTest.findById(bookA.getIsbn());

        // 5. Assertions
        assertThat(result).isPresent();
        assertThat(result.get().getIsbn()).isEqualTo(bookA.getIsbn());
        assertThat(result.get().getTitle()).isEqualTo(bookA.getTitle());

        // Check that the author was cascaded correctly
        assertThat(result.get().getAuthor()).isNotNull();
        assertThat(result.get().getAuthor().getName()).isEqualTo(author.getName());
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        // 2. Create Book A (Using the Util methods)
        Author authorA = TestDataUtil.createAuthorA();
        Book bookA = TestDataUtil.createBookA(authorA);
        bookA=underTest.save(bookA);

        // 3. Create Book B
        Author authorB = TestDataUtil.createAuthorB();
        Book bookB = TestDataUtil.createBookB(authorB);
        bookB=underTest.save(bookB);

        // 4. Create Book C
        Author authorC = TestDataUtil.createAuthorC();
        Book bookC = TestDataUtil.createBookC(authorC);
        bookC=underTest.save(bookC);

        // 5. Retrieve all books
        Iterable<Book> result = underTest.findAll();

        // 6. Verify
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(bookA, bookB, bookC);

    }
}
