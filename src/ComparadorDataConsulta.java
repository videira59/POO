import java.util.Comparator;
import java.io.Serializable;
public class ComparadorDataConsulta implements Comparator<Consulta>,Serializable{
  public int compare(Consulta a1,Consulta b1){
    return (int) (a1.getData().compareTo(b1.getData()));
  }
}
