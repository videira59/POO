import java.util.Comparator;

public class ComparadorIdImovel implements Comparator<String>{
  public int compare (String a,String b){
    return a.compareTo(b);
  }
}
