package com.example.geektrust.service;

import com.example.geektrust.enums.InputEnum;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.exceptions.NoVacantRoomException;
import com.example.geektrust.model.MeetingRoom;
import com.example.geektrust.model.VacancyResponse;
import com.example.geektrust.strategy.AllotmentStrategy;
import com.example.geektrust.strategy.InputStrategy;
import com.example.geektrust.strategy.OutputStrategy;
import com.example.geektrust.strategy.VacancyCheckStrategy;
import lombok.NonNull;

import java.util.List;

public class MeetingSchedulerService {

    private final List<MeetingRoom> meetingRoomList;
    private final InputStrategy inputStrategy;
    private final OutputStrategy outputStrategy;
    private final AllotmentStrategy allotmentStrategy;
    private final VacancyCheckStrategy vacancyCheckStrategy;

    public MeetingSchedulerService(@NonNull final List<MeetingRoom> meetingRoomList, @NonNull final InputStrategy inputStrategy, @NonNull final OutputStrategy outputStrategy, @NonNull final AllotmentStrategy schedulerStrategy, @NonNull final VacancyCheckStrategy vacancyCheckStrategy) {
        this.meetingRoomList = meetingRoomList;
        this.inputStrategy = inputStrategy;
        this.outputStrategy = outputStrategy;
        this.allotmentStrategy = schedulerStrategy;
        this.vacancyCheckStrategy = vacancyCheckStrategy;
    }

    public void processCommand(String s) {
        String[] commandData = s.split(" +|\\t");
        switch (Enum.valueOf(InputEnum.class, commandData[0])){
            case BOOK:
                bookTheMeetingRoom(commandData);
                break;
            case VACANCY:
                showTheVacancyOfMeetingRoom(commandData);
                break;
            default:
                outputStrategy.showInvalidInput();
        }
    }

    private void showTheVacancyOfMeetingRoom(String[] commandData) {
        try{
            inputStrategy.checkValidityOfVacancyInput(commandData);
            VacancyResponse vacancyResponse = vacancyCheckStrategy.checkForVacancy(inputStrategy.createVacancyRequest(commandData), meetingRoomList);
            outputStrategy.showVacantRoomList(vacancyResponse);
        }catch (NoVacantRoomException noVacantRoomException){
            outputStrategy.showNoVacantRoom();
        }catch (InvalidInputException invalidInputException){
            outputStrategy.showInvalidInput();
        }
    }

    private void bookTheMeetingRoom(String[] commandData) {
        try{
            inputStrategy.checkValidityOfBookInput(commandData);
            MeetingRoom allotedMeetingRoom = allotmentStrategy.allotMeetingRoom(inputStrategy.createBookRequest(commandData));
            outputStrategy.showSuccessfulBookedResponse(allotedMeetingRoom);
        }catch (NoVacantRoomException noVacantRoomException){
            outputStrategy.showNoVacantRoom();
        }catch (InvalidInputException invalidInputException){
            outputStrategy.showInvalidInput();
        }
    }
}