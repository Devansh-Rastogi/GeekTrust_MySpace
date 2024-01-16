package com.example.geektrust.strategy;

import com.example.geektrust.exceptions.NoVacantRoomException;
import com.example.geektrust.model.BookRequest;
import com.example.geektrust.model.MeetingRoom;

import java.util.List;

public class D_TowerMeetingRoomAllotmentStrategy extends ChainOfResponsibilityStrategy{
    public D_TowerMeetingRoomAllotmentStrategy(List<MeetingRoom> meetingRoomList) {
        super(meetingRoomList);
    }

    @Override
    public MeetingRoom allotMeetingRoom(BookRequest bookRequest) {
        try{
            if(bookRequest.getPersonCapacity() <= 7)
                return allotMeetingRoom(meetingRoomList, bookRequest.getTimeInterval());
            throw new NoVacantRoomException();
        }catch (NoVacantRoomException e){
            return super.allotMeetingRoom(bookRequest);
        }
    }
}
