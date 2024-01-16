package com.example.geektrust.model;

import java.util.List;

public class G_MansionMeetingRoom extends MeetingRoom{
    public G_MansionMeetingRoom(String g_mansion, List<TimeInterval> bufferInterval){
        super(g_mansion, bufferInterval);
        this.setMaxOccupancy(20);
    }
}
