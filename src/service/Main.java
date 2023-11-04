package service;

import model.ReaderFunction;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

 public static ArrayList<String> eventTimes = new ArrayList<>() ;
 public static ArrayList<String> event = new ArrayList<>() ;
    public static void main(String[] args) throws IOException {

        ReaderFunction rf= new ReaderFunction();
        rf.textReader(eventTimes,"src/time.txt");
        for (String eventTime : eventTimes) {
            Pattern pattern = Pattern.compile("(Event: .+)");
            Matcher matcher = pattern.matcher(eventTime);
            while(matcher.find()){
                event.add(matcher.group());
            }
        }
        for (String eventTime : eventTimes) {
            System.out.println(eventTime);
        }
        for (int i = 0; i < eventTimes.size(); i++) {
            Pattern pattern = Pattern.compile("(^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})");
            Matcher matcher = pattern.matcher(eventTimes.get(i));
            long seconds = 0;
            if(matcher.find()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                TemporalAccessor accessor = formatter.parse(matcher.group());
                LocalDateTime from = LocalDateTime.from(accessor);
                Duration between = Duration.between(LocalDateTime.now(),from);
                seconds= between.toSeconds();
                ScheduledExecutorService executor;
                executor = Executors.newScheduledThreadPool(4);
                int finalI = i;
                executor.scheduleAtFixedRate(()->{
                    System.out.println(accessor+"  "+event.get(finalI-1));
                },seconds,60*5, TimeUnit.SECONDS);
            }

        }
    }
}