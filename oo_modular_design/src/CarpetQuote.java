class CarpetQuote {

    double quote(double width, double length, double pricePerSqrMtr, boolean roundUp){
        double area = width * length;

        if(roundUp) {
            area = Math.ceil(area);
        }

        return pricePerSqrMtr * area;
    }
}
