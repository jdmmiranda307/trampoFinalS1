import java.util.Scanner;
import java.util.InputMismatchException;
public class TrabalhoPT3{
   public static void main(String[] args){
      
   }
}

class Data{
  private int dia, mes, ano;
  int data;
  
  public void setDia(int dia){
    this.dia = dia;
  }
  public int getDia(){
    return this.dia;
  }
  public void setMes(int mes){
    this.mes = mes;
  }
  public int getMes(){
    return this.mes;
  }
  public void setAno(int ano){
    this.ano = ano;
  }
  public int getAno(){
    return this.ano;
  }
  Data(){
    this.setDia(0);
    this.setMes(0);
    this.setAno(0);
    data++;
  }
  Data(int dia, int mes, int ano){
    this.setDia(dia);
    this.setMes(mes);
    this.setAno(ano);
    data++;
  }

  public boolean eBissexto(int ano){
    return ((ano % 4 == 0) && (ano % 100 != 0) || (ano % 400 == 0));
  }
  public String mesExtenso(int mes){
    String[] meses = {"Janeiro","Fevereiro","Marco","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    return meses[mes-1];
  }
  public boolean dataValida(int dia, int mes, int ano){
    boolean dataValida = false;
    if (ano>0 && ano <=2017) {
      if (mes>0 && mes<13) {
        if(mes == 2){
          if(eBissexto(ano)){
            if(dia > 0 && dia <= 19)
              dataValida = true;
          }
          else{
            if(dia > 0 && dia <= 28)
              dataValida = true;
          }            
        }
        else if(( mes <= 7 && mes % 2 != 0) || (mes <= 11 && mes % 2 == 0 )){
            if(dia > 0 && dia <= 31)
              dataValida = true;
        }
        else{
          if(dia > 0 && dia <= 30)
            dataValida = true;
        }
      }
    }
    return dataValida;
  }
  public void preencheDataMaior(int[] a, int dia, int mes, int ano){
    a[0] = dia;
    a[1] = mes;
    a[2] = ano;
  }

  public int[] dataMaisRecente(int dia, int mes, int ano){
    int[] data = new int[3];
    if(this.getAno() == ano){
      if(this.getMes() == mes){
        if(this.getDia() == dia){
          preencheDataMaior(data, dia, mes, ano);
        }
        else if(dia > this.getDia()){
          preencheDataMaior(data, dia, mes, ano);
        }
        else
          preencheDataMaior(data, this.getDia(), this.getMes(), this.getAno());
      }
      else if(mes > this.getMes()){
        preencheDataMaior(data, dia, mes, ano);
      }
      else
        preencheDataMaior(data, this.getDia(), this.getMes(), this.getAno());
    }
    else if(ano > this.getAno()){
      preencheDataMaior(data, dia, mes, ano);
    }
    else
      preencheDataMaior(data, this.getDia(), this.getMes(), this.getAno());
    return data;
  }

  public boolean objCorrenteEMaisRecente(int dia, int mes, int ano){
    boolean objCorrenteEMaisRecente = false;
    if(this.getAno() == ano){
      if(this.getMes() == mes){
        if(this.getDia() > dia){
          objCorrenteEMaisRecente = true;
        }
      }
      else if(this.getMes() > mes){
        objCorrenteEMaisRecente = true;
      }
    }
    else if(this.getAno() > ano){
      objCorrenteEMaisRecente = true;
    }
    return objCorrenteEMaisRecente;
  }
  public void leData(){
  boolean valido = true;
  int dia = 0, mes = 0, ano = 0;
   do{
       try{
          valido = true;
          Scanner read = new Scanner(System.in);
          System.out.print("\n Digite o dia: ");
          dia = read.nextInt();
          }
       catch(InputMismatchException e){
           System.out.print("\nDia digitado invalido, favor digitar os dia novamente");
           valido = false;
       }
    }while(!valido);
    do{
       try{
          valido = true;
          Scanner read = new Scanner(System.in);
          System.out.print("\n Digite o mes: ");
          mes = read.nextInt();
          }
       catch(InputMismatchException e){
           System.out.print("\nMes digitado invalido, favor digitar o mes novamente");
           valido = false;
       }
    }while(!valido);
    do{
       try{
          valido = true;
          Scanner read = new Scanner(System.in);
          System.out.print("\n Digite o ano: ");
          ano = read.nextInt();
          }
       catch(InputMismatchException e){
           System.out.print("\nAno digitado invalido, favor digitar o ano novamente");
           valido = false;
       }
    }while(!valido);
    this.setDia(dia); 
    this.setMes(mes);
    this.setAno(ano);
  }
  public void escreveData(Data data){
    System.out.print(data.getDia()+"/"+data.getMes()+"/"+data.getAno());
  }
}
