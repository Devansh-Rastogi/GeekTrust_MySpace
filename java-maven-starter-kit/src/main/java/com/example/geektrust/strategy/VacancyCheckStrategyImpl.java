package com.example.geektrust.strategy;

import com.example.geektrust.exceptions.NoVacantRoomException;
import com.example.geektrust.lib.TimeIntervalLibrary;
import com.example.geektrust.model.MeetingRoom;
import com.example.geektrust.model.VacancyRequest;
import com.example.geektrust.model.VacancyResponse;

import java.util.List;

public class VacancyCheckStrategyImpl implements VacancyCheckStrategy{
    private TimeIntervalLibrary timeIntervalLibrary = new TimeIntervalLibrary();
    @Override
    public VacancyResponse checkForVacancy(VacancyRequest vacancyRequest, List<MeetingRoom> meetingRoomList) {
        VacancyResponse vacancyResponse = new VacancyResponse();
        for (MeetingRoom meetingRoom : meetingRoomList) {
            if(timeIntervalLibrary.checkIfMeetingRoomAvailableForTimeSlot(meetingRoom, vacancyRequest.getTimeInterval()))
                vacancyResponse.getMeetingRoomName().add(meetingRoom.getName());
        }
        if(vacancyResponse.getMeetingRoomName().isEmpty())
            throw new NoVacantRoomException();
        return vacancyResponse;
    }
}
