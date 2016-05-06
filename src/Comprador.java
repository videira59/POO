import java.util.TreeSet;
import java.util.Set;
/**
 * Write a description of class Comprador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public  class Comprador extends Utilizador{
    private TreeSet<Imovel> favoritos;

    public Comprador (){
      super ("n/a","n/a","n/a","n/a","n/a");
      favoritos = new TreeSet<Imovel> ();
    }

    public Comprador (Comprador c){
      super (c);
      this.favoritos = c.getFavoritos.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
    }

    public Comprador (String email,String nome,String password,String morada,String dataNascimento,Set<Imovel> favoritos){
      super (email,nome,password,morada,dataNascimento);
      this.favoritos = favoritos.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
    }

    public Set<Imovel> getFavoritos (){
      return favoritos.stream().map(i->{return i.clone ();}).collect(Collectors.toSet());
    }

    public void setFavoritos (Set<Imovel> favoritos){
      this.favoritos = favoritos.stream().map(i-> {return i.clone();}).collect(Collectors.toSet());
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
    }
}
