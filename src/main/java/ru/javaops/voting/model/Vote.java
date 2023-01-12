package ru.javaops.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "rest_id", "date_vote"}, name = "voice_unique_user_rest_datevote_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseEntity {
    @Column(name = "date_vote", nullable = false)
    @NotNull
    private LocalDate dateVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    public Vote(Integer id, LocalDate dateVote, User user, Restaurant restaurant) {
        super(id);
        this.dateVote = dateVote;
        this.user = user;
        this.restaurant = restaurant;
    }
}
