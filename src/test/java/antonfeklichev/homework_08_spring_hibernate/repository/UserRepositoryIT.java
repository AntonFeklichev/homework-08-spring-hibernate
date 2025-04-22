package antonfeklichev.homework_08_spring_hibernate.repository;

import antonfeklichev.homework_08_spring_hibernate.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.profiles.active=test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // новая БД каждый тест
class UserRepositoryIT {

    @Autowired
    private UserRepository repository;

    @Test
    void saveAndFind() {
        User user = new User("Anna", "Ivanova", "anna@mail.com");
        repository.save(user);
        assertThat(user.getId()).isNotNull();

        User found = repository.findById(user.getId()).orElseThrow();
        assertThat(found.getEmail()).isEqualTo("anna@mail.com");
    }

    @Test
    void findAllReturnsList() {
        repository.save(new User("Anton", "Sidorov", "anton@mail.com"));
        repository.save(new User("Sergey", "Denisov", "sergey@mail.com"));

        List<User> all = repository.findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    void updateChangesEntity() {
        User user = repository.save(new User("Petr", "Ivanovich", "petr@mail.com"));
        user.setFirstName("Andrey");
        repository.update(user);

        assertThat(repository.findById(user.getId()).get().getFirstName()).isEqualTo("Andrey");
    }

    @Test
    void deleteRemovesEntity() {
        User user = repository.save(new User("Olga", "Ivanova", "olga@mail.com"));
        repository.delete(user.getId());
        assertThat(repository.findById(user.getId())).isEmpty();
    }
}