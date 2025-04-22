package antonfeklichev.homework_08_spring_hibernate.repository;

import antonfeklichev.homework_08_spring_hibernate.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // новая БД каждый тест
class UserRepositoryIT {

    @Autowired
    private UserRepository repo;

    @Test
    void saveAndFind() {
        User user = new User("Anna", "Ivanova", "anna@mail.com");
        repo.save(user);
        assertThat(user.getId()).isNotNull();

        User found = repo.findById(user.getId()).orElseThrow();
        assertThat(found.getEmail()).isEqualTo("anna@mail.com");
    }

    @Test
    void findAllReturnsList() {
        repo.save(new User("Anton", "Sidorov", "anton@mail.com"));
        repo.save(new User("Sergey", "Denisov", "sergey@mail.com"));

        List<User> all = repo.findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    void updateChangesEntity() {
        User user = repo.save(new User("Petr", "Ivanovich", "petr@mail.com"));
        user.setFirstName("Andrey");
        repo.update(user);

        assertThat(repo.findById(user.getId()).get().getFirstName()).isEqualTo("Andrey");
    }

    @Test
    void deleteRemovesEntity() {
        User user = repo.save(new User("Olga", "Ivanova", "olga@mail.com"));
        repo.delete(user.getId());
        assertThat(repo.findById(user.getId())).isEmpty();
    }
}
