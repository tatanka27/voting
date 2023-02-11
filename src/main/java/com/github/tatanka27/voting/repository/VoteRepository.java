package com.github.tatanka27.voting.repository;

import com.github.tatanka27.voting.error.DataConflictException;
import com.github.tatanka27.voting.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId and v.dateVote = :dateVote")
    Optional<Vote> getByUserIdAndDateVote(int userId, LocalDate dateVote);

    @Query("SELECT v FROM Vote v WHERE v.id = :id and v.user.id = :userId")
    Optional<Vote> get(int id, int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId")
    List<Vote> findAllByUserId(int userId);

    default Vote getExistedOrBelonged(int id, int userId) {
        return get(id, userId).orElseThrow(
                () -> new DataConflictException("Vote id=" + id + " is not exist or doesn't belong to User id=" + userId));
    }
}
