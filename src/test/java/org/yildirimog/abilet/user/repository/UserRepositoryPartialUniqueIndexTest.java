package org.yildirimog.abilet.user.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.yildirimog.abilet.user.entity.User;
import org.yildirimog.abilet.user.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Soft-delete ile uyumlu partial unique index'in (V1__init.sql) gerçek Postgres
 * üzerinde doğru çalıştığını doğrular. Docker yoksa otomatik atlanır.
 */
@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest
@TestPropertySource(properties = {
        "spring.flyway.enabled=true",
        "spring.jpa.hibernate.ddl-auto=validate",
        "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect"
})
class UserRepositoryPartialUniqueIndexTest {

    @Container
    @ServiceConnection
    static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private UserRepository userRepository;

    private User newUser(String email) {
        return User.builder()
                .firstName("Ada")
                .lastName("Lovelace")
                .password("hashed")
                .email(email)
                .birthDate(LocalDate.of(1990, 1, 1))
                .role(Role.CUSTOMER)
                .build();
    }

    @Test
    void twoActiveUsersWithSameEmail_violateUniqueIndex() {
        userRepository.saveAndFlush(newUser("dup@example.com"));

        assertThatThrownBy(() -> userRepository.saveAndFlush(newUser("dup@example.com")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void emailCanBeReusedAfterSoftDelete() {
        User first = userRepository.saveAndFlush(newUser("reuse@example.com"));

        first.setDeletedAt(LocalDateTime.now());
        userRepository.saveAndFlush(first);

        User second = userRepository.saveAndFlush(newUser("reuse@example.com"));

        assertThat(second.getId()).isNotNull();
        assertThat(second.getId()).isNotEqualTo(first.getId());
    }
}
