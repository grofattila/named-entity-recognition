package tmit.webbanyaszat;

import org.apache.log4j.BasicConfigurator;
import tmit.webbanyaszat.ner.CoreNlpModel;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BasicConfigurator.configure();
        System.out.println("Launching NER detector.....");

        CoreNlpModel coreNlpModel = new CoreNlpModel();

        //coreNlpModel.train();
        coreNlpModel.run();
    }
}
