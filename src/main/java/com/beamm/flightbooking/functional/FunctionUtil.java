package com.beamm.flightbooking.functional;

import com.beamm.flightbooking.functional.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;


public class FunctionUtil {

    public enum ReportType {
        DAILY,
        WEEKLY,
        MONTHLY
    }
  
    public static void main(String[] args) {

        // Airplanes
        Airplane airplane1 = new Airplane(1, "ET-DL4S400", "777-200LR", 7, 25, 150);
        Airplane airplane2 = new Airplane(1, "ET-NJK0344", "A350-900", 10, 20, 170);
        Airplane airplane3 = new Airplane(1, "ET-ANK0344", "A380-900", 15, 25, 260);
        List<Airplane> airplanes = Arrays.asList(airplane1, airplane2, airplane3);

        // Airports
        Airport airport1 = new Airport(1, "Bole International Airport", "ADD", "Addis Ababa");
        Airport airport2 = new Airport(1, "John F Kennedy International Airport", "JFK", "New York");
        Airport airport3 = new Airport(1, "Chicago O'Hare International Airport", "ORD", "Chicago");
        Airport airport4 = new Airport(1, "Beijing Capital International Airport", "PEK", "Beijing");
        List<Airport> airports = Arrays.asList(airport1, airport2, airport3, airport4);

        // Flights
        Flight flight1 = new Flight(1, "ET302", airport1, airport2, LocalTime.now(), LocalTime.now(), 434.3, 4434.0);
        Flight flight2 = new Flight(2, "ET555", airport2, airport1, LocalTime.now(), LocalTime.now(), 434.3, 2934.0);
        Flight flight3 = new Flight(2, "ET345", airport3, airport1, LocalTime.now(), LocalTime.now(), 494.7, 3489.0);
        Flight flight4 = new Flight(2, "ET777", airport2, airport1, LocalTime.now(), LocalTime.now(), 434.3, 2934.0);

        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Scheduled Flights
        ScheduledFlight scheduledFlight1 = new ScheduledFlight(1, flight1, airplane1, 120,
                50.0, LocalDate.of(2020, 10, 10),
                LocalDate.of(2020, 10, 10), new ArrayList<Passenger>());

        ScheduledFlight scheduledFlight2 = new ScheduledFlight(2, flight2, airplane1, 110,
                590.0, LocalDate.of(2020, 11, 13),
                LocalDate.of(2020, 11, 13), new ArrayList<Passenger>());


        ScheduledFlight scheduledFlight3 = new ScheduledFlight(3, flight1, airplane1, 150,
                570.0, LocalDate.of(2020, 10, 13),
                LocalDate.of(2020, 10, 13), new ArrayList<Passenger>());

        ScheduledFlight scheduledFlight4 = new ScheduledFlight(4, flight3, airplane2, 80,
                850.0, LocalDate.of(2020, 10, 15),
                LocalDate.of(2020, 10, 15), new ArrayList<Passenger>());

        ScheduledFlight scheduledFlight5 = new ScheduledFlight(5, flight3, airplane3, 90,
                720.0, LocalDate.of(2020, 10, 15),
                LocalDate.of(2020, 9, 5), new ArrayList<Passenger>());

        ScheduledFlight scheduledFlight6 = new ScheduledFlight(6, flight1, airplane2, 110,
                730.0, LocalDate.of(2020, 11, 15),
                LocalDate.of(2020, 9, 5), new ArrayList<Passenger>());

        // Bookings
        Address address = new Address(1, "S12N 4th", "Chicago", "Illinois", "USA", "42423");//34,"a",,"c","d","12");
        LocalDate birthDay = LocalDate.of(1993, Month.JULY, 12);
        Person person1 = new Person(1, "John", "King", "Robert", birthDay, "johnrobert", "M", "+14914636363", "johnroberts@gmail.com", address);
        Person person2 = new Person(2, "Peter", "Madrig", "White", birthDay, "peterwhite", "M", "+17483743843", "peterwhite@gmail.com", address);
        Trip trip1 = new Trip(1, "50", Meal.VEGIE, "4FFCK", FlightClass.ECONOMY, scheduledFlight1, 1000.00);
        Trip trip2 = new Trip(2, "53", Meal.HALAL, "HJ434", FlightClass.BUSINESS, scheduledFlight2, 980.00);
        Trip trip3 = new Trip(2, "55", Meal.VEGIE, "HJ434", FlightClass.BUSINESS, scheduledFlight2, 1080.00);
        Trip trip4 = new Trip(4, "55", Meal.VEGIE, "HX434", FlightClass.BUSINESS, scheduledFlight2, 1080.00);

        Passenger passenger1 = new Passenger(1, "EP64734", person1, new ArrayList<Trip>() {{
            add(trip1);
            add(trip2);
        }});
        Passenger passenger2 = new Passenger(2, "EP03278", person2, new ArrayList<Trip>() {{
            add(trip3);
        }});
        Booking booking1 = new Booking(1, 423.56, LocalDateTime.now(), "ERTRKH4378FKOF8", "2",
                new ArrayList<Passenger>() {{
                    add(passenger1);
                }}
        );
        Booking booking2 = new Booking(2, 239.26, LocalDateTime.of(2020, 10, 13, 2, 10), "NI43HB4J3BJ3", "2",
                new ArrayList<Passenger>() {{
                    add(passenger1);
                }}
        );
        Booking booking3 = new Booking(2, 239.26, LocalDateTime.of(2020, 10, 13, 2, 10), "NI43HB4J3BJ3", "2",
                new ArrayList<Passenger>() {{
                    add(passenger2);
                }}
        );

        List<Booking> bookings = Arrays.asList(booking1, booking2, booking3);

        List<ScheduledFlight> scheduledFlights = Arrays.asList(scheduledFlight1, scheduledFlight2, scheduledFlight3, scheduledFlight4, scheduledFlight5, scheduledFlight6);

        Airline airline = new Airline(1, "Ethiopian Airlines", scheduledFlights, bookings);

        System.out.println(topKRoutes.apply(airline, 2020, 5));
        System.out.println(topMealForAllFlights.apply(airline, 10));
        System.out.println(topKFrequentFlyers.apply(airline, 2020, 1));
        System.out.println(totalNumberOfMiles.apply(airline, 2020));
        System.out.println(bookingsPerMonth.apply(airline, 2020));
        System.out.println(topKRevenuePerRoute.apply(airline, 2020, 5));
        System.out.println(topMealForAGivenYearForAllFlightsOnMonthlyBasis.apply(airline, 2020));
        System.out.println(crowdedFlightPerYear.apply(airline, 2020));
        System.out.println(leastExpendingPassengers.apply(airline, 2020, 5));
        System.out.println(multiLegFlightPassengers.apply(airline, 2020));
        System.out.println(mostUsedAirpotsForAGivenYear.apply(airline, 2020, 4));
        System.out.println(topKFlightsToRemove.apply(airline, 12, 50, 10));
        System.out.println(numberOfPassengersOnDailyBasis.apply(airline, 2020));

    }

    public static TriFunction<Airline, Integer, Integer, List<String>> topKRoutes = (airline, year, topK) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(f -> f.getDepartureDate().getYear() == year)
                    .collect(Collectors.groupingBy(ScheduledFlight::getFlight, Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .map(f -> f.getKey().getFlightNumber())
                    .limit(topK)
                    .collect(Collectors.toList());

    public static Function<List<Trip>, String> getTopMealForAFlight = (trips) ->
            trips.stream()
                    .collect(Collectors.groupingBy(Trip::getMeal, Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .map(e -> e.getKey().toString())
                    .findFirst()
                    .get();

    public static BiFunction<Airline, Integer, Map<String, String>> topMealForAllFlights = (airline, month) ->
            Stream.of(airline)
                    .flatMap(a -> a.getBookings().stream())
                    .flatMap(b -> b.getTrips().stream())
                    .filter(t -> t.getScheduledFlight().getDepartureDate().getMonth().getValue() == month)
                    .collect(Collectors.groupingBy(t -> t.getScheduledFlight().getFlight().getFlightNumber()))
                    .entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey(),//.getFlightNumber()
                            entry -> getTopMealForAFlight.apply(entry.getValue())
                    ));

    public static Function<List<Trip>, String> getTopMealForAGivenFlightForAGivenMonth = (trips) ->
            trips.stream()
                    .collect(Collectors.groupingBy(Trip::getMeal, Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .map(e -> e.getKey().toString())
                    .findFirst()
                    .get();

    public static Function<List<Trip>, Map<String, String>> topMealForAMonthForAllFlights = (trips) ->
            trips.stream()
                    .collect(Collectors.groupingBy(t -> t.getScheduledFlight().getFlight().getFlightNumber()))
                    .entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey(),
                            entry -> getTopMealForAGivenFlightForAGivenMonth.apply(entry.getValue())
                    ));

    public static BiFunction<Airline, Integer, Map<String, Map<String, String>>> topMealForAGivenYearForAllFlightsOnMonthlyBasis = (airline, year) ->
            Stream.of(airline)
                    .flatMap(a -> a.getBookings().stream())
                    .flatMap(b -> b.getTrips().stream())
                    .filter(t -> t.getScheduledFlight().getDepartureDate().getYear() == year)
                    .collect(Collectors.groupingBy(t -> t.getScheduledFlight().getDepartureDate().getMonth().getDisplayName(TextStyle.FULL,
                            Locale.ENGLISH)))
                    .entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey(),
                            entry -> topMealForAMonthForAllFlights.apply(entry.getValue())
                    ));


    public static TriFunction<Airline, Integer, Integer, List<String>> topKFrequentFlyers = (airline, year, topK) ->
            Stream.of(airline)
                    .flatMap(a -> a.getBookings().stream())
                    .flatMap(b -> b.getPassengers().stream())
                    .collect(Collectors.groupingBy(p -> p.getPerson()))
                    .entrySet().stream()
//                    .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().stream().flatMap(p -> p.getTrips().stream()).count()))
                    .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue().stream().count()))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(entry -> entry.getValue(), Comparator.reverseOrder()))
                    .limit(topK)
                    .map(entry -> entry.getKey().getFirstName() + " " + entry.getKey().getMiddleName() + " " + entry.getKey().getLastName())
                    .collect(Collectors.toList());


    public static BiFunction<Airline, Integer, Double> totalNumberOfMiles = (airline, year) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(s -> s.getDepartureDate().getYear() == year)
                    .map(s -> s.getFlight())
                    .mapToDouble(f -> f.getDistance())
                    .sum();

    public static BiFunction<Airline, Integer, Map<String, Long>> bookingsPerMonth = (airline, year) ->
            Stream.of(airline)
                    .flatMap(a -> a.getBookings().stream())
                    .filter(b -> b.getDateTimeOfBooking().getYear() == year)
                    .collect(Collectors.groupingBy(b -> b.getDateTimeOfBooking().getMonth().getDisplayName(TextStyle.FULL,
                            Locale.ENGLISH), Collectors.counting()));

    public static Function<List<Trip>, Double> toptalRevenuePerFlight = trips ->
            trips.stream()
                    .flatMapToDouble(t -> DoubleStream.of(t.getPrice()))
                    .sum();

    public static TriFunction<Airline, Integer, Integer, List<String>> topKRevenuePerRoute = ((airline, year, topk) -> Stream.of(airline)
            .flatMap(a -> a.getBookings().stream())
            .filter(b -> b.getDateTimeOfBooking().getYear() == year)
            .flatMap(b -> b.getTrips().stream())
            .collect(Collectors.groupingBy(t -> t.getScheduledFlight().getFlight().getFlightNumber()))
            .entrySet().stream()
            .collect(Collectors.toMap(entry -> entry.getKey(), entry -> toptalRevenuePerFlight.apply(entry.getValue())))
            .entrySet().stream()
            .sorted(Comparator.comparing(ent -> ent.getValue(), Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .limit(topk)
            .collect(Collectors.toList()));


    public static Function<List<ScheduledFlight>, Double> getAverageOccupancy = (scheduledFlights) ->
            scheduledFlights.stream()
                    .mapToDouble(s -> s.getPassengers().stream().count() / (double) s.getCapacity())
                    .average()
                    .getAsDouble();

    public static BiFunction<Airline, Integer, String> crowdedFlightPerYear = (airline, year) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(s -> s.getDepartureDate().getYear() == year)
                    .collect(Collectors.groupingBy(s -> s.getFlight()))
                    .entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey().getFlightNumber(),
                            entry -> getAverageOccupancy.apply(entry.getValue())
                    ))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(m -> m.getKey(), Comparator.reverseOrder()))
                    .map(m -> m.getKey())
                    .findFirst()
                    .get();

    public static Function<List<Trip>, Double> sumPassengersYearlyExpenditure = trips ->
            trips.stream().flatMapToDouble(t -> DoubleStream.of(t.getPrice() - t.getScheduledFlight().getCurrentPrice())).sum();


    public static TriFunction<Airline, Integer, Integer, List<String>> leastExpendingPassengers = ((airline, year, topk) -> Stream.of(airline)
            .flatMap(a -> a.getBookings().stream())
            .filter(b -> b.getDateTimeOfBooking().getYear() == year)
            .flatMap(b -> b.getTrips().stream())
            .collect(Collectors.groupingBy(t -> t.getScheduledFlight().getFlight()))
            .entrySet().stream()
            .collect(Collectors.toMap(
                    entry -> entry.getKey().getFlightNumber(),
                    entry -> sumPassengersYearlyExpenditure.apply(entry.getValue())))
            .entrySet().stream()
            .sorted(Comparator.comparing(e -> e.getValue()))
            .map(Map.Entry::getKey)
            .limit(topk)
            .collect(Collectors.toList()));

    public static BiFunction<Airline, Integer, Long> multiLegFlightPassengers = (airline, year) ->
            Stream.of(airline)
                    .flatMap(a -> a.getBookings().stream())
                    .filter(b -> b.getDateTimeOfBooking().getYear() == year)
                    .filter(b -> b.getTrips().stream().count() >= 2)
                    .flatMap(b -> b.getPassengers().stream())
                    .map(p -> p.getPerson())
                    .distinct()
                    .count();


    public static TriFunction<Airline, Integer, Integer, List<String>> mostUsedAirpotsForAGivenYear = (airline, year, topK) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(s -> s.getDepartureDate().getYear() == year)
                    .flatMap(s -> Stream.of(s.getFlight().getOrigin(), s.getFlight().getDestination()))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                    .map(e -> e.getKey().getAirportName())
                    .limit(topK)
                    .collect(Collectors.toList());

    public static Function<List<ScheduledFlight>, Double> calculatePercentageOfVacantSeats = scheduledFlights ->
            scheduledFlights.stream()
                    .mapToDouble(s -> 100.0 * s.getPassengers().size() / s.getCapacity())
                    .sum();

    public static QuadFunction<Airline, Integer, Integer, Integer, List<String>> topKFlightsToRemove = (airline, observationMonths, occupancyLimitPercentage, topK) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(s -> s.getDepartureDate().isAfter(LocalDate.now().minusMonths(observationMonths)))
                    .collect(Collectors.groupingBy(s -> s.getFlight().getFlightNumber()))
                    .entrySet().stream()
                    .collect(Collectors.toMap(entry -> entry.getKey(),
                            entry -> calculatePercentageOfVacantSeats.apply(entry.getValue())))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(entry -> entry.getValue(), Comparator.reverseOrder()))
                    .limit(topK)
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList());

    public static BiFunction<Airline, Integer, Map<LocalDate, Long>> numberOfPassengersOnDailyBasis = (airline, year) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(s -> s.getDepartureDate().getYear() == year)
                    .collect(Collectors.groupingBy(s -> s.getDepartureDate()))
                    .entrySet().stream()
                    .collect(Collectors.toMap(entry -> entry.getKey(),
                            entry -> entry.getValue().stream().count()));

}


