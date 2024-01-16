package com.example.geektrust.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class C_CaveMeetingRoom extends MeetingRoom {

    public C_CaveMeetingRoom(String name, List<TimeInterval> bufferTimeInterval) {
        super(name, bufferTimeInterval);
        this.setMaxOccupancy(3);
    }
}
