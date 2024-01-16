package com.example.geektrust.strategy;

import com.example.geektrust.exceptions.NoVacantRoomException;
import com.example.geektrust.model.BookRequest;
import com.example.geektrust.model.MeetingRoom;

import java.util.List;

public class C_CaveMeetingRoomAllotmentStrategy extends ChainOfResponsibilityStrategy{
    public C_CaveMeetingRoomAllotmentStrategy(List<MeetingRoom> meetingRoomList) {
        super(meetingRoomList);
    }

    @Override
    public MeetingRoom allotMeetingRoom(BookRequest bookRequest) {
        try{
            if(bookRequest.getPersonCapacity() <= 3)
                return allotMeetingRoom(meetingRoomList, bookRequest.getTimeInterval());
            throw new NoVacantRoomException();
        }catch (NoVacantRoomException e){
            return super.allotMeetingRoom(bookRequest);
        }
    }
}
