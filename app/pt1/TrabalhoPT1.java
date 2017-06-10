import java.util.Scanner;
public class TrabalhoPT1{
   public static void main(String[] args){
      double[][] m = new double[5][5];
      leMatriz(m);
      maiorLinha(m);
      mediaDiagonal(m);
      maiorAbaixo(m);
      colMaior(m);
      double[][] mt = new double[5][5];
      criaMatrizTransposta(m , mt);
      imprimeMatriz(mt);
   }
   public static void leMatriz(double[][] matriz){    
      Scanner read = new Scanner(System.in);
      for(int i = 0; i < matriz.length; i++){
         for(int j = 0; j < matriz[i].length; j++){
             System.out.print("Digite o valor para a posicao Matriz["+ (i+1) +"]["+ (j+1) +"]: ");
             matriz[i][j] = read.nextDouble();
         }
      } 
   }
   public static void maiorLinha(double[][] m){
      double maior;
      for(int i = 0; i < m.length; i++){
         maior = 0;
         for(int j = 0; j < m[i].length; j++){
            if(m[i][j] > maior)
               maior = m[i][j];
            if(j == m[i].length - 1)
               System.out.print("\nO maior valor da linha: " + (i+1) + " e o: "+ maior);
         }         
      }
   }
    public static void mediaDiagonal(double[][] m){
      double soma = 0;
      for(int i = 0; i < m.length; i++){
         soma += m[i][i];       
      }
      System.out.print("\nA media da diagonal da matriz e: "+ (soma/m.length));
   }
   public static void maiorAbaixo(double[][] m){
      double maior = 0;
      for(int i = 0; i < m.length; i++){
         for(int j = i-1; j >= 0; j--){
            if(m[i][j] > maior)
               maior = m[i][j];
         }  
      }
      System.out.print("\n O maior valor abaixo da diagonal e: " + maior);
   }
   public static void colMaior(double[][] m){
      double maior = 0;
      int col = 0;
      for(int i = 0; i < m.length; i++){
         for(int j = 0; j < m[i].length; j++){
            if(m[i][j] > maior){
               maior = m[i][j];
               col = j;
            }
         }  
      }
      System.out.print("\n A coluna do maior valor da matriz e: " + (col+1));

   }
   public static void criaMatrizTransposta(double[][] m, double[][] mt){
      for(int i = 0; i < m.length; i++){
         for(int j = 0; j < m[i].length; j++){
            mt[j][i] = m[i][j];
         }
      }
   }
   public static void imprimeMatriz(double[][] m){
      for(int i = 0; i < m.length; i++){
         System.out.println();
         for(int j = 0; j < m[i].length; j++){
            System.out.print("  " + m[i][j]);
         }
      }  
   }

}