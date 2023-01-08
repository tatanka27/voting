package ru.javaops.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.voting.model.Voice;

@Repository
@Transactional(readOnly = true)
public interface VoiceRepository extends JpaRepository<Voice, Integer> {
}
