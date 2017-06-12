import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
public class TrabalhoPT3{
   public static void main(String[] args){
    Scanner read = new Scanner(System.in);
    int option =0;
    int f = 0;
    Funcionario[] funcionarios = new Funcionario[100];
    do{
      option = menuPrincipal();
      if(option != 0){
        switch(option){
            case 1:
              funcionarios[Funcionario.getFuncionarios()] = new Funcionario();
              funcionarios[Funcionario.getFuncionarios()-1].leFuncionario();
              break;
            case 2:
              for(int i = 0; i < Funcionario.getFuncionarios(); i++){
                try{
                funcionarios[i].escreveFuncionario();
                }
                catch(Exception e){
                  System.out.println("Algo inesperado aconteceu!");
                }
              }
              break;
            case 3:
              System.out.print("Digite qual o funcionario que sera comparado o salario: ");
              f = leInteiro();
              System.out.print("Digite o valor a ser comparado com o salario do funcionario escolhido: ");
              double valor = leReal();
              try{
                if(funcionarios[f-1].maiorSalario(valor))
                  System.out.print("O salario do funcionario e maior que o informado");
                else
                  System.out.print("O salario informado e maior que o do funcionario");
              }
              catch(Exception e){
                System.out.print("Funcionario inexistente.");
              }
              break;
            case 4:
              String nome;
              System.out.println("Digite o nome do funcionario: ");
              nome = read.nextLine();
              for(int i = 0; i < Funcionario.getFuncionarios(); i++){
                funcionarios[i].pesquisaFuncionario(nome);
              }
              break;
            case 5:
              Funcionario.ordenarFuncionario(funcionarios);
              break;
            case 6:
              System.out.println("Digite o CPF do funcionario: ");
              long cpf = leLong();
              try{
                Funcionario.pesquisaFuncionarioCPF(funcionarios, 0, Funcionario.getFuncionarios(), cpf);
              }
              catch(Exception e){
                System.out.println("Ocorreu algo de errado no sistema.");
              }
              break;
            case 7:
              escreveArquivo(funcionarios);
              escreverNFuncionarios();
              break;
            case 8:
              leArquivo(funcionarios);
              break;
            case 9:
              System.out.println("Digite o valor a ser comparado: ");
              acimaValor(funcionarios, leReal());
              break;
            case 10:
              System.out.println("Digite o dia a ser comparado: ");
              int dia =leInteiro();
              System.out.println("Digite o mes a ser comparado: ");
              int mes =leInteiro();
              System.out.println("Digite o ano a ser comparado: ");
              int ano =leInteiro();
              Data date = new Data(dia, mes, ano);
              admitidosDepois(date, funcionarios);
              break;
            case 11:
              System.out.println("Digite o mes que voce desaja buscar: ");
              int mesAniversario =leInteiro();
              aniversariamMes(funcionarios, mesAniversario);
              break;
        }
      }
    }while(option != 0);
  }
  public static void aniversariamMes(Funcionario[] aF, int mes){
    for(int i = 0; i < Funcionario.getFuncionarios(); i++){
      if(aF[i].getDtNascimento().getMes() == mes){
        aF[i].escreveFuncionario();
      }
    }
  }
  public static void admitidosDepois(Data d, Funcionario[] aF){
    for(int i = 0; i < Funcionario.getFuncionarios(); i++){
      if(aF[i].getDtAdmissao().objCorrenteEMaisRecente(d.getDia(), d.getMes(), d.getAno())){
        aF[i].escreveFuncionario();
      }
    }
  }
  public static void acimaValor(Funcionario[] aF, double valor){
    for(int i = 0; i < Funcionario.getFuncionarios(); i++){
      if(aF[i].getSalario() > valor){
        aF[i].escreveFuncionario();
      }
    }
  }
   public static int lerNumero(String nomeArq){
      int funcs = 0;
     try{
         InputStream is = new FileInputStream(nomeArq);
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         funcs = Integer.parseInt(br.readLine());; 
         br.close();
         return funcs;
      }
      catch(IOException ioexception){
         System.err.println(ioexception);
      }
      return funcs;
   }
  public static void leArquivo(Funcionario[] aF){
    ObjectInputStream input;
    Funcionario aux = new Funcionario();
    try{
      input= new ObjectInputStream(
      new FileInputStream("funcionarios.dat"));
      for(int i = 0; i < lerNumero("nFuncionarios.txt"); i++){
        aux = (Funcionario)input.readObject();  
        aF[i] = new Funcionario(aux.getCpf(), aux.getNome(), aux.getDtNascimento().getDia(), aux.getDtNascimento().getMes(), aux.getDtNascimento().getAno(), aux.getDtAdmissao().getDia(), aux.getDtAdmissao().getMes(), aux.getDtAdmissao().getAno(), aux.getSalario());
        Funcionario.funcionarios--;
      }
      input.close();
    }
    catch(Exception e){
      System.err.println("Erro ao manipular arquivo");
    }
  }
  public static void escreveArquivo(Funcionario[] aF){
    ObjectOutputStream output;
    try{
      output= new ObjectOutputStream(
      new FileOutputStream("funcionarios.dat"));
        for(int i = 0; i < Funcionario.getFuncionarios(); i++){  
          output.writeObject(aF[i]);  
        }
      output.close();
    }
    catch(Exception e){
      System.err.println("Erro ao manipular arquivo");
    }
  }
  public  static  void escreverNFuncionarios(){
    File nFun = new File("nFuncionarios.txt");
    try{
         if(nFun.createNewFile()){
            System.out.println("Foi criado o arquivo ");
         }
      }
      catch(IOException ioException){
         System.out.print(ioException);
      }
      try(FileWriter fw = new FileWriter(nFun)){
      fw.write(Funcionario.getFuncionarios() +"\n");
      fw.flush();
      }
      catch(IOException ioException){
         System.out.print(ioException);
      }
  }
  public static long leLong(){
    boolean valido = true;
    long longo = 0;
    do{
       try{
          valido = true;
          Scanner read = new Scanner(System.in);
          longo = read.nextLong();
          }
       catch(InputMismatchException e){
           System.out.print("\nvalor invalido, favor digitar novamente: ");
           valido = false;
       }
    }while(!valido);
    return longo;

  }
  public static int leInteiro(){
    boolean valido = true;
    int inteiro = 0;
    do{
       try{
          valido = true;
          Scanner read = new Scanner(System.in);
          inteiro = read.nextInt();
          }
       catch(InputMismatchException e){
           System.out.print("\nvalor invalido, favor digitar novamente: ");
           valido = false;
       }
    }while(!valido);
    return inteiro;
  }
   public static double leReal(){
    boolean valido = true;
    double real = 0;
    do{
       try{
          valido = true;
          Scanner read = new Scanner(System.in);
          real = read.nextDouble();
          }
       catch(InputMismatchException e){
           System.out.print("\nvalor invalido, favor digitar novamente: ");
           valido = false;
       }
    }while(!valido);
    return real;
  }
  public static int menuPrincipal(){
    Scanner read = new Scanner(System.in);
    int option = 0; 
    do{       
      System.out.print("\nDigite:"
                       + "\n 0 - Sair do programa"
                       + "\n 1 - Inserir Funcionario"
                       + "\n 2 - Visualizar Funcionario"
                       + "\n 3 - Maior Salario"
                       + "\n 4 - Buscar funcionario por nome"
                       + "\n 5 - Ordenar funcionarios por CPF"
                       + "\n 6 - Buscar funcionarios por CPF"
                       + "\n 7 - Armazenar funcionarios em disco"
                       + "\n 8 - Ler funcionarios armazenados em disco para um arranjo"
                       + "\n 9 - Funcionarios que ganham acima de valor"
                       + "\n 10 - Funcionarios admitidos depois de data"
                       + "\n 11 - Funcionarios que aniversariam em determinado mes\n");
     option = read.nextInt();
     if(option <0 || option > 11) System.out.print("Opcao Invalida. Selecione novamente\n");
    }while(option <0 || option > 11);
    return option;
  }
}

class Data implements Serializable{
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
    boolean erro = true;
    do{
       try{
          Scanner read = new Scanner(System.in);
          System.out.print("\n Digite o dia: ");
          int dia = read.nextInt();
          erro = false;
          this.setDia(dia); 
          }
       catch(InputMismatchException e){
           System.out.print("\ndia digitado invalido, favor digitar os dia novamente");
       }
    }while(erro);
    erro = true;
    do{
       try{
          Scanner read = new Scanner(System.in);
          System.out.print("\n Digite o mes: ");
          int mes = read.nextInt();
          erro = false;
          this.setMes(mes);
          }
       catch(InputMismatchException e){
           System.out.print("\nmes digitado invalido, favor digitar os mes novamente");
       }
    }while(erro);
    erro = true;
    do{
       try{
          Scanner read = new Scanner(System.in);
          System.out.print("\n Digite o ano: ");
          int ano = read.nextInt();
          erro = false;
          this.setAno(ano);
          }
       catch(InputMismatchException e){
           System.out.print("\nano digitado invalido, favor digitar os ano novamente");
       }
    }while(erro);
  }
  public void escreveData(Data data){
    System.out.print(data.getDia()+"/"+data.getMes()+"/"+data.getAno());
  }
}

class Funcionario implements Serializable{
    private long cpf;
    static int funcionarios = 0;
    private String nome;
    private Data dtNascimento = new Data();
    private Data dtAdmissao = new Data(); 
    private double salario;
  
  public void setCpf(long cpf){
    this.cpf = cpf;
  }
  public long getCpf(){
    return this.cpf;
  }
  public void setNome(String nome){
    this.nome = nome;
  }
  public String getNome(){
    return this.nome;
  }
  public void setDtNascimento(int dia, int mes, int ano){
    this.dtNascimento.setDia(dia);
    this.dtNascimento.setMes(mes);
    this.dtNascimento.setAno(ano);
  }
  public Data getDtNascimento(){
    return this.dtNascimento;
  }
  public void setDtAdmissao(int dia, int mes, int ano){
    this.dtAdmissao.setDia(dia);
    this.dtAdmissao.setMes(mes);
    this.dtAdmissao.setAno(ano);
  }
  public Data getDtAdmissao(){
    return this.dtAdmissao;
  }
  public void setSalario(double salario){
    this.salario = salario;
  }
  public double getSalario(){
    return this.salario;
  }
  public static int getFuncionarios(){
    return funcionarios;
  }
   Funcionario(){
    this.setCpf(0);
    this.setNome("");
    this.setDtNascimento(0, 0, 0);
    this.setDtAdmissao(0, 0, 0);
    this.setSalario(0);
    funcionarios++;
  }
  Funcionario(long cpf, String nome, int diaNas, int mesNas, int anoNas, int diaAdm, int mesAdm, int anoAdm, double salario){
    this.setCpf(cpf);
    this.setNome(nome);
    this.setDtNascimento(diaNas, mesNas, anoNas);
    this.setDtAdmissao(diaAdm, mesAdm, anoAdm);
    this.setSalario(salario);
    funcionarios++;
  }
  public void leFuncionario(){
    boolean erro = true;
    String nome;
    do{
       try{
          Scanner read = new Scanner(System.in);
          System.out.print("\n Digite o cpf: ");
          long cpf = read.nextLong();
          erro = false;
          this.setCpf(cpf);
          }
       catch(InputMismatchException e){
           System.out.print("\nCPF digitado invalido, favor digitar o cpf novamente");
       }
    }while(erro);
    erro = true;
    do{
       try{
          Scanner read = new Scanner(System.in);
          System.out.print("\n Digite o salario: ");
          double salario = read.nextDouble();
          erro = false;
          this.setSalario(salario);
          }
       catch(InputMismatchException e){
           System.out.print("\nsalario digitado invalido, favor digitar o salario novamente");
       }
    }while(erro);
    Scanner read = new Scanner(System.in);
    System.out.print("\n Digite o nome: ");
    nome = read.nextLine();

    
    System.out.print("\nData de nascimento: ");
    this.dtNascimento.leData();
    System.out.print("\nData de admissao: ");
    this.dtAdmissao.leData();
    
    this.setNome(nome);
  }
  public void escreveFuncionario(){
    System.out.println("CPF: "+this.getCpf());
    System.out.println("NOME: "+this.getNome());
    System.out.println("DATA NASCIMENTO: "+this.getDtNascimento().getDia()+"/"+this.getDtNascimento().getMes()+"/"+this.getDtNascimento().getAno());
    System.out.println("DATA ADMISSAO: "+this.getDtAdmissao().getDia()+"/"+this.getDtAdmissao().getMes()+"/"+this.getDtAdmissao().getAno());
    System.out.println("SALARIO: "+this.getSalario());
    System.out.println("");
  }
 public boolean maiorSalario(double salario){
    boolean salarioMaior = true;
    if(salario > this.getSalario())
      salarioMaior = false;
    return salarioMaior;
 } 
 public void pesquisaFuncionario(String nome){
    if(new String(this.getNome()).equals(nome))   escreveFuncionario();
    else System.out.println("Funcionario inexistente");
 }
  public static void ordenarFuncionario(Funcionario[] aF){
    int aux;
    int posMaior;
    for(int j=Funcionario.getFuncionarios(); j>1; j--){
      posMaior=0;
      for(int i=1; i<j; i++){
        if(aF[i].getCpf()>aF[posMaior].getCpf()){
          posMaior=i;
        }
      }
      troca(aF, j-1, posMaior);
    }
  }
  public static void troca(Funcionario[] aF, int pos, int posM){
    Funcionario aux = new Funcionario();
    aux = aF[pos];
    aF[pos] = aF[posM];
    aF[posM] = aux;
    funcionarios--;
  }
  public static void pesquisaFuncionarioCPF(Funcionario[] aF, int min, int max, long cpf){
    ordenarFuncionario(aF);
    int metade = (min + max)/2;
    try{
      if (min > max)
        System.out.println("CPF nao encontrado na lista de funcionarios ");
      else if(aF[metade].getCpf() == cpf)
        aF[metade].escreveFuncionario();
      else if(aF[metade].getCpf() < cpf)
        pesquisaFuncionarioCPF(aF, metade+1, max, cpf);
      else
        pesquisaFuncionarioCPF(aF, min, metade-1, cpf);
    }
    catch(Exception e){
      System.out.println("Funcionario nao encontrado");
    }
  }
}