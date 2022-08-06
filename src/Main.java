import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Get input:
        Scanner scn = new Scanner(System.in);
        System.out.println("CRNN - COVID-19 Risk Neural Network");
        System.out.println("1) Train");
        System.out.println("2) Predict");
        System.out.print("> ");
        String input = scn.nextLine();
        input = input.trim();

        //Training sequence:
        if (input.equals("1")) {

            System.out.print("Input CSV:  ");
            String path = scn.nextLine();
            CSVParser csvParser = new CSVParser(path);
            System.out.print("Epochs:     ");
            int epochs = scn.nextInt();

            List<List<Double>> inputs = csvParser.getInputs();
            List<Double> outputs = csvParser.getOutputs();

            List<double[]> formattedInputs = new ArrayList<>();
            for (int i = 0; i < inputs.size(); i++) {
                List<Double> current = inputs.get(i);
                double[] arr = new double[current.size()];
                for (int j = 0; j < current.size(); j++) {
                    arr[j] = current.get(j);
                }
                formattedInputs.add(arr);
            }

            Neuron neuron = new Neuron(formattedInputs, outputs);
            neuron.train(epochs);

            System.out.print("Model location: ");
            String model = scn.next();
            ModelIO mio = new ModelIO(neuron.getWeights(), neuron.getMidpoint());
            mio.cacheModel(model);
        }

        //Prediction sequence:
        if (input.equals("2")) {

            System.out.print("Input CSV:       ");
            String in = scn.nextLine();
            System.out.print("Model location:  ");
            String model = scn.nextLine();

            PredictionService.predict(in, model);
        }

        if (!input.equals("1") && !input.equals("2")) {
            System.out.println("Must enter training or prediction sequences");
        }
    }
}