import java.util.Comparator;
import java.io.Serializable;
public class ComparadorConsultasImovel implements Comparator<Imovel>,Serializable{
  public int compare(Imovel imo1,Imovel imo2){
    int a,b;
    a = imo1.getConsultas().size();
    b = imo2.getConsultas().size();
    return a - b;
  }
}
