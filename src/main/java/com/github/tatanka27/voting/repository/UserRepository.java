package com.github.tatanka27.voting.repository;

import com.github.tatanka27.voting.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmailIgnoreCase(String email);

}
