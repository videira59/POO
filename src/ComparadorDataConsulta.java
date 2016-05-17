import java.util.Comparator;
public class ComparadorDataConsulta implements Comparator<Consulta>{
  public int compare(Consulta a1,Consulta b1){
    return (int) (a1.getData().compareTo(b1.getData()));
  }
}
