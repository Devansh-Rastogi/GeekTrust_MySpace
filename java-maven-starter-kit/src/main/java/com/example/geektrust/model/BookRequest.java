package com.example.geektrust.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
public class BookRequest {
    private TimeInterval timeInterval;
    private int personCapacity;
}
