package com.jim.thinkinjava.c04;



//: Garbage.java
//Demonstration of the garbage
//collector and finalzation

class Chair{
	static boolean gcrun = false;
	static boolean f = false;
	static int created =0;
	static int finalized = 0;
	int i ;
	Chair(){
		i = ++created;
		if(created == 47)
			System.out.println("Created 47");
		
	}
	protected void finalize(){
		if(!gcrun){
			gcrun = true;
			System.out.println(
			"Beginning to finalize after "+
			created+" Chairs have been created");
			
		}
		if (i == 47){
			System.out.println(
			"Finalizing Chair #47 " +
			"Setting flag to stop Chair creation");
			f = true;
		}
		finalized++;
		if(finalized >= created)
			System.out.println(
		"All " + finalized + " finalized");
	}
}

public class Garbage{
	public static void main(String[] args){
		if(args.length ==0){
			System.err.println("error!you should input \"befor\" or \"after\"");
			//return;
		}
		while(!Chair.f){

			new Chair();
			new String("To take up space");
		}
		System.out.println(
		"After all Chairs hava been created:\n" + 
		"total create = " + Chair.created +
		", total finalizde = " + Chair.finalized);
		//if(args[0].equals("before")){
			System.out.println("gc()");
			System.gc();
			System.out.println("runFinalization():");
			System.runFinalization();
		//}
		System.out.println("bye!");
		//if(args[0].equals("after"))
			//System.runFinalizersOnExit(true);
		
	}
}///:~
