import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Neuron {

    public static List<double[]> inputs = new ArrayList<>();
    public static List<Double> outputs = new ArrayList<>();
    public static double[] weights;
    private static long startTime;

    //Constructor:
    public Neuron(List<double[]> passedInputs, List<Double> passedOutputs) {

        //Fills inputs:
        for (int i = 0; i < passedInputs.size(); i++) {
            inputs.add(passedInputs.get(i));
        }

        //Fills outputs:
        for (int i = 0; i < passedOutputs.size(); i++) {
            outputs.add(passedOutputs.get(i));
        }

        //Fills weights:
        weights = new double[passedInputs.get(0).length];
        Arrays.fill(weights, 0);
        for (int i = 0; i < passedInputs.size(); i++) {
            for (int j = 0; j < passedInputs.get(i).length; j++) {
                if (passedInputs.get(i)[j] == 0) {
                    weights[j] = weights[j] + (0.5 / passedInputs.get(i).length);
                }
                if (passedInputs.get(i)[j] != 0) {
                    weights[j] = weights[j] + (outputs.get(i) / inputs.get(i)[j]) / inputs.get(0).length;
                }
            }
        }
        for (int i = 0; i < weights.length; i++) {
            weights[i] = weights[i] / passedInputs.size();
        }

        //Displays starting weights:
        System.out.println("");
        for (int i = 0; i < weights.length; i++) {
            System.out.println("Starting Weights[" + i + "]: " + weights[i]);
        }
        System.out.println("");
    }

    //Train:
    public static void train(int epochs) {

        startTime = System.nanoTime();
        int passedCycles = 0;

        //Trains until epochs are complete:
        while (passedCycles <= epochs) {

            int row = 0;
            int column = 0;
            for (row = 0; row < inputs.size(); row++) {

                //Calculates test value:
                double test = 0;
                double real = outputs.get(row);
                for (column = 0; column < inputs.get(row).length; column++) {
                    test = test + (inputs.get(row)[column] * weights[column]);
                }

                //Adjusts weights according to error:
                double error = test - real;
                for (int i = 0; i < weights.length; i++) {
                    error = (error * inputs.get(row)[i]) / 200000000;
                    weights[i] = weights[i] - error;
                }

                passedCycles++;
            }
        }

        long elapsedTime = System.nanoTime() - startTime;
        elapsedTime = elapsedTime / 1000000;

        //Displays final weights:
        System.out.println("Training completed...");
        System.out.println("Time elapsed: " + elapsedTime + " ms");
        for (int i = 0; i < weights.length; i++) {
            System.out.println("Final Weights[" + i + "]: " + weights[i]);
        }
        System.out.println("");
    }

    //Return weights:
    public static double[] getWeights() {
        return weights;
    }

    //Return midpoint:
    public static double getMidpoint() {

        double avg = 0;
        for (Double x : outputs) {
            avg = avg + x;
        }
        avg = avg / outputs.size();

        double[] formattedOutputs = new double[outputs.size()];
        for (int i = 0; i < outputs.size(); i++) {
            formattedOutputs[i] = outputs.get(i);
        }

        double median = avg;
        Arrays.sort(formattedOutputs);
        if (formattedOutputs.length % 2 == 0) {
            double a = formattedOutputs[(formattedOutputs.length / 2) - 1];
            double b = formattedOutputs[formattedOutputs.length / 2];
            median = (a + b) / 2;
        }
        if (formattedOutputs.length % 2 != 0) {
            median =  formattedOutputs[(formattedOutputs.length - 1) / 2];
        }

        return (avg + median) / 2;
    }
}