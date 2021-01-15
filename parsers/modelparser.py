def predictDeaths(features, model):
    
    result = 0
    i = 0

    while i < len(features):
        result += features[i] * model[i]
        i += 1
    
    return result