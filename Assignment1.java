
public class Assignment1 {
    public static void main(String[] args){
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        if (a < 1 || b < 1 || c < 1){
            System.out.println("Invalid input!");

        } else if (a + b < c || a + c < b || b + c < a){
            System.out.println("The input ("+a+", "+b+", "+c+") does not define a valid triangle!");

        } else if (a*a + b*b == c*c || a*a + c*c == b*b || b*b + c*c == a*a){
            System.out.println("The input ("+a+", "+b+", "+c+") defines a valid right triangle!");

        } else {
            System.out.println("The input ("+a+", "+b+", "+c+") defines a valid triangle!");

        }
    }
}