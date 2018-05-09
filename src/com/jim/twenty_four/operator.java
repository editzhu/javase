package com.jim.twenty_four;

public class operator {
    public float[] Num = new float[4];
    public float result;

    public operator() {
	Num[0] = 0;
	Num[1] = 0;
	Num[2] = 0;
	Num[3] = 0;
    }

    public void set4num(float[] num) {
	for (int i = 0; i < num.length; i++) {
	    Num[i] = num[i];
	}
    }

    public String jisuan() {
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < 4; j++) {
		for (int k = 0; k < 4; k++) {
		    result = fourOp(k, fourOp(j, fourOp(i, Num[0], Num[1]), Num[2]), Num[3]);
		    System.out.println(result);
		    if (result > 23.9 && result < 24.1) {
			return Num[0] + "+" + Num[1] + "+" + Num[2] + "+" + Num[3] + "+" + i + "+" + j + "+" + k;
		    }
		}
	    }
	}
	return "error";
    }

    public float fourOp(int op, float num1, float num2) {
	if (op == 0)
	    return num1 + num2;
	if (op == 1)
	    return num1 - num2;
	if (op == 2)
	    return num1 * num2;
	if (op == 3)
	    return num1 / num2;
	return 0;
    }

    public static void main(String[] args) {
	operator o = new operator();
	float[] nn = { 2, 3, 9, 9 };
	o.set4num(nn);
	System.out.println(o.jisuan());
    }
}
