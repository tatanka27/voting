package ru.javaops.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Optional<Vote> findByUserIdAndDateVote(int userId, LocalDate dateVote);
}
