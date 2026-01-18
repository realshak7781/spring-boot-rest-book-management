package com.example.SpringJPA.JPA.repositories;

import com.example.SpringJPA.JPA.TestDataUtil;
import com.example.SpringJPA.JPA.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorEntityRepositoryIntegrationTests {
    private AuthorRepository underTest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest, BookRepository bookRepository) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtil.createAuthorA();
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        AuthorEntity authorEntityA = TestDataUtil.createAuthorA();
        authorEntityA =underTest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtil.createAuthorB();
        authorEntityB =underTest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtil.createAuthorC();
        authorEntityC =underTest.save(authorEntityC);

        Iterable<AuthorEntity> result =underTest.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactly(authorEntityA, authorEntityB, authorEntityC);
    }

    @Test
    public void testThatAuthorCanbeUpdatedAndRecalled(){
        AuthorEntity authorEntityA = TestDataUtil.createAuthorA();
        authorEntityA =underTest.save(authorEntityA);
        authorEntityA.setName("Tom Brady");
        authorEntityA =underTest.save(authorEntityA);

        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(authorEntityA.getName());
    }


    @Test
    public void testThatAuthorCanBeDeletedAndRecalled(){
        AuthorEntity authorEntityA = TestDataUtil.createAuthorB();
        authorEntityA =underTest.save(authorEntityA);

        underTest.deleteById(authorEntityA.getId());
        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        AuthorEntity authorEntityA = TestDataUtil.createAuthorA();
        authorEntityA =underTest.save(authorEntityA);
        AuthorEntity authorEntityB =TestDataUtil.createAuthorB();
        authorEntityB =underTest.save(authorEntityB);
        AuthorEntity authorEntityC =TestDataUtil.createAuthorC();
        authorEntityC =underTest.save(authorEntityC);

        Iterable<AuthorEntity> result=underTest.ageLessThan(50);
        assertThat(result).contains(authorEntityB, authorEntityC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        AuthorEntity authorEntityA = TestDataUtil.createAuthorA();
        authorEntityA =underTest.save(authorEntityA);
        AuthorEntity authorEntityB =TestDataUtil.createAuthorB();
        authorEntityB =underTest.save(authorEntityB);
        AuthorEntity authorEntityC =TestDataUtil.createAuthorC();
        authorEntityC =underTest.save(authorEntityC);

       Iterable<AuthorEntity> result = underTest.getAuthorsWithAgeGreaterThan(50);
       assertThat(result).contains(authorEntityA);
    }
}
