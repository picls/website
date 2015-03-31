package thread;

import java.io.Console;

public class test {
	
	public static void main(String args[]){
		Testthread t1 = new Testthread();
		System.out.println(t1.getState());
		t1.start();
		t1.run();
		System.out.println(t1.getState());
		
		
		
	}

}

class Testthread extends Thread{
	public void start(){
		System.out.println('x');
		try{ 
			this.sleep(1000);
		}catch(InterruptedException e){		}
		System.out.println(this.getId());
		System.out.println(this.getName());
		System.out.println(this.getPriority());
		System.out.println(this.getState());
		//System.out.println(this.);
	}
	
	public void run(){
		
	}
	
	public void deal(){
		
	}
}
