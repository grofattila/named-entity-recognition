package tmit.webbanyaszat.ner;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.util.ErasureUtils;

import java.io.*;
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
        crf.train(BASE + "train.csv");
        crf.serializeClassifier(props.get("serializeTo").toString());
    }

    public CRFClassifier getModel(String modelPath) throws IOException, ClassNotFoundException {
        BufferedInputStream stream = new BufferedInputStream(new GZIPInputStream(new FileInputStream(modelPath)));
        return CRFClassifier.getClassifier(stream);
    }

    public void predict(CRFClassifier model, File file, String outputFileName) throws IOException {
        StringBuilder sb = new StringBuilder();


        FileOutputStream fop = new FileOutputStream(outputFileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                fop.write(model.classifyToString(line).trim().getBytes());
                fop.write("\n".getBytes());
            }
        }
        fop.close();

    }


    private void stringToFile(String outputFileName, String result) {
        try {
            FileWriter fileWriter = new FileWriter(outputFileName);
            fileWriter.append(result);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
