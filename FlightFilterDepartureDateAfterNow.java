package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FlightFilterDepartureDateAfterNow extends FlightFilter {//исключить: вылет до текущего момента времени
    @Override
    protected boolean checkSegments (List<Segment> all_segments) {//переопределяем метод проверки сегментов
        if(all_segments.size()<1) {//если нет сегментов, то false
            return false;
        }
        return this.departureDateAfterNow(all_segments.get(0).getDepartureDate());//передаем дату вылета первого сегмента
    }
    private boolean departureDateAfterNow(LocalDateTime departureDate){//сравнение текущей даты+время и переданной(дата вылета)
        LocalDateTime dateNow = LocalDateTime.now();//текущая дата+время
        return Duration.between(dateNow, departureDate).toMinutes() > 0;//исключаем прошедшие вылеты и вылеты в текущую минуту
    }
}
