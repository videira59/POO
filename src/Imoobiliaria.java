import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.stream.Collectors;
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
    imoveis = new TreeMap<String,Imovel>(new ComparadorIdImovel());
    utilizadores = new TreeSet<Utilizador>();
  }

  public Imoobiliaria (Imoobiliaria imo){
    this.utilizador = imo.getUtilizador();
    this.imoveis = imo.getImoveis();
    this.utilizadores = imo.getUtilizadores().stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  public Imoobiliaria (Utilizador utilizador,Map<String,Imovel> imoveis, Set<Utilizador> utilizadores){
    this.utilizador = utilizador;
    this.imoveis = new TreeMap<String,Imovel> (imoveis);
    this.utilizadores = utilizadores.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
    setImoveis(imoveis);
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
                        .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
  }
  public void setImoveis (Map<String,Imovel> imoveis){
    this.imoveis.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
  }

  public Set<Utilizador> getUtilizadores (){
    return new TreeSet<Utilizador> (utilizadores);
  }

  public void setUtilizadores (Set<Utilizador> utilizadores){
    this.utilizadores = new TreeSet<Utilizador> (utilizadores);
  }

  public Imoobiliaria clone (){
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
    Utilizador u = this.utilizadores.get(email);
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
    int tamanho;
    String idImovel;
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie Sessão.");
    if (imoveis.containsValue(im))
      throw new ImovelExisteException("Imovel já existente");
    tamanho = imoveis.size();
    tamanho ++;
    idImovel = Integer.toString(tamanho);
    imoveis.put(idImovel,im); // add o comparador
  }

  /** Função que devolve ao utilizador a lista das 10 ultimas consultas dos imoveis que este tem para venda

  @return Lista das consultas */
  public List<Consulta> getConsulta ()
  throws SemAutorizacaoException{
    TreeMap<String,Imovel> imoUtilizador = utilizador.getPortfolio();
    ArrayList<Consulta> lista = new ArrayList<Consulta> (new ComparadorDataConsulta());
    if(utilizadores == null)
      throw new SemAutorizacaoException("Inicie Sessão.");
    for (Map.Entry<String,Imovel> entry: imoUtilizador.entrySet()){
      lista.addAll(entry.getValue().consultas);
    }

      // add todas as consultas a uma lista
    // guardar apenas 10 primeiras
    return lista;
  }

  /** Função que muda o estado de um dado imovel

  @param idImovel id do imovel a mudar o estado
  @param estado Estado para o qual o imovel vai mudar*/
  public void setEstado (String idImovel, String estado)
  throws ImovelInexistenteException,SemAutorizacaoException,EstadoInvalidoException{
    Imovel i = imoveis.get(idImovel);
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie sessão.");
    if (i == null)
      throw new ImovelInexistenteException("O imóvel não existe no sistema!");
    if (!estado.compareTo("Venda") || !estado.compareTo("Vendido"))
      throw new EstadoInvalidoException("O estado introduzido não é válido.");
    i.setEstado(estado);
  }

  /** Função que obtem o conjunto dos ids dos n imoveis mais consultados

  @param n numero de ids a serem devolvidos
  @return Conjunto dos ids*/
  public Set<String> getTopImoveis (int n){
    int i;
    TreeSet<String> lista = new TreeSet<String>();
    TreeMap<String,Imovel> imoveisUti = new utilizador.getPortfolio();
    TreeMap<Imovel,String> ord = new TreeMap<Imovel,String>(new ComparadorConsultas());
    String id,idImovel;
    for(Map.Entry<String,Imovel> entry: imoveisUti.entrySet()){
      ord.add(entry.getValue(),entry.getKey());
    }
    for(i=0;i<n;i++)
      for(Map.Entry<Imovel,String> entry:ord.entrySet())
        lista.add(entry.getValue());
    return new TreeSet<String>(lista);
    }

/* Funções para todos os utilizadores*/
  /** Função que devolve todos os imoveis de um dado tipo e até um dado preço

  @param classe tipo dos imóveis
  @param preco preço máximo dos imóveis
  @return lista dos imoveis */
  public List<Imovel> getImovel (String classe, int preco){
    Imovel imovel = new Imovel();
    ArrayList<Imovel> lista = new ArrayList<Imovel>() ;
    Consulta consulta = new Consulta(utilizador.getEmail(),new GregorianCalendar());
    for (Map.Entry<String,Imovel> entry: imoveis.entrySet()){
      if (entry.getValue().getClass().getSimpleName().equals(classe) && entry.getValue().getPrecoP() >= preco){
        entry.getValue().consultas.add(consulta);
        lista.add(entry.getValue());
      }
    }
    return lista.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  /** Função que devolve todos os imoveis habitaveis até um dado preço

  @param preco preço máximo dos imóveis
  @return lista dos imóveis habitaveis */
  public List<Habitavel> getHabitaveis (int preco){
    ArrayList<Habitavel> lista = new ArrayList<Habitavel>();
    Consulta consulta = new Consulta(utilizador.getEmail(),new GregorianCalendar());
    for (Map.Entry<String,Imovel> entry:imoveis.entrySet()){
      if (entry.getValue() instanceof Habitavel)
        entry.getValue().consultas.add(consulta);
        lista.add(entry.getValue());
    }
    return lista.stream().map(i->{return i.clone();}).collect(Collectors.toSet());
  }

  /** Função que obtem o mapeamento entre todos os imóveis e o seu vendedor

  @return Mapeamento dos imóveis */
  public Map<Imovel,Vendedor> getMapeamentoImoveis (){
    // percorrer todos os utilizadores
      // obter o portfolio
  }

  /** Funções para compradores registados */

  /** Função que define um dado imovel como favoritos
  @param idImovel Id do imóvel a ser adicionado aos favoritos  */
  public void setFavoritos(String idImovel)
  throws ImovelInexistenteException, SemAutorizacaoException{
    Imovel u = imoveis.get(idImovel);
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie sessão.");
    if (u == null)
      throw new ImovelInexistenteException("Imovel não existe");
    utilizador.favoritos.add(imovel);
  }

  /** Função que devolve um set com os favoritos do utilizador
  @return Set com todos os imoveis favoritos*/
  public Set<Imovel> getFavoritos ()
  throws SemAutorizacaoException{
    TreeSet<Imovel> set = new TreeSet<Imovel>();
    TreeMap<String,Imovel> favoritos = utilizador.getFavoritos();
    Consulta consulta = new Consulta(utilizador.getEmail(),new GregorianCalendar());
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie sessão.");
    for(Map.Entry<String,Imovel> entry:favoritos.entrySet()){
      entry.getValue().consultas.add(consulta);
      set.add(entry.getValue());
    }
    return set;
  }
}
