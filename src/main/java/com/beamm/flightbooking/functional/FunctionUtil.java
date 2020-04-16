package com.beamm.flightbooking.functional;

import com.beamm.flightbooking.functional.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;


public class FunctionUtil {

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

//    Flight Times - Displays all flight departure and arrival times for a date range departing from a specific city
    public static QuadFunction<Airline, LocalDate, LocalDate, Airport, List<String>>
                                flightTimesFromAGivenCityForARangeOfDays = (airline, startDate, endDate, airport) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(s -> s.getDepartureDate().isAfter(startDate.minusDays(1)) &&
                            s.getDepartureDate().isBefore(endDate.plusDays(1)))
                    .sorted(Comparator.comparing(s -> s.getDepartureDate()))
                    .map(s -> s.getFlight().getFlightNumber() + ": " + s.getFlight().getOrigin().getAirportCity()
                            + "->" + s.getFlight().getDestination().getAirportCity() + ", " + LocalDateTime.of(s.getDepartureDate(),
                            s.getFlight().getDepartureTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                            + ", " + LocalDateTime.of(s.getArrivalDate(), s.getFlight().getArrivalTime()).format(
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .collect(Collectors.toList());

    public static BiFunction<Airline, LocalDateTime, List<String>> ongoingFlightsAtAGivenTime = (airline, time) ->
            Stream.of(airline)
                    .flatMap(a -> a.getScheduledFlights().stream())
                    .filter(s -> time.isAfter(LocalDateTime.of(s.getDepartureDate(), s.getFlight().getDepartureTime())) &&
                            time.isBefore(LocalDateTime.of(s.getArrivalDate(), s.getFlight().getArrivalTime())))
                    .sorted(Comparator.comparing(s -> s.getFlight().getDepartureTime(),Comparator.reverseOrder()))
                    .map(s -> s.getFlight().getFlightNumber() + ", " + s.getFlight().getOrigin().getAirportCity()
                            + " - " + s.getFlight().getDestination().getAirportCity())
                    .collect(Collectors.toList());

}