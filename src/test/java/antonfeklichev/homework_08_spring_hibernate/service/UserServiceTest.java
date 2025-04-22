package antonfeklichev.homework_08_spring_hibernate.service;

import antonfeklichev.homework_08_spring_hibernate.model.User;
import antonfeklichev.homework_08_spring_hibernate.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Sergey", "Ivanov", "sergey@mail.com");
        user.setId(1L);
    }

    @Test
    void createDelegatesToRepo() {
        when(repository.save(user)).thenReturn(user);

        User saved = service.create(user);

        assertThat(saved).isSameAs(user);
        verify(repository).save(user);
    }

    @Test
    void getThrowsWhenNotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.get(2L))
                .isInstanceOf(NoSuchElementException.class);

        verify(repository).findById(2L);
    }

    @Test
    void getAllReturnsList() {
        List<User> list = List.of(user);
        when(repository.findAll()).thenReturn(list);

        assertThat(service.getAll()).containsExactly(user);
        verify(repository).findAll();
    }
}