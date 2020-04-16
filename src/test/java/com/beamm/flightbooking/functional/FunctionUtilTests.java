package com.beamm.flightbooking.functional;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.beamm.flightbooking.functional.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;


class FunctionUtilTests {
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

    @Test
    public void topKRoutes(){
        List<String> top5Routes = Arrays.asList("ET302", "ET345", "ET555");
        assertEquals(top5Routes,FunctionUtil.topKRoutes.apply(airline, 2020, 5));
    }

    @Test
    public void topMealForAllFlights(){
        Map<String,String> topMeal = new HashMap<String , String>() {{
            put("ET302","VEGIE");
        }};
        assertEquals(topMeal, FunctionUtil.topMealForAllFlights.apply(airline,10));

    }
    @Test
    public void bookingsPerMonth(){
        Map<String,Long> bookingPerMonth = new HashMap<String,Long>() {{
            put("October", 2L);
            put("April", 1L);
        }};
        assertEquals(bookingPerMonth, FunctionUtil.bookingsPerMonth.apply(airline,2020));
    }

    @Test
    public void topKFrequentFlyers(){
        List<String> topFrequentFlyers = Arrays.asList("John King Robert", "Peter Madrig White");
        assertEquals(topFrequentFlyers, FunctionUtil.topKFrequentFlyers.apply(airline,2020,10));
    }

    @Test
    public void totalNumberOfMiles(){
        double totalMiles = 23214.0;
        assertEquals(totalMiles, FunctionUtil.totalNumberOfMiles.apply(airline,2020));
    }

    @Test
    public void crowdedFlightPerYear(){
        String topCrowdedExpected = "ET555";
        String topCrowdedFound = FunctionUtil.crowdedFlightPerYear.apply(airline,2020);
        assertEquals(topCrowdedExpected, topCrowdedFound);
    }

    @Test
    public void topMealForAGivenYearForAllFlightsOnMonthlyBasis(){
        Map<String,Map<String,String>> topMeal = new HashMap<String , Map<String,String>>() {{
            put("October",new HashMap<String , String>() {{
                put("ET302","VEGIE");
            }});

            put("November",new HashMap<String , String>() {{
                put("ET555","HALAL");
            }});
        }};
        assertEquals(topMeal, FunctionUtil.topMealForAGivenYearForAllFlightsOnMonthlyBasis.apply(airline, 2020));

    }

    @Test
    public void topKRevenuePerRoute(){
        List<String> topKRevenuePerRout = Arrays.asList("ET555", "ET302");
        assertEquals(topKRevenuePerRout,FunctionUtil.topKRevenuePerRoute.apply(airline, 2020, 5));
    }

    @Test
    public void leastExpendingPassengers(){
        List<String> leastExpending = Arrays.asList("ET555", "ET302");
        assertEquals(leastExpending, FunctionUtil.leastExpendingPassengers.apply(airline, 2020, 5));

    }

    @Test
    public void multiLegFlightPassengers(){
        long multilegPassengers = 1;
        assertEquals(multilegPassengers, FunctionUtil.multiLegFlightPassengers.apply(airline,2020));
    }

    @Test
    public void mostUsedAirpotsForAGivenYear(){
        List<String> mostUsed = Arrays.asList("Bole International Airport","John F Kennedy International Airport","Chicago O'Hare International Airport");
        assertEquals(mostUsed, FunctionUtil.mostUsedAirpotsForAGivenYear.apply(airline, 2020, 4));
    }
    
    @Test
    public void topNFlightsBasedOnSeatOccupancy() {
    	List<String> topNFlight = Arrays.asList("ET302", "ET345", "ET555");
        assertEquals(topNFlight,FunctionUtil.topNFlightsBasedOnSeatOccupancy.apply(airline,LocalDate.of(2020, 10, 15),5));
    
    }

    public void numberOfPassengersOnDailyBasis(){
        Map<LocalDate,Long> numPassanger = new HashMap<LocalDate, Long>(){{
            put(LocalDate.of(2020,10,15), 1L);
            put(LocalDate.of(2020,11,13), 1L);
            put(LocalDate.of(2020,10,10), 1L);
        }
        };

        assertEquals(numPassanger, FunctionUtil.numberOfPassengersOnDailyBasis.apply(airline, 2020));
    }

    @Test
    public void topKFlightsToRemove(){
        List<String> topKFlights = Arrays.asList("ET555", "ET302", "ET345");
        assertEquals(topKFlights, FunctionUtil.topKFlightsToRemove.apply(airline, 12, 50, 10));
    }

}
