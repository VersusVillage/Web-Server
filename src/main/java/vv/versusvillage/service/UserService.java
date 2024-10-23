package vv.versusvillage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vv.versusvillage.domain.User;
import vv.versusvillage.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String register(User user) {
        validateDuplicateNickname(user);
        user.setCreatedAt(LocalDateTime.now());
        User newUser = userRepository.save(user);
        return newUser.getUserNickname();
    }

    private void validateDuplicateNickname(User user) {
        userRepository.findByNickname(user.getUserNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("해당 닉네임은 사용힐 수 없습니다.");
                });
    }

    @Transactional
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<User> findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


}
