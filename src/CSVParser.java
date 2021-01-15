import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static String path;
    public static List<List<Double>> inputs = new ArrayList<>();
    public static List<Double> outputs = new ArrayList<>();

    public CSVParser(String _path) {
        this.path = _path;
    }

    //Get inputs:
    public List<List<Double>> getInputs() {

        Path p = Paths.get(path);

        try {
            List<String> lines = Files.readAllLines(p);
            for (int i = 0; i < lines.size(); i++) {
                inputs.add(new ArrayList<>());
                String[] raw = lines.get(i).split(",");
                for (int j = 0; j < raw.length - 1; j++) {
                    double val = Double.valueOf(raw[j]);
                    inputs.get(i).add(val);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        return this.inputs;
    }

    //Get outputs:
    public List<Double> getOutputs() {

        Path p = Paths.get(path);

        try {
            List<String> lines = Files.readAllLines(p);
            for (int i = 0; i < lines.size(); i++) {
                String[] raw = lines.get(i).split(",");
                double val = Double.valueOf(raw[raw.length - 1]);
                outputs.add(val);
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        return this.outputs;
    }
}