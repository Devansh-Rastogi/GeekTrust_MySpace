package com.example.geektrust.strategy;

import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.exceptions.NoVacantRoomException;
import com.example.geektrust.model.BookRequest;
import com.example.geektrust.model.TimeInterval;
import com.example.geektrust.model.VacancyRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandInputStrategy implements InputStrategy {

    @Override
    public void checkValidityOfBookInput(Object command) {
        String[] commandInfo = (String[]) command;
        if(Integer.parseInt(commandInfo[3])<2 || Integer.parseInt(commandInfo[3])>20)
            throw new NoVacantRoomException();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            dateFormat.setLenient(false);
            Date startTime = dateFormat.parse(commandInfo[1]);
            Date endTime = dateFormat.parse(commandInfo[2]);
            if(!startTime.before(endTime))
                throw new InvalidInputException();
        }catch (Exception e){
            throw new InvalidInputException();
        }
        if(Integer.parseInt(commandInfo[1].split(":")[1]) % 15 != 0 || Integer.parseInt(commandInfo[2].split(":")[1]) % 15 != 0)
            throw new InvalidInputException();
    }

    @Override
    public BookRequest createBookRequest(Object commandData) {
        String[] commandInfo = (String[]) commandData;
        return new BookRequest(new TimeInterval(commandInfo[1], commandInfo[2]), Integer.parseInt(commandInfo[3]));
    }

    @Override
    public void checkValidityOfVacancyInput(Object commandData) {
        String[] commandInfo = (String[]) commandData;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            dateFormat.setLenient(false);
            Date startTime = dateFormat.parse(commandInfo[1]);
            Date endTime = dateFormat.parse(commandInfo[2]);
            if(!startTime.before(endTime))
                throw new InvalidInputException();
        }catch (Exception e){
            throw new InvalidInputException();
        }
    }

    @Override
    public VacancyRequest createVacancyRequest(Object commandData) {
        String[] commandInfo = (String[]) commandData;
        return new VacancyRequest(new TimeInterval(commandInfo[1], commandInfo[2]));
    }
}
