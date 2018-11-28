package tmit.webbanyaszat;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import org.apache.log4j.BasicConfigurator;
import tmit.webbanyaszat.ner.CoreNlpModel;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        System.out.println("Launching NER detector.....");

        App app = new App();

        CoreNlpModel coreNlpModel = new CoreNlpModel();
        //coreNlpModel.train();

        CRFClassifier model = coreNlpModel.getSerializedModel("/ner-model.ser.gz");


        File file = app.readFile("test/Test1NER.csv");
        coreNlpModel.predict(model, file, "Test1NER_res.csv");

        File file2 = app.readFile("test/Test2NER.csv");
        coreNlpModel.predict(model, file2, "Test2NER_res.csv");
    }

    public File readFile(String resourceName) {
        System.out.println("Reading: " + resourceName);
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(resourceName).getFile());
    }


}
