fun quote(width: Double, length: Double, pricePerSqrMtr: Double, roundUp: Boolean): Double {
    val area = width * length

    val areaToPrice = if (roundUp) {
        Math.ceil(area)
    } else {
        area
    }

    return pricePerSqrMtr * areaToPrice
}