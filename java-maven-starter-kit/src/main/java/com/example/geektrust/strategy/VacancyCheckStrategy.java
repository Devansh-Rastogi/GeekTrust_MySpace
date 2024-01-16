package com.example.geektrust.strategy;

import com.example.geektrust.model.MeetingRoom;
import com.example.geektrust.model.VacancyRequest;
import com.example.geektrust.model.VacancyResponse;

import java.util.List;

public interface VacancyCheckStrategy {
    VacancyResponse checkForVacancy(VacancyRequest vacancyRequest, List<MeetingRoom> meetingRoomList);
}