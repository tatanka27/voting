package ru.javaops.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javaops.voting.model.Voice;

@Repository
public interface VoiceRepository extends JpaRepository<Voice, Integer> {
}
