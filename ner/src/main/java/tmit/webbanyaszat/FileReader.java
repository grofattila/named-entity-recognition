package tmit.webbanyaszat;

import java.io.InputStream;

public class FileReader {

    public void getFile(String filePath) {
        InputStream in = FileReader.class.getResourceAsStream(filePath);
        //while (in.)
    }

}
