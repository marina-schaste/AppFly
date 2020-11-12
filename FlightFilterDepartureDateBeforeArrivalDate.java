package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FlightFilterDepartureDateBeforeArrivalDate extends FlightFilter {//исключить: имеются сегменты с датой прилёта раньше даты вылета
    @Override
    protected boolean checkSegments (List<Segment> all_segments) {//переопределяем метод проверки сегментов
        if(all_segments.size()<1) {//если нет сегментов, то false
            return false;
        }
        boolean check = true;
        for (int m = 0; m < all_segments.size(); m++) {
            check = this.departureDateBeforeArrivalDate(all_segments.get(m).getDepartureDate(),all_segments.get(m).getArrivalDate());//передаем дату вылета, дату прилета текущего сегмента
        }
        return check;
    }
    private boolean departureDateBeforeArrivalDate(LocalDateTime departureDate, LocalDateTime arrivalDate){//сравнение переданных дат(дата вылета, дата прилета)
        return Duration.between(departureDate, arrivalDate).toMinutes() > 0;//исключаем полеты в прошлое(дата вылета позже даты прилета) и телепортацию менее чем за 1 минуту
    }
}
