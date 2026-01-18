package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.TestDataUtil;
import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import com.example.SpringJPA.JPA.domain.entities.BookEntity;
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
public class BookEntityRepositoryIntegrationTests {
    private BookRepository underTest;
    private AuthorRepository authorRepository;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookuthorCanBeCreatedAndRecalled(){
        // 1. Create a test Author (ensure ID is null so DB generates a new one)
        AuthorEntity authorEntity =TestDataUtil.createAuthorC();
        BookEntity bookEntityA =TestDataUtil.createBookA(authorEntity);
        // 3. Save the book. Because of CascadeType.ALL, this saves the author too!
        bookEntityA =underTest.save(bookEntityA);

        // 4. Recall the book from the database
        Optional<BookEntity> result = underTest.findById(bookEntityA.getIsbn());

        // 5. Assertions
        assertThat(result).isPresent();
        assertThat(result.get().getIsbn()).isEqualTo(bookEntityA.getIsbn());
        assertThat(result.get().getTitle()).isEqualTo(bookEntityA.getTitle());

        // Check that the author was cascaded correctly
        assertThat(result.get().getAuthorEntity()).isNotNull();
        assertThat(result.get().getAuthorEntity().getName()).isEqualTo(authorEntity.getName());
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        // 2. Create Book A (Using the Util methods)
        AuthorEntity authorEntityA = TestDataUtil.createAuthorA();
        BookEntity bookEntityA = TestDataUtil.createBookA(authorEntityA);
        bookEntityA =underTest.save(bookEntityA);

        // 3. Create Book B
        AuthorEntity authorEntityB = TestDataUtil.createAuthorB();
        BookEntity bookEntityB = TestDataUtil.createBookB(authorEntityB);
        bookEntityB =underTest.save(bookEntityB);

        // 4. Create Book C
        AuthorEntity authorEntityC = TestDataUtil.createAuthorC();
        BookEntity bookEntityC = TestDataUtil.createBookC(authorEntityC);
        bookEntityC =underTest.save(bookEntityC);

        // 5. Retrieve all books
        Iterable<BookEntity> result = underTest.findAll();

        // 6. Verify
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(bookEntityA, bookEntityB, bookEntityC);

    }
}
