package com.gridnine.testing;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightFilterDepartureDateAfterNowTest {
    private final FlightFilterDepartureDateAfterNow classAfterNow = new FlightFilterDepartureDateAfterNow();
    @Test
    public void checkSegmentsTest() {
        List<Segment> segmentsReturnTrue = this.createSegmentsTest1ReturnTrue();
        assertEquals(true,this.classAfterNow.checkSegments(segmentsReturnTrue));

        List<Segment> segmentsReturnFalse = this.createSegmentsTest2ReturnFalse();
        assertEquals(false,this.classAfterNow.checkSegments(segmentsReturnFalse));

        segmentsReturnFalse = this.createSegmentsTest3ReturnFalse();
        assertEquals(false,this.classAfterNow.checkSegments(segmentsReturnFalse));

        segmentsReturnFalse = this.createSegmentsTest4ReturnFalse();
        assertEquals(false,this.classAfterNow.checkSegments(segmentsReturnFalse));
    }
    private List<Segment> createSegmentsTest1ReturnTrue() {//дата отправления больше текущей даты - true
        //пришлось сделать createFlight public для доступа в тестах
        Flight flight = FlightBuilder.createFlight(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));
        return flight.getSegments();
    }
    private List<Segment> createSegmentsTest2ReturnFalse() {//дата отправления меньше текущей даты - false
        //пришлось сделать createFlight public для доступа в тестах
        Flight flight = FlightBuilder.createFlight(LocalDateTime.now().minusMinutes(1), LocalDateTime.now().plusHours(3));
        return flight.getSegments();
    }
    private List<Segment> createSegmentsTest3ReturnFalse() {//дата отправления равна текущей даты - false
        //пришлось сделать createFlight public для доступа в тестах
        Flight flight = FlightBuilder.createFlight(LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        return flight.getSegments();
    }
    private List<Segment> createSegmentsTest4ReturnFalse() {//нет сегментов - false
        //пришлось сделать createFlight public для доступа в тестах
        Flight flight = FlightBuilder.createFlight();
        return flight.getSegments();
    }
}
