public class ComparadorConsultas implements Comparator<Int>{
  public int compare(Imovel imo1,Imovel imo2){
    int a,b;
    a = imo1.getConsulta().size();
    b = imo2.getConsulta().size();
    return a - b;
  }
}
