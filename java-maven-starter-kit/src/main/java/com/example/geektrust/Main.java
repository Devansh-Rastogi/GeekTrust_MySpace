package com.example.geektrust;

import com.example.geektrust.model.*;
import com.example.geektrust.service.MeetingSchedulerService;
import com.example.geektrust.strategy.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MeetingRoom> meetingRoomList = getMeetingRoomList();
        MeetingSchedulerService meetingSchedulerService = new MeetingSchedulerService(meetingRoomList, new CommandInputStrategy(), new CommandLineOutputStrategy(), createAllotmentStrategy(meetingRoomList), new VacancyCheckStrategyImpl());
//        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                meetingSchedulerService.processCommand(sc.nextLine());
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
    }

    private static AllotmentStrategy createAllotmentStrategy(List<MeetingRoom> meetingRoomList) {
        C_CaveMeetingRoomAllotmentStrategy c_caveStrategy = new C_CaveMeetingRoomAllotmentStrategy(Arrays.asList(meetingRoomList.get(0)));
        D_TowerMeetingRoomAllotmentStrategy d_towerStrategy = new D_TowerMeetingRoomAllotmentStrategy(Arrays.asList(meetingRoomList.get(1)));
        c_caveStrategy.setNextChain(d_towerStrategy);
        d_towerStrategy.setNextChain(new G_MansionMeetingRoomAllotmentStrategy(Arrays.asList(meetingRoomList.get(2))));
        return c_caveStrategy;
    }

    private static List<MeetingRoom> getMeetingRoomList() {
        List<MeetingRoom> meetingRoomList = new ArrayList<>();
        List<TimeInterval> bufferInterval = createSampleBufferTimeInterval();
        meetingRoomList.add(new C_CaveMeetingRoom("C-Cave", bufferInterval));
        meetingRoomList.add(new D_TowerMeetingRoom("D-Tower", bufferInterval));
        meetingRoomList.add(new G_MansionMeetingRoom("G-Mansion", bufferInterval));
        return meetingRoomList;
    }

    private static List<TimeInterval> createSampleBufferTimeInterval() {
        return new ArrayList<TimeInterval>(){{
            add(new TimeInterval("09:00", "09:15"));
            add(new TimeInterval("13:15", "13:45"));
            add(new TimeInterval("18:45", "19:00"));
        }};
    }
}
