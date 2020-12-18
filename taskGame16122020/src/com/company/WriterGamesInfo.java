package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WriterGamesInfo implements IWriterGamesInfo {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyy HH:mm:ss");
    private final Calendar calendarInstance = Calendar.getInstance();

    private String thePrefix;

    public WriterGamesInfo(String thePrefix) {
        this.thePrefix = thePrefix;
    }

    @Override
    public void info(String gameInfo) {
        System.out.printf("[%s] [%s] %s\n", simpleDateFormat.format(calendarInstance.getTime()), this.thePrefix, gameInfo);
    }
}
