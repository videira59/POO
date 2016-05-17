import java.util.TreeMap;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Write a description of class Comprador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Comprador extends Utilizador{
    private TreeMap<String,Imovel> favoritos;

    public Comprador (){
      super ("n/a","n/a","n/a","n/a","n/a");
      favoritos = new TreeMap<String,Imovel> ();
    }

    public Comprador (Comprador c){
      super (c);
      this.favoritos = (TreeMap<String,Imovel>)c.getFavoritos();
    }

    public Comprador (String email,String nome,String password,String morada,String dataNascimento,Map<String,Imovel> favoritos){
      super (email,nome,password,morada,dataNascimento);
      this.favoritos = new TreeMap<String,Imovel> (favoritos);
      setFavoritos(favoritos);
    }

    public Map<String,Imovel> getFavoritos (){
      return this.favoritos.entrySet()
                           .stream()
                           .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public void setFavoritos (Map<String,Imovel> favoritos){
      this.favoritos.entrySet()
                    .stream()
                    .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public Comprador clone (){
      return new Comprador (this);
    }

    public boolean equals(Object obj){
      if (obj == this)
        return true;
      if (obj == null || obj.getClass() != this.getClass())
        return false;
      Comprador c = (Comprador) obj;
      return c.getFavoritos().equals(favoritos);
    }

    public String toString (){
      StringBuilder sb = new StringBuilder();
      sb.append("A lista de favoritos é:(").append(favoritos).append(")\n");
      return sb.toString();
    }
}
