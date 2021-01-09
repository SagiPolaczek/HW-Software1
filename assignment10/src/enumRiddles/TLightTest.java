package enumRiddles;

enum TLight {
	   // Each instance provides its implementation to abstract method
	   RED(30),
	   AMBER(10),
	   GREEN(30);
	 
	   
	   private final int seconds;     // Private variable
	 
	   TLight(int seconds) {          // Constructor
	      this.seconds = seconds;
	   }
	 
	   int getSeconds() {             // Getter
	      return seconds;
	   }  
	   
	   public TLight next() {
		   if (this == RED) {
			   return GREEN;
		   }
		   if (this == AMBER) {
			   return RED;
		   }
		   return AMBER;
	   }
	}
	   
	public class TLightTest {
	   public static void main(String[] args) {
	      for (TLight light : TLight.values()) {
	         System.out.printf("%s: %d seconds, next is %s\n", light,
	               light.getSeconds(), light.next());
	      }
	   }
	}