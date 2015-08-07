import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class test {

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
	double y = 1000000;
	double x = 6822.52;
	double z = 0.0045;
	int count = 0;
	while (true) {
	    y = y * z + y - x;
	    count++;
	    if (y < 0)
		break;
	}
	System.out.println(y);
    }
}
