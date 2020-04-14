package com.beamm.flightbooking.functional;

import com.beamm.flightbooking.functional.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FunctionUtil {

    public static void main(String[] args) {

        // Airplanes
        Airplane airplane1 = new Airplane(1,"ET-DL4S400","777-200LR",7,25,150);
        Airplane airplane2 = new Airplane(1,"ET-NJK0344","A350-900",10,20,170);
        List<Airplane> airplanes = Arrays.asList(airplane1,airplane2);

        // Airports
        Airport airport1 = new Airport(1,"Bole International Airport","ADD","Addis Ababa");
        Airport airport2 = new Airport(1,"John F Kennedy International Airport","JFK","New York");
        Airport airport3 = new Airport(1,"Chicago O'Hare International Airport","ORD","Chicago");
        Airport airport4 = new Airport(1,"Beijing Capital International Airport","PEK","Beijing");
        List<Airport> airports = Arrays.asList(airport1,airport2,airport3,airport4);

        // Flights
        Flight flight1 = new Flight(1,"ET302",airport1, airport2, LocalTime.now(),LocalTime.now(),434.3,4434.0);
        Flight flight2 = new Flight(2,"ET555", airport2, airport1, LocalTime.now(), LocalTime.now(),434.3,2934.0);
        List<Flight> flights = Arrays.asList(flight1,flight2);

        // Scheduled Flights
        ScheduledFlight scheduledFlight1 = new ScheduledFlight(1, flight1, airplane1, 50,
                50.0, LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 10), new ArrayList<Passenger>());

        ScheduledFlight scheduledFlight2 = new ScheduledFlight(2, flight1, airplane1, 50,
                50.0, LocalDate.of(2020, 10, 13),
                LocalDate.of(2020, 10, 13), new ArrayList<Passenger>());


        ScheduledFlight scheduledFlight3 = new ScheduledFlight(3, flight1, airplane1, 50,
                50.0, LocalDate.of(2019, 10, 13),
                LocalDate.of(2020, 10, 13), new ArrayList<Passenger>());

        ScheduledFlight scheduledFlight4 = new ScheduledFlight(4, flight2, airplane2, 50,
                50.0, LocalDate.of(2020, 10, 15),
                LocalDate.of(2020, 10, 15), new ArrayList<Passenger>());

        List<ScheduledFlight> scheduledFlights = Arrays.asList(scheduledFlight1,scheduledFlight2,scheduledFlight3,scheduledFlight4);

        // Bookings
        Address address = new Address(1,"S12N 4th","Chicago","Illinois","USA","42423");//34,"a",,"c","d","12");
        LocalDate birthDay = LocalDate.of(1993, Month.JULY, 12);
        Person person1 = new Person(1, "John", "King", "Robert", birthDay, "johnrobert", "M", "+14914636363", "johnroberts@gmail.com",address);
        Person person2 = new Person(2, "Peter", "Madrig", "White", birthDay, "peterwhite", "M", "+17483743843", "peterwhite@gmail.com",address);
        Trip trip1 = new Trip(1,"50", Meal.VEGIE, "4FFCK",FlightClass.ECONOMY,scheduledFlight1);
        Trip trip2 = new Trip(2,"53", Meal.HALAL, "HJ434",FlightClass.BUSINESS,scheduledFlight2);
        Trip trip3 = new Trip(2,"53", Meal.VEGIE, "HJ434",FlightClass.BUSINESS,scheduledFlight2);
        Passenger passenger1 = new Passenger(1, "EP64734", person1, new ArrayList<Trip>() {{
            add(trip1);
        }});
        Passenger passenger2 = new Passenger(2, "EP03278", person2, new ArrayList<Trip>() {{
            add(trip3);
        }});
        Booking booking1 = new Booking(1,423.56,LocalDateTime.now(),"ERTRKH4378FKOF8","2",
                new ArrayList<Trip>() {{
                    add(trip1);
                }},
                new ArrayList<Passenger>() {{
                    add(passenger1);
                }}
        );
        Booking booking2 = new Booking(2,239.26,LocalDateTime.of(2020, 10, 13,2,10),"NI43HB4J3BJ3","2",
                new ArrayList<Trip>() {{
                    add(trip2);
                }},
                new ArrayList<Passenger>() {{
                    add(passenger1);
                }}
        );
        Booking booking3 = new Booking(2,239.26,LocalDateTime.of(2020, 10, 13,2,10),"NI43HB4J3BJ3","2",
                new ArrayList<Trip>() {{
                    add(trip3);
                }},
                new ArrayList<Passenger>() {{
                    add(passenger2);
                }}
        );
        List<Booking> bookings = Arrays.asList(booking1,booking2,booking3);

        Airline airline = new Airline(1,"Ethiopian Airlines",scheduledFlights,bookings);

        System.out.println(topKRoutes.apply(airline,2020,5));
        System.out.println(topMealForAllFlights.apply(airline,10));
        System.out.println(topKFrequentFlyers.apply(airline,2020,10));
        System.out.println(totalNumberOfMiles.apply(airline,2020));

    }

    public static TriFunction<Airline,Integer,Integer,List<String>> topKRoutes=(airline,year,topK) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(f -> f.getDepartureDate().getYear() == year)
                    .collect(Collectors.groupingBy(ScheduledFlight::getFlight,Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .map(f -> f.getKey().getFlightNumber())
                    .limit(topK)
                    .collect(Collectors.toList());

    public static Function<List<Trip>,String> getTopMealForAFlight = (trips) ->
            trips.stream()
                    .collect(Collectors.groupingBy(Trip::getMeal,Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder()))
                    .map(e -> e.getKey().toString())
                    .findFirst()
                    .get();

    public static BiFunction<Airline,Integer,Map<String,String>> topMealForAllFlights = (airline,month) ->
            Stream.of(airline)
                    .flatMap(a -> a.getBookings().stream())
                    .flatMap(b -> b.getTrips().stream())
                    .filter(t -> t.getScheduledFlight().getDepartureDate().getMonth().getValue() == month)
                    .collect(Collectors.groupingBy(t -> t.getScheduledFlight().getFlight()))
                    .entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey().getFlightNumber(),
                            entry -> getTopMealForAFlight.apply(entry.getValue())
                            ));

    public static TriFunction<Airline,Integer,Integer,List<String>> topKFrequentFlyers = (airline,year,topK) ->
            Stream.of(airline)
                    .flatMap(a -> a.getBookings().stream())
                    .filter(b -> b.getDateTimeOfBooking().getYear() == year)
                    .collect(Collectors.groupingBy(Booking::getPassengers,Collectors.counting()))
                    .entrySet().stream()
                    .flatMap(e -> e.getKey().stream()
                        .map(s -> new AbstractMap.SimpleImmutableEntry<Passenger,Long>(s,e.getValue())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .map(m -> m.getKey().getPerson().getFirstName() + " " + m.getKey().getPerson().getLastName())
                    .limit(topK)
                    .collect(Collectors.toList());

    public static BiFunction<Airline,Integer,Double> totalNumberOfMiles = (airline,year) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(s -> s.getDepartureDate().getYear() == year)
                    .map(s -> s.getFlight())
                    .mapToDouble(f -> f.getDistance())
                    .sum();

}
