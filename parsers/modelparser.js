function predictDeaths(features, model) {
    
    var result = 0;

    for (var i = 0; i < features.length; i++) {
        result += features[i] * model[i];
    }

    return result;
}