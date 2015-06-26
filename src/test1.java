import java.util.ArrayList;
import java.util.List;

public class test1 {
    private static List<String> list;

    public static List<?> get() {
	list = new ArrayList<String>();
	return list;
    }
}
