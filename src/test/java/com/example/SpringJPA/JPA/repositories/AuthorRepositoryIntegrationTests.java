package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.TestDataUtil;
import com.example.SpringJPA.JPA.domain.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {
    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest, BookRepository bookRepository) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createAuthorA();
        authorA=underTest.save(authorA);
        Author authorB = TestDataUtil.createAuthorB();
        authorB=underTest.save(authorB);
        Author authorC = TestDataUtil.createAuthorC();
        authorC=underTest.save(authorC);

        Iterable<Author> result =underTest.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanbeUpdatedAndRecalled(){
        Author authorA = TestDataUtil.createAuthorA();
        authorA=underTest.save(authorA);
        authorA.setName("Tom Brady");
        authorA=underTest.save(authorA);

        Optional<Author> result = underTest.findById(authorA.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(authorA.getName());
    }


    @Test
    public void testThatAuthorCanBeDeletedAndRecalled(){
        Author authorA = TestDataUtil.createAuthorB();
        authorA=underTest.save(authorA);

        underTest.deleteById(authorA.getId());
        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        Author authorA = TestDataUtil.createAuthorA();
        authorA=underTest.save(authorA);
        Author authorB=TestDataUtil.createAuthorB();
        authorB=underTest.save(authorB);
        Author authorC=TestDataUtil.createAuthorC();
        authorC=underTest.save(authorC);

        Iterable<Author> result=underTest.ageLessThan(50);
        assertThat(result).contains(authorB, authorC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        Author authorA = TestDataUtil.createAuthorA();
        authorA=underTest.save(authorA);
        Author authorB=TestDataUtil.createAuthorB();
        authorB=underTest.save(authorB);
        Author authorC=TestDataUtil.createAuthorC();
        authorC=underTest.save(authorC);

       Iterable<Author> result = underTest.getAuthorsWithAgeGreaterThan(50);
       assertThat(result).contains(authorA);
    }
}
