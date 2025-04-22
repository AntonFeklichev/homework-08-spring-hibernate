package antonfeklichev.homework_08_spring_hibernate.service;

import antonfeklichev.homework_08_spring_hibernate.model.User;
import antonfeklichev.homework_08_spring_hibernate.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User create(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email must not be empty");
        }
        return repo.save(user);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public User get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User " + id + " not found"));
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<User> getAll() {
        return repo.findAll();
    }

    public User update(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User id is required for update");
        }
        return repo.update(user);
    }

    public void delete(Long id) {
        repo.delete(id);
    }
}

