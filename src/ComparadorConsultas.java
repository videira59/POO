import java.util.Comparator;
public class ComparadorConsultas implements Comparator<Imovel>{
  public int compare(Imovel imo1,Imovel imo2){
    int a,b;
    a = imo1.getConsultas().size();
    b = imo2.getConsultas().size();
    return a - b;
  }
}
