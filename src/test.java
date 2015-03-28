
public class test {
    String s;
    {
	s = new String("adf");
	System.out.println(s);
    }

    public static void main(String[] args) {
	System.out.println("inside main()");
	int[][] a1 = { { 1, 2, 3, }, { 4, 5, 6, }, };
	System.out.println(a1[0].length);
    }
}
