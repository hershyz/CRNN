public double predictDeaths(double[] features, double[] model) {

    double result = 0;
    for (int i = 0; i < features.length; i++) {
        result += features[i] * model[i];
    }

    return result;
}