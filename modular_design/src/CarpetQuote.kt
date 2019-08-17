fun quote(room: Pair<Double, Double>, carpet: Pair<Double, Boolean>): Double {
    val (width, length) = room
    val (pricePerSqrMtr, roundUp) = carpet

    val area = width * length

    val areaToPrice = if (roundUp) {
        Math.ceil(area)
    } else {
        area
    }

    return pricePerSqrMtr * areaToPrice
}