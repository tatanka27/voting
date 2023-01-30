package com.github.tatanka27.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import static com.github.tatanka27.voting.util.ValidationUtil.checkExisted;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id")
    T get(int id);

    default T getExisted(int id) {
        return checkExisted(get(id), id);
    }
}
