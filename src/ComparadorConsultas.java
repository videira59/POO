import java.util.Comparator;
import java.io.Serializable;

public class ComparadorConsultas implements Comparator<Consulta>,Serializable{
  public int compare(Consulta a,Consulta b){
    return a.getData().compareTo(b.getData());
  }
}
