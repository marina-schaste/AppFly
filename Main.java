package com.gridnine.testing;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
    public static void main(String[] args) {
//        start();
        runTests();
    }
    private static void start() {
        FlightFilter defaultFilter = new FlightFilter();
        defaultFilter.filter();//default
        System.out.println("-------------------------");

        FlightFilterDepartureDateAfterNow departureDateAfterNowFilter = new FlightFilterDepartureDateAfterNow();
        departureDateAfterNowFilter.filter();//исключить: вылет до текущего момента времени
        System.out.println("-------------------------");

        FlightFilterDepartureDateBeforeArrivalDate departureDateBeforeArrivalDateFilter = new FlightFilterDepartureDateBeforeArrivalDate();
        departureDateBeforeArrivalDateFilter.filter();//исключить: имеются сегменты с датой прилёта раньше даты вылета
        System.out.println("-------------------------");

        FlightFilterArrivalDateBeforeNextDepartureDate arrivalDateBeforeNextDepartureDateFilter = new FlightFilterArrivalDateBeforeNextDepartureDate();
        arrivalDateBeforeNextDepartureDateFilter.filter();//исключить: общее время, проведённое на земле превышает два часа
    }
    private static void runTests() {//запуск тестов
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(FlightFilterDepartureDateAfterNowTest.class);
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }
}