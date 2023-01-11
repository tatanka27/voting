package ru.javaops.voting.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {
    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name", nullable = false, unique = true)
    protected String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("name ASC")
    @Schema(hidden = true)
    private List<Item> items;

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
