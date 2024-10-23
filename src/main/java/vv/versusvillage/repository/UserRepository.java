package vv.versusvillage.repository;

import vv.versusvillage.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByNickname(String name);
    List<User> findAll();
}
