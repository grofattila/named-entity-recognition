package tmit.webbanyaszat;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import org.apache.log4j.BasicConfigurator;
import tmit.webbanyaszat.ner.CoreNlpModel;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        System.out.println("Launching NER detector.....");


        CoreNlpModel coreNlpModel = new CoreNlpModel();
        //coreNlpModel.train();

        CRFClassifier model = coreNlpModel.getSerializedModel("/ner-model.ser.gz");
        coreNlpModel.predict(model, new String[]{"In", " 2005", " Zambia", " qualified", " for", " debt", " relief", " under", " the", " Highly", " Indebted", " Poor", " Country", " Initiative", " consisting"});
    }
}
