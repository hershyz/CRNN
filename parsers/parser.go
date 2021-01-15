func predictDeaths(features []double, model []double) double {

	result := 0

	for i, s:= range features {
		result += [i]features * [i]model
	}

	return result
}