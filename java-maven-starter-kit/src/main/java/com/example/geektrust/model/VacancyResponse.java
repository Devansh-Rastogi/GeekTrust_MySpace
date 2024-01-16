package com.example.geektrust.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VacancyResponse {
    private List<String> meetingRoomName = new ArrayList<>();
}
