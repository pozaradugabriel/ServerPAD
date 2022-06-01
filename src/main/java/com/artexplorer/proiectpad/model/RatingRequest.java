package com.artexplorer.proiectpad.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RatingRequest {
    private final Long museumId;
    private final Integer grade;
}
