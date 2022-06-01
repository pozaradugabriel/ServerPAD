package com.artexplorer.proiectpad.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Integer grade;

    @NotNull
    private Long museumId;

    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (
            name = "userId",
            referencedColumnName = "id"
    )
    private UserInfo user;

    public Rating(@NotEmpty Integer grade, @NotNull Long museumId, @NotNull UserInfo user) {
        this.grade = grade;
        this.museumId = museumId;
        this.user = user;
    }
}
