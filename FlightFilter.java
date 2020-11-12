package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

class FlightFilter {//Основной класс для фильтрации полетов
    List<Flight> flights;

    FlightFilter() {//Инициализация полетов по умолчанию
        FlightBuilder fb = new FlightBuilder();
        changeFlights(fb.createFlights());
    }

    public void filter () {// 1) фильтрация полетов, 2) перезаписывается поле flights 3) вывод в консоль
        changeFlights(this.parseFlights());
        System.out.println(this.flights);
    }

    public final void changeFlights (List<Flight> flights) {//Изменение списка полетов
        this.flights = flights;
    }
    protected List<Flight> parseFlights(){//Фильтрация списка полетов, возвращает новый список. По умолчанию фильтрация по сегментам методом checkSegments
        List<Flight> flights = new ArrayList<>();
        for (int k = 0; k < this.flights.size(); k++) {
            Flight flight = this.flights.get(k);
            if (this.checkSegments(flight.getSegments())) {//если проверка сегментов пройдена, то добавляем полет
                flights.add(flight);
            }
        }
        return flights;
    }
    protected boolean checkSegments (List<Segment> all_segments) {//Проверка сегментов полета. По умолчанию без условия
        return true;
    }
}
