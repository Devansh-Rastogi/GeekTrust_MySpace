package com.example.geektrust.strategy;

import com.example.geektrust.model.BookRequest;
import com.example.geektrust.model.VacancyRequest;

public interface InputStrategy {

    void checkValidityOfBookInput(Object commandInfo);

    BookRequest createBookRequest(Object commandData);

    void checkValidityOfVacancyInput(Object commandData);

    VacancyRequest createVacancyRequest(Object commandData);
}
