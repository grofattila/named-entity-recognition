package tmit.webbanyaszat.ner;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.SeqClassifierFlags;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CoreNlpModel {

    private static final String BASE = "/home/atig/Data/github/named-entity-recognition/ner/src/main/resources/train/";


    public CoreNlpModel() {

    }

    /**
     * NER modell tanítása
     */
    public void train() throws IOException {
        InputStream in = CoreNlpModel.class.getResourceAsStream("/train.properties");
        Properties props = new Properties();
        props.load(in);

        SeqClassifierFlags flags = new SeqClassifierFlags(props);
        CRFClassifier<CoreLabel> crf = new CRFClassifier<>(flags);
        for (int i = 0; i < 197; i++) {
            crf.train(BASE + "train" + (i + 1) + ".data");
        }
        crf.serializeClassifier(props.get("serializeTo").toString());
    }

    public CRFClassifier getModel(String modelPath) {
        return CRFClassifier.getClassifierNoExceptions(modelPath);
    }

    public void doTagging(CRFClassifier model, String input) {
        input = input.trim();
        System.out.println(input + "=>" + model.classifyToString(input));
    }

    public void run() throws IOException, ClassNotFoundException {
        InputStream in = CoreNlpModel.class.getResourceAsStream("/train.properties");
        Properties props = new Properties();
        props.load(in);

        SeqClassifierFlags flags = new SeqClassifierFlags(props);


        InputStream model = CoreNlpModel.class.getResourceAsStream("/ner-model.ser.gz");

        CRFClassifier<CoreLabel> crf = new CRFClassifier<>(props);
        crf.loadClassifier(model, props);

        String[] tests = new String[]{"apple watch", "samsung mobile phones", " lcd 52 inch tv"};
        for (String item : tests) {
            doTagging(crf, item);
        }
    }


}
