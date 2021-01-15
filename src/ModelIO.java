import java.io.File;
import java.io.FileWriter;

public class ModelIO {

    private static double[] weights;
    private static double midpoint;

    //Constructor:
    public ModelIO(double[] passedWeights, double passedMidpoint) {
        weights = new double[passedWeights.length];
        for (int i = 0; i < passedWeights.length; i++) {
            weights[i] = passedWeights[i];
        }
        midpoint = passedMidpoint;
    }

    //Cache Model:
    public static void cacheModel(String filePath) {
        try {
            File file = new File(filePath);
            file.createNewFile();
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < weights.length; i++) {
                fw.write(String.valueOf(weights[i]));
                if (i != weights.length - 1) {
                    fw.write(",");
                }
            }

            fw.write("\n");
            fw.write(String.valueOf(midpoint));
            fw.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }
    }
}