package com.example.geektrust.strategy;

import com.example.geektrust.exceptions.NoVacantRoomException;
import com.example.geektrust.lib.TimeIntervalLibrary;
import com.example.geektrust.model.BookRequest;
import com.example.geektrust.model.MeetingRoom;
import com.example.geektrust.model.TimeInterval;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public abstract class ChainOfResponsibilityStrategy implements AllotmentStrategy {
    protected final List<MeetingRoom> meetingRoomList;
    private TimeIntervalLibrary timeIntervalLibrary = new TimeIntervalLibrary();
    public ChainOfResponsibilityStrategy(List<MeetingRoom> meetingRoomList){
        this.meetingRoomList = meetingRoomList;
    }

    protected ChainOfResponsibilityStrategy nextChain;

    public void setNextChain(ChainOfResponsibilityStrategy chainOfResponsibilityStrategy) {
        this.nextChain = chainOfResponsibilityStrategy;
    }

    @Override
    public MeetingRoom allotMeetingRoom(BookRequest bookRequest){
        if(nextChain!=null)
            return nextChain.allotMeetingRoom(bookRequest);
        throw new NoVacantRoomException();
    }

    protected MeetingRoom allotMeetingRoom(List<MeetingRoom> meetingRoomList, TimeInterval timeInterval) {
        Optional<MeetingRoom> meetingRoomOptional = fetchMeetingRoom(meetingRoomList, timeInterval);
        if(meetingRoomOptional.isPresent()){
            meetingRoomOptional.get().getOccupiedIntervalList().add(timeInterval);
            return meetingRoomOptional.get();
        }
        throw new NoVacantRoomException();
    }

    protected Optional<MeetingRoom> fetchMeetingRoom(List<MeetingRoom> meetingRoomList, TimeInterval timeInterval) {
        return meetingRoomList.stream()
                .filter(meetingRoom -> !timeIntervalLibrary.isTimeOverlapped(meetingRoom.getBufferTimeInterval(), timeInterval) && !timeIntervalLibrary.isTimeOverlapped(meetingRoom.getOccupiedIntervalList(), timeInterval))
                .findFirst();
    }
}
