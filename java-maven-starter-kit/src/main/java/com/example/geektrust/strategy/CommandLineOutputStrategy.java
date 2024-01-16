package com.example.geektrust.strategy;

import com.example.geektrust.model.MeetingRoom;
import com.example.geektrust.model.VacancyResponse;

public class CommandLineOutputStrategy implements OutputStrategy{
    @Override
    public void showNoVacantRoom() {
        System.out.println("NO_VACANT_ROOM");
    }

    @Override
    public void showInvalidInput() {
        System.out.println("INCORRECT_INPUT");
    }

    @Override
    public void showSuccessfulBookedResponse(MeetingRoom allotedMeetingRoom) {
        System.out.println(allotedMeetingRoom.getName());
    }

    @Override
    public void showVacantRoomList(VacancyResponse vacancyResponse) {
        System.out.println(String.join(" ", vacancyResponse.getMeetingRoomName()));
    }
}
