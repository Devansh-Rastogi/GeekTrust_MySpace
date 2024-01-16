package com.example.geektrust.strategy;

import com.example.geektrust.model.BookRequest;
import com.example.geektrust.model.MeetingRoom;

public interface AllotmentStrategy {
    MeetingRoom allotMeetingRoom(BookRequest bookRequest);
}
