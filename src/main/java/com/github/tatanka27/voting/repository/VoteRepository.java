package com.github.tatanka27.voting.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.github.tatanka27.voting.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    Optional<Vote> findByUserIdAndDateVote(int userId, LocalDate dateVote);
}
