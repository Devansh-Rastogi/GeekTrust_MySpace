package com.example.geektrust.strategy;

import com.example.geektrust.model.MeetingRoom;
import com.example.geektrust.model.VacancyResponse;

public interface OutputStrategy {
    void showNoVacantRoom();

    void showInvalidInput();

    void showSuccessfulBookedResponse(MeetingRoom allotedMeetingRoom);

    void showVacantRoomList(VacancyResponse vacancyResponse);
}
