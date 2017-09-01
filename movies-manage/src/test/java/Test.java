import org.springframework.core.io.PathResource;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * Created by Cfw on 2017/6/7.
 */
public class Test {

    public static void main(String [] args) throws IOException {
        //Paths.get("E:/rmi/rmiImporter.xml")
        PathResource pathResource = new PathResource("/rmi/rmiImporter.xml");
        InputSource inputSource = new InputSource(pathResource.getInputStream());

    }
}
