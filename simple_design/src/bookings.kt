import java.util.Date

fun book(user:String, type:Type, date: Date, placeRepository:MutableMap<Pair<String,Int>,Booking?>, choice:Pair<String,Int>, placeType:PlaceType, food:FoodOption?):Booking?{
    if(!placeRepository.containsKey(choice)){
        throw PlaceDoesNotExistException()
    }
    
    if(placeRepository[choice] != null){
        throw DoubleBookingException()
    }
    
    var booking : Booking? = null

    when(type){
        Type.A -> {
            val carriageClass = when(placeType){
                PlaceType.One -> CarriageClass.First
                PlaceType.Two -> CarriageClass.Coach
                else -> {
                    throw InvalidClassSelectionException()
                }
            }
            booking = TBooking(user, date, choice, carriageClass)
        }
        Type.B -> {
            val seatingClass = when(placeType){
                PlaceType.One -> SeatingClass.First
                PlaceType.Two -> SeatingClass.Business
                PlaceType.Three -> SeatingClass.Economy
            }
            if(food == null){
                throw MissingFoodChoiceException()
            }
            booking = FBooking(user, date, choice, seatingClass, food)
        }
        Type.C -> {
            val section = when(placeType){
                PlaceType.One -> Section.FrontStalls
                PlaceType.Two -> Section.RearStalls
                PlaceType.Three -> Section.Circle
            }
            booking = TRBooking(user, date, choice, section)
        }
    }
    placeRepository[choice] = booking
    return booking
}

class MissingFoodChoiceException : Throwable()

class DoubleBookingException : Throwable()

class PlaceDoesNotExistException : Throwable()

class TRBooking(user: String, date: Date, placeSelection: Pair<String, Int>, val section: Section) : Booking(user, date, placeSelection)

class FBooking(user: String, date: Date, placeSelection: Pair<String, Int>, val seatingClass: SeatingClass, val foodOption: FoodOption) : Booking(user, date, placeSelection)

class InvalidClassSelectionException : Throwable()

open class Booking(open val user:String, open val date: Date, open val placeSelection:Pair<String,Int>)

class TBooking(user: String, date: Date, placeSelection: Pair<String, Int>, val carriageClass: CarriageClass) : Booking(user, date, placeSelection)

enum class Type {
    A,
    B,
    C
}

enum class PlaceType {
    One,
    Two,
    Three
}

enum class CarriageClass {
    First,
    Coach
}

enum class SeatingClass {
    First,
    Business,
    Economy
}

enum class FoodOption {
    Meat,
    Fish,
    Vegan
}

enum class Section {
    FrontStalls,
    RearStalls,
    Circle
}