package com.example.geektrust.model;

import java.util.List;

public class D_TowerMeetingRoom extends MeetingRoom{
    public D_TowerMeetingRoom(String d_tower, List<TimeInterval> bufferInterval){
        super(d_tower, bufferInterval);
        this.setMaxOccupancy(7);
    }
}
