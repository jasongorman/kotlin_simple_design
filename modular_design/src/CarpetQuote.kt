fun quote(room: Pair<Double, Double>, carpet: Pair<Double, Boolean>) : Double {
    val (width, length) = room
    val (pricePerSqrMtr, roundUp) = carpet

    var area = width * length

    if(roundUp){
        area = Math.ceil(area)
    }

    return pricePerSqrMtr * area
}