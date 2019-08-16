@file:Suppress("DEPRECATION")

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertSame
import org.junit.Test
import java.util.*

class BookingTest {
    @Test(expected = DoubleBookingException::class) fun doubleBooking(){
        val user = ""
        val type = Type.A
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to Booking(user, date, choice))
        val placeType = PlaceType.One
        book(user,type, date, places, choice, placeType, null)
    }

    @Test(expected = PlaceDoesNotExistException::class) fun placeNotExist() {
        val user = ""
        val type = Type.A
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        book(user,type, date, places, Pair("B",2), placeType, null)
    }

    @Test fun tBookingCreated() {
        val user = ""
        val type = Type.A
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking = book(user,type, date, places, Pair("A", 1), placeType, null)
        assertEquals(TBooking::class, booking!!::class)
    }

    @Test fun fBookingCreated() {
        val user = ""
        val type = Type.B
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking = book(user,type, date, places, Pair("A", 1), placeType, FoodOption.Meat)
        assertEquals(FBooking::class, booking!!::class)
    }

    @Test fun trBookingCreated() {
        val user = ""
        val type = Type.C
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking = book(user,type, date, places, Pair("A", 1), placeType, null)
        assertEquals(TRBooking::class, booking!!::class)
    }

    @Test fun selectedPlaceHasBooking() {
        val user = ""
        val type = Type.C
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking = book(user,type, date, places, Pair("A", 1), placeType, null)
        assertSame(booking, places[choice])
    }

    @Test fun bookingHasUser() {
        val user = "ajssaxf"
        val type = Type.C
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking = book(user,type, date, places, Pair("A", 1), placeType, null)
        assertEquals(user, booking!!.user)
    }

    @Test fun bookingIsForSpecifiedDate() {
        val user = ""
        val type = Type.C
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking = book(user,type, date, places, Pair("A", 1), placeType, null)
        assertEquals(date, booking!!.date)
    }

    @Test fun bookingsIsForSelectedPlace() {
        val user = ""
        val type = Type.C
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking = book(user,type, date, places, Pair("A", 1), placeType, null)
        assertEquals(choice, booking!!.placeSelection)
    }

    @Test fun tBookingOfPlaceTypeOneIsFirstClass() {
        val user = ""
        val type = Type.A
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking : TBooking = book(user,type, date, places, Pair("A", 1), placeType, null) as TBooking
        assertEquals(CarriageClass.First, booking.carriageClass)
    }

    @Test fun tBookingOfPlaceTypeTwoIsCoach() {
        val user = ""
        val type = Type.A
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.Two
        val booking : TBooking = book(user,type, date, places, Pair("A", 1), placeType, null) as TBooking
        assertEquals(CarriageClass.Coach, booking.carriageClass)
    }

    @Test(expected=InvalidClassSelectionException::class) fun tBookingOfPlaceTypeThreeIsInvalid() {
        val user = ""
        val type = Type.A
        val date = Date()
        val choice = Pair("A", 1)
        val places : MutableMap<Pair<String,Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.Three
        val booking : TBooking = book(user,type, date, places, Pair("A", 1), placeType, null) as TBooking
        assertEquals(CarriageClass.First, booking.carriageClass)
    }

    @Test fun fBookingOfPlaceTypeOneIsFirstClass() {
        val user = ""
        val type = Type.B
        val date = Date()
        val choice = Pair("A", 1)
        val places: MutableMap<Pair<String, Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking: FBooking = book(user, type, date, places, Pair("A", 1), placeType, FoodOption.Meat) as FBooking
        assertEquals(SeatingClass.First, booking.seatingClass)
    }

    @Test fun fBookingOfPlaceTypeTwoIsBusinessClass() {
        val user = ""
        val type = Type.B
        val date = Date()
        val choice = Pair("A", 1)
        val places: MutableMap<Pair<String, Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.Two
        val booking: FBooking = book(user, type, date, places, Pair("A", 1), placeType, FoodOption.Meat) as FBooking
        assertEquals(SeatingClass.Business, booking.seatingClass)
    }

    @Test fun fBookingOfPlaceTypeThreeIsEconomyClass() {
        val user = ""
        val type = Type.B
        val date = Date()
        val choice = Pair("A", 1)
        val places: MutableMap<Pair<String, Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.Three
        val booking: FBooking = book(user, type, date, places, Pair("A", 1), placeType, FoodOption.Meat) as FBooking
        assertEquals(SeatingClass.Economy, booking.seatingClass)
    }

    @Test fun foodOptionIsRecordedForFBooking() {
        val user = ""
        val type = Type.B
        val date = Date()
        val choice = Pair("A", 1)
        val places: MutableMap<Pair<String, Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking: FBooking = book(user, type, date, places, Pair("A", 1), placeType, FoodOption.Meat) as FBooking
        assertEquals(FoodOption.Meat, booking.foodOption)
    }

    @Test(expected=MissingFoodChoiceException::class) fun foodChoiceNotMadeForFBooking() {
        val user = ""
        val type = Type.B
        val date = Date()
        val choice = Pair("A", 1)
        val places: MutableMap<Pair<String, Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        book(user, type, date, places, Pair("A", 1), placeType, null) as FBooking
    }

    @Test fun trBookingOfPlaceTypeOneIsFrontStalls() {
        val user = ""
        val type = Type.C
        val date = Date()
        val choice = Pair("A", 1)
        val places: MutableMap<Pair<String, Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.One
        val booking = book(user, type, date, places, Pair("A", 1), placeType, null) as TRBooking
        assertEquals(Section.FrontStalls, booking.section)
    }

    @Test fun trBookingOfPlaceTypeTwoIsRearStalls() {
        val user = ""
        val type = Type.C
        val date = Date()
        val choice = Pair("A", 1)
        val places: MutableMap<Pair<String, Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.Two
        val booking: TRBooking = book(user, type, date, places, Pair("A", 1), placeType, null) as TRBooking
        assertEquals(Section.RearStalls, booking.section)
    }

    @Test fun trBookingOfPlaceTypeThreeIsCircle() {
        val user = ""
        val type = Type.C
        val date = Date()
        val choice = Pair("A", 1)
        val places: MutableMap<Pair<String, Int>, Booking?> = mutableMapOf(choice to null)
        val placeType = PlaceType.Three
        val booking = book(user, type, date, places, Pair("A", 1), placeType, null) as TRBooking
        assertEquals(Section.Circle, booking.section)
    }
}