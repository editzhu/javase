public class test {

    test1[] t = new test1[2];

    public static void main(String[] args) {
	test test = new test();
	test.t[0] = new test1();
	System.out.println(test.t[0]);
    }
}