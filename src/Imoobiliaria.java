import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
/**
 * Write a description of class Imoobiliaria here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Imoobiliaria{
  /** Utilizador que está registado no momento */
  private Utilizador utilizador;

  /** Map que a cada id do imovel atribui-lhe um imovel*/
  private Map<String,Imovel> imoveis;

  /** Set com todos os utilizadores do programa */
  private Set<Utilizador> utilizadores;

  public Imoobiliaria (){
    utilizador = null;
    imoveis = new TreeMap<String,Imovel> ();
    utilizadores = new TreeSet<Utilizador> ();
  }

  public Imoobiliaria (Imoobiliaria i){
    this.utilizador = i.getUtilizador();
    this.imoveis = i.getImoveis();
    this.utilizadores = i.getUtilizadores().stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  public Imoobiliaria (Utilizador utilizador,Map<String,Imovel> imoveis, TreeSet<Utilizador> utilizadores){
    this.utilizador = utilizador;
    this.imoveis = new TreeSet<Imovel> (imoveis);
    this.utilizadores = utilizadores.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  public Utilizador getUtilizador (){
    return utilizador;
  }

  public void setUtilzador (Utilizador utilizador){
    this.utilizador = utilizador;
  }

  public Map<String,Imovel> getImoveis (){
    return this.imoveis.entrySet()
                        .stream()
                        .collect(toMap(e->e.getKey(),e->e.getValue().clone()));
  }
  public void setImoveis (Map<String,Imovel> imoveis){
    this.imoveis.entrySet()
                .stream()
                .collect(toMap(e->e.getKey(),e->e.getValue().clone()));
  }

  public Set<Utilizador> getUtilizadores (){
    return new TreeSet<Utilizador> (utilizadores);
  }

  public void setUtilizadores (Set<Utilizador> utilizadores){
    this.utilizadores = new TreeSet<Utilizador> (utilizadores);
  }

  public Imoobiliaria cloen (){
    return new Imoobiliaria(this);
  }

  public boolean equals(Object obj){
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    Imoobiliaria imo = (Imoobiliaria) obj;
    return imo.getUtilizador().equals(utilizador)
    && imo.getUtilizadores().equals(utilizadores)
    && imo.getImoveis().equals(imoveis);
  }

  public String toString (){
    StringBuilder sb = new StringBuilder();
    sb.append("O utilizador registado é:").append(utilizador).append("\n");
    sb.append("A lista de utilizadores do programa é:").append(utilizadores).append("\n");
    sb.append("A lista de imoveis do programa é:").append(imoveis).append("\n");
  }
  public static Imoobiliaria initApp (){

  }
  /** Função que adiciona um novo utilizador.

  @param utilizador utilizador a registar*/
  public void registarUtilizador (Utilizador utilizador)
  throws UtilizadorExistenteException{
    if (utilizadores.contains(utilizador)) {
      throw new UtilizadorExistenteException("Utilizador já existente!");
    }
    utilizadores.add(utilizador);
  }

  /** Função que valida o acesso à aplicação.

  @param email email do utilizador
  @param password password do utilizador */
  public void iniciaSessao (String email,String password)
  throws SemAutorizacaoException{
    Utilizador u = this.utilizador.get(email);
    if (u == null)
      throw new SemAutorizacaoException("Utilizador nao existente");
    if (!u.getPassword().equals(password))
      throw new SemAutorizacaoException("A password nao correspondente ao utilizador");

    this.utilizador = this.utilizadores.get(email);
  }

  /** Função que fecha a sessão atualmente iniciada*/
  public void fechaSessao () {
    this.utilizador = null;
  }


/* Funções especificas aos vendedores */

  /** Função que adiciona um novo imovel para venda

  @param im imovel a adicionar*/
  public void registaImovel (Imovel im)
  throws ImovelExisteException, SemAutorizacaoException{
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie Sessão.");
    if (imoveis.containsValue(im))
      throw new ImovelExisteException("Imovel já existente");
    imoveis.add(im);
  }

  /** Função que devolve ao utilizador a lista das 10 ultimas consultas dos imoveis que este tem para venda

  @return Lista das consultas */
  public List<Consulta> getConsulta ()
  throws SemAutorizacaoException{
    if(utilizadores == null)
      throw new SemAutorizacaoException("Inicie Sessão.");

  }

  /** Função que muda o estado de um dado imovel

  @param idImovel id do imovel a mudar o estado
  @param estado Estado para o qual o imovel vai mudar*/
  public void setEstado (String idImovel, String estado)
  throws ImovelInexistenteException,SemAutorizacaoException,EstadoInvalidoException{
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie sessão.");
    if (!imoveis.containsKey(idImovel))
      throw new ImovelInexistenteException("O imóvel não existe no sistema!");


  }

  /** Função que obtem o conjunto dos ids dos n imoveis mais consultados

  @param n numero de ids a serem devolvidos
  @return Conjunto dos ids*/
  public Set<String> getTopImoveis (int n){

  }
/* Funções para todos os utilizadores*/
  /** Função que devolve todos os imoveis de um dado tipo e até um dado preço

  @param classe tipo dos imóveis
  @param preco preço máximo dos imóveis
  @return lista dos imoveis */
  public List<Imovel> getImovel (String classe, int preco){

  }

  /** Função que devolve todos os imoveis habitaveis até um dado preço

  @param preco preço máximo dos imóveis
  @return lista dos imóveis habitaveis */
  public List<Habitavel> getHabitaveis (int preco){

  }

  /** Função que obtem o mapeamento entre todos os imóveis e o seu vendedor

  @return Mapeamento dos imóveis */
  public Map<Imovel,Vendedor> getMapeamentoImoveis (){

  }

  /** Funções para compradores registados */

  /** Função que define um dado imovel como favoritos
  @param idImovel Id do imóvel a ser adicionado aos favoritos  */
  public void setFavoritos(String idImovel)
  throws ImovelInexistenteException, SemAutorizacaoException{
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie sessão.");
    if (!imoveis.contains(idImovel))
      throw new ImovelInexistenteException("Imovel não existe");
  }

  /** Função que devolve um set com os favoritos do utilizador
  @return Set com todos os imoveis favoritos*/
  public Set<Imovel> getFavoritos ()
  throws SemAutorizacaoException{
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie sessão.");
    return this.utilizador.getFavoritos().stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }
}
