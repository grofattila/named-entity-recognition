package tmit.webbanyaszat.ner;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.util.ErasureUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

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
        //for (int i = 0; i < 197; i++) {
        //crf.train(BASE + "train" + (i + 1) + ".data");
        /// crf.serializeClassifier(props.get("serializeTo").toString());
        // }
        crf.train(BASE + "train.csv");
        crf.serializeClassifier(props.get("serializeTo").toString());
    }

    public CRFClassifier getModel(String modelPath) throws IOException, ClassNotFoundException {
        BufferedInputStream stream = new BufferedInputStream(new GZIPInputStream(new FileInputStream(modelPath)));
        return CRFClassifier.getClassifier(stream);
    }

    public void predict(CRFClassifier model, String[] wordList) {
        ArrayList<String> list = new ArrayList<>();

        for (String word : wordList) {
            list.add(model.classifyToString(word));
        }
        System.out.println(list);
    }


    /**
     * Osztályozó betöltése.
     *
     * @param modelPath Elérési útvonal.
     * @return Modell.
     */
    public CRFClassifier getSerializedModel(String modelPath) {
        CRFClassifier<CoreLabel> classifier = null;
        try (InputStream is = CoreNlpModel.class.getResourceAsStream(modelPath)) {
            InputStream zis = is;
            zis = new GZIPInputStream(is);
            classifier = ErasureUtils.uncheckedCast(CRFClassifier.getClassifier(zis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classifier;
    }

}
