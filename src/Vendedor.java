import java.util.TreeMap;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Write a description of class Vendedor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vendedor extends Utilizador
{
  private Map<String,Imovel> historico;
  private Map<String,Imovel> portfolio;

  public Vendedor (){
    super("n/a","n/a","n/a","n/a","n/a");
    historico = new TreeMap<String,Imovel> ();
    portfolio = new TreeMap<String,Imovel> ();
  }

  public Vendedor (String email,String nome,String password,String morada,String data,Map<String,Imovel> historico,Map<String,Imovel> portfolio){
    super(email,nome,password,morada,data);
    this.historico = new TreeMap<String,Imovel>(historico);
    this.portfolio = new TreeMap<String,Imovel>(portfolio);
    setHistorico(historico);
    setPortfolio(portfolio);
  }

  public Vendedor (Vendedor v){
    super(v);
    this.historico = v.getHistorico();
    this.portfolio = v.getPortfolio();
  }

  public Map<String,Imovel> getHistorico (){
    return this.historico.entrySet()
                         .stream()
                         .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
  }

  public void setHistorico (Map<String,Imovel> historico){
    this.historico.entrySet()
                  .stream()
                  .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
  }

  public Map<String,Imovel> getPortfolio(){
    return this.portfolio.entrySet()
                         .stream()
                         .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
  }

  public void setPortfolio (Map<String,Imovel> portfolio){
    this.portfolio.entrySet()
                         .stream()
                         .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
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
    return sb.toString();
  }
}
