import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {
	Map<String, String> m = new HashMap<String, String>();
	String s = "1";
	for (int i = 0; i < 1000000000; i++) {
	    m.put(s, s);
	}

    }
}
