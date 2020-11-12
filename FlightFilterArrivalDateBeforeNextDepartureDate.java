package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FlightFilterArrivalDateBeforeNextDepartureDate extends FlightFilter {//исключить: общее время, проведённое на земле превышает два часа
    @Override
    protected boolean checkSegments (List<Segment> all_segments) {//переопределяем метод проверки сегментов
        if(all_segments.size()<1) {//если нет сегментов, то false
            return false;
        }
        Duration timeOnEarth = Duration.ZERO;//счетчик времени "на земле"(между сегментами)
        for (int m = 0; m < all_segments.size(); m++) {
            if (m < (all_segments.size() - 1)) {//если не последний сегмент
                timeOnEarth = timeOnEarth.plus(this.arrivalDateBeforeNextDepartureDate(all_segments.get(m).getArrivalDate(), all_segments.get(m + 1).getDepartureDate()));//добавляем к счетчику время между датой прилета текущего сегмента, датой вылета следующего сегмента
            }
        }
        return timeOnEarth.toHours()<2;//если общее время между полетами меньше 2-х часов, то true, иначе false
    }
    private Duration arrivalDateBeforeNextDepartureDate(LocalDateTime arrivalDate, LocalDateTime nextDepartureDate){//сравнение переданных дат(дата прилета текущего сегмента, дата вылета следующего сегмента)
        return Duration.between(arrivalDate, nextDepartureDate);
    }
}
