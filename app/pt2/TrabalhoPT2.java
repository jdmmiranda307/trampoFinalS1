import java.util.Scanner;
public class TrabalhoPT2{
   public static void main(String[] args){
      Scanner read = new Scanner(System.in);
      System.out.print("Digite a posicao k da sequencia de fibonacci que voce deseja saber: ");
      int pfibonacci = read.nextInt();
      System.out.println("O valor na posicao e: " + fibonacci(pfibonacci));
     //  double[] arr = {0, 9, 8, 7, 6, 5, 4, 3, 2};
//       arr = inverteArranjo(arr);
//       for(int i = 0; i < arr.length; i++){
//          System.out.println(arr[i]);
//       }
      String palavra = "arara";
      if(palindromoRecursivo(palavra))
         System.out.println("PALINDROMO!");
      else
         System.out.println("NAO E PALINDROMO");
   }
   public static int fibonacci(int n){
      int fib;
      if (n == 0) fib = 0;
      else if(n == 1) fib =  1;
      else fib = fibonacci(n-1) + fibonacci(n-2);
      return fib;
   }
   public static double[] inverteArranjo(double[] a){
      double[] aux = a;
      return inverteArranjo(a,aux, 0, a.length-1);
   }
   public static double[] inverteArranjo(double[] a, double[] aux, int i, int j){
      if (i == a.length-1){
         return aux;   
      }
      else{
         aux[j] = a[i];
         a = inverteArranjo(a,aux, i++, j--);
         return a;
      }
   }
   
   public static boolean palindromoRecursivo(String palavra){
      return palindromoRecursivo(palavra, 0, palavra.length());
   }
   
   public static boolean palindromoRecursivo(String palavra, int i, int j){
      boolean palindromo = false;
      if(j <= i){
         if(palavra.charAt(i) == palavra.charAt(j)) palindromo = true;
         else palindromo = false;
      }
      else{
         if(palavra.charAt(i) != palavra.charAt(j)) palindromo = false;
         else palindromo = palindromoRecursivo(palavra, i++, j--);
      }
      return palindromo;
   }
}