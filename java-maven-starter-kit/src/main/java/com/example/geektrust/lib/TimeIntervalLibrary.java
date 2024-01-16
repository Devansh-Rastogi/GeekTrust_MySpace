package com.example.geektrust.lib;

import com.example.geektrust.model.MeetingRoom;
import com.example.geektrust.model.TimeInterval;

import java.util.List;

public class TimeIntervalLibrary {
    public boolean checkIfMeetingRoomAvailableForTimeSlot(MeetingRoom meetingRoom, TimeInterval timeInterval) {
        return !isTimeOverlapped(meetingRoom.getBufferTimeInterval(), timeInterval) && !isTimeOverlapped(meetingRoom.getOccupiedIntervalList(), timeInterval);
    }

    public boolean isTimeOverlapped(List<TimeInterval> occupiedTimeIntervalList, TimeInterval timeInterval) {
        int suggestedStartTime = Integer.parseInt(timeInterval.getStartTime().replace(":", ""));
        int suggestedEndTime = Integer.parseInt(timeInterval.getEndTime().replace(":", ""));
        occupiedTimeIntervalList.sort((o1, o2) -> Integer.parseInt(o1.getStartTime().replace(":", "")) - Integer.parseInt(o2.getEndTime().replace(":", "")));
        for (TimeInterval interval : occupiedTimeIntervalList) {
            int startTime = Integer.parseInt(interval.getStartTime().replace(":", ""));
            int endtime = Integer.parseInt(interval.getEndTime().replace(":", ""));
            if((suggestedStartTime >= startTime && suggestedStartTime < endtime)
                    || (suggestedEndTime > startTime && suggestedEndTime <= endtime )
                    || (suggestedStartTime <= startTime && suggestedEndTime >= endtime))
                return true;
        }
        return false;
    }
}
