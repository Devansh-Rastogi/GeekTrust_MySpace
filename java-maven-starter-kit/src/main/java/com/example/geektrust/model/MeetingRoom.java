package com.example.geektrust.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class MeetingRoom {
    private String id;
    private String name;
    private List<TimeInterval> occupiedIntervalList;
    private int maxOccupancy;
    private List<TimeInterval> bufferTimeInterval;

    public MeetingRoom(String name, List<TimeInterval> bufferTimeInterval){
        this.name = name;
        this.bufferTimeInterval = bufferTimeInterval;
        this.occupiedIntervalList = new ArrayList<>();
    }
}