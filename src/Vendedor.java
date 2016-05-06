import java.util.TreeSet;
import java.util.Set;
/**
 * Write a description of class Vendedor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vendedor extends Utilizador
{
  private Set<Imovel> historico;
  private Set<Imovel> portfolio;

  public Vendedor (){
    super("n/a","n/a","n/a","n/a","n/a");
    historico = new TreeSet<Imovel> ();
    portfolio = new TreeSet<Imovel> ();
  }

  public Vendedor (String email,String nome,String password,String morada,String data,Set<Imovel> historico,Set<Imovel> portfolio){
    super(email,nome,password,morada,data);
    this.historico = historico.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
    this.portfolio = portfolio.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  public Vendedor (Vendedor v){
    super(v);
    this.historico = v.getHistorico.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
    this.portfolio = v.getPortfolio.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  public Set<Imovel> getHistorico (){
    return historico.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  public void setHistorico (Set<Imovel> historico){
    this.historico = historico.stream().map(i-> {return i.clone();}).collect(Collectors.toSet());
  }

  public Set<Imovel> getPortfolio(){
    return portfolio.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  public void setPortfolio (Set<Imovel> portfolio){
    this.portfolio = portfolio.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  public Vendedor clone (){
    return new Vendedor(this);
  }

  public boolean equals(Object obj){
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    Vendedor v = (Vendedor) obj;
    return v.getPortfolio().equals(portfolio)
    && v.getHistorico().equals(historico);
  }

  public String toString (){
    StringBuilder sb = new StringBuilder();
    sb.append("O portfolio de imoveis é:").append(portfolio).append(")\n");
    sb.append("O historico de vendas é:").append(historico).append(")\n");
  }
}
