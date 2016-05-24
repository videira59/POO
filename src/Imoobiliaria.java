import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.io.Serializable;
/**
 * Write a description of class Imoobiliaria here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Imoobiliaria implements Serializable{
  /** Utilizador que está registado no momento */
  private Utilizador utilizador;

  /** Map que a cada id do imovel atribui-lhe um imovel*/
  private Map<String,Imovel> imoveis;

  /** Set com todos os utilizadores do programa */
  private Map<String,Utilizador> utilizadores;

  public Imoobiliaria (){
    utilizador = null;
    imoveis = new TreeMap<String,Imovel>(new ComparadorIdImovel());
    utilizadores = new TreeMap<String,Utilizador>();
  }

  public Imoobiliaria (Imoobiliaria imo){
    this.utilizador = imo.getUtilizador();
    this.imoveis = imo.getImoveis();
    this.utilizadores = imo.getUtilizadores();
  }

  public Imoobiliaria (Utilizador utilizador,Map<String,Imovel> imoveis, Map<String,Utilizador> utilizadores){
    this.utilizador = utilizador;
    this.imoveis = new TreeMap<String,Imovel> (imoveis);
    this.utilizadores = new TreeMap<String,Utilizador> (utilizadores);
    setImoveis(imoveis);
    setUtilizadores(utilizadores);
  }

  public int getTipoUtilizador (){
    if (utilizador instanceof Comprador)
      return 1;
    if (utilizador instanceof Vendedor)
      return 2;
    return 0;
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

  public Map<String,Utilizador> getUtilizadores (){
    return this.utilizadores.entrySet()
                            .stream()
                            .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
  }

  public void setUtilizadores (Map<String,Utilizador> utilizadores){
    this.utilizadores.entrySet()
                     .stream()
                     .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
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
    return sb.toString();
  }
  public static Imoobiliaria initApp () throws IOException, ClassNotFoundException{
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("imoobiliaria.data"));
    Imoobiliaria im = (Imoobiliaria) ois.readObject();
    ois.close();
    return im;
  }
  /** Função que adiciona um novo utilizador.

  @param utilizador utilizador a registar*/
  public void registarUtilizador (Utilizador utilizador)
  throws UtilizadorExistenteException{
    String email = utilizador.getEmail();
    if (utilizadores.containsKey(email)) {
      throw new UtilizadorExistenteException("Utilizador já existente!");
    }
    utilizadores.put(email,utilizador);
  }

  /** Função que valida o acesso à aplicação.

  @param email email do utilizador
  @param password password do utilizador */
  public void iniciaSessao (String email,String password)
  throws SemAutorizacaoException{
    Utilizador u = this.utilizadores.get(email);
    if (u == null)
      throw new SemAutorizacaoException("Utilizador nao existente!");
    if (!u.getPassword().equals(password))
      throw new SemAutorizacaoException("Password incorreta!");
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
    idImovel = im.getId();
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie Sessão!");
    if (!(utilizador instanceof Vendedor))
      throw new SemAutorizacaoException("Só possivel quando iniciado como Vendedor!");
    if (imoveis.containsKey(idImovel))
      throw new ImovelExisteException("Imovel já existente!");

    Vendedor v = (Vendedor) utilizador;
    imoveis.put(idImovel,im);
    v.getPortfolio().put(idImovel,im);
  }

  /** Função que devolve ao utilizador a lista das 10 ultimas consultas dos imoveis que este tem para venda

  @return Lista das consultas */
  public List<Consulta> getConsultas ()
  throws SemAutorizacaoException{
    Vendedor v = (Vendedor) utilizador;
    TreeSet<Consulta> aux = new TreeSet<Consulta>(new ComparadorConsultas());
    ArrayList<Consulta> lista = new ArrayList<Consulta>();
    if(utilizadores == null)
      throw new SemAutorizacaoException("Inicie Sessão.");
    if (!(utilizador instanceof Vendedor))
      throw new SemAutorizacaoException("Só possivel quando iniciado como Vendedor!");

    for (Map.Entry<String,Imovel> entry: v.getPortfolio().entrySet()){
      aux.addAll(entry.getValue().getConsultas());
    }
    Iterator itr = aux.iterator();
    for (int i = 0;itr.hasNext() && i<10;i++){
      lista.add((Consulta) itr.next());
    }
    return lista;
  }

  /** Função que muda o estado de um dado imovel

  @param idImovel id do imovel a mudar o estado
  @param estado Estado para o qual o imovel vai mudar*/
  public void setEstado (String idImovel, String estado)
  throws ImovelInexistenteException,SemAutorizacaoException,EstadoInvalidoException{
    Imovel i = imoveis.get(idImovel);
    String estadoImo = i.getEstado();
    Vendedor v = (Vendedor) utilizador;
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie sessão.");
    if (!(utilizador instanceof Vendedor))
      throw new SemAutorizacaoException("Só possivel quando iniciado como Vendedor!");
    if (i == null)
      throw new ImovelInexistenteException("O imóvel não existe no sistema!");
    if ((estado.compareTo("Venda")) != 0)
      if ((estado.compareTo("Vendido")) != 0)
        throw new EstadoInvalidoException("O estado introduzido não é válido.");
    if (estadoImo.compareTo(estado) == 0)
      if (estado.compareTo("Venda") == 0){
        i.setEstado(estado);
        v.getPortfolio().remove(idImovel);
        v.getHistorico().put(idImovel,i);
      }
      else {
        i.setEstado(estado);
        v.getHistorico().remove(idImovel);
        v.getPortfolio().put(idImovel,i);
      }

    // mudar para alterar o imovel de local se alterar o estado

  }

  /** Função que obtem o conjunto dos ids dos n imoveis mais consultados

  @param n numero de ids a serem devolvidos
  @return Conjunto dos ids*/
  public Set<String> getTopImoveis (int n){
    int i;
    Vendedor v = (Vendedor) utilizador;
    TreeSet<String> lista = new TreeSet<String>();
    TreeMap<String,Imovel> imoveisUti = new TreeMap<String,Imovel>(v.getPortfolio());
    TreeMap<Imovel,String> ord = new TreeMap<Imovel,String>(new ComparadorConsultasImovel());
    String id,idImovel;
    for(Map.Entry<String,Imovel> entry: imoveisUti.entrySet()){
      ord.put(entry.getValue(),entry.getKey());
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
    ArrayList<Imovel> lista = new ArrayList<Imovel>() ;
    Consulta consulta = new Consulta(utilizador.getEmail(),new GregorianCalendar());
    for (Map.Entry<String,Imovel> entry: imoveis.entrySet()){
      if (entry.getValue().getClass().getSimpleName().equals(classe) && entry.getValue().getPrecoP() >= preco){
        entry.getValue().getConsultas().add(consulta);
        lista.add(entry.getValue());
      }
    }
    return lista.stream().map(i->{return i.clone();}).collect(Collectors.toList());
  }

  /** Função que devolve todos os imoveis habitaveis até um dado preço

  @param preco preço máximo dos imóveis
  @return lista dos imóveis habitaveis*/
  public List<Habitavel> getHabitaveis (int preco){
    ArrayList<Habitavel> lista = new ArrayList<Habitavel>();
    Consulta consulta = new Consulta(utilizador.getEmail(),new GregorianCalendar());
    for (Map.Entry<String,Imovel> entry:imoveis.entrySet()){
      if (((entry.getValue()) instanceof Habitavel) && (entry.getValue().getPrecoP()>= preco)){
        entry.getValue().getConsultas().add(consulta);
        lista.add((Habitavel)entry.getValue());
      }
    }
    return lista.stream().map(i->{return i.clone();}).collect(Collectors.toList());
  }

  /** Função que obtem o mapeamento entre todos os imóveis e o seu vendedor

  @return Mapeamento dos imóveis*/
  public Map<Imovel,Vendedor> getMapeamentoImoveis (){
    // percorrer todos os utilizadores
      // obter o portfolio
          // guardar cada imovel no map
    Vendedor v;
    Consulta consulta = new Consulta (utilizador.getEmail(),new GregorianCalendar());
    TreeMap<Imovel,Vendedor> aux = new TreeMap<Imovel,Vendedor>();
    for (Map.Entry<String,Utilizador> entry:utilizadores.entrySet()){
      if(entry.getValue() instanceof Vendedor){
        v = (Vendedor) entry.getValue();
        for(Map.Entry<String,Imovel> entryy: v.getPortfolio().entrySet()){
          aux.put(entryy.getValue(),v);
          entryy.getValue().getConsultas().add(consulta);
        }
      }
    }
    return aux;
  }

  /** Funções para compradores registados */

  /** Função que define um dado imovel como favoritos
  @param idImovel Id do imóvel a ser adicionado aos favoritos*/
  public void setFavorito(String idImovel)
  throws ImovelInexistenteException, SemAutorizacaoException{
    Comprador c = (Comprador) utilizador;
    Imovel u = imoveis.get(idImovel);
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie sessão.");
    if (!(utilizador instanceof Comprador))
      throw new SemAutorizacaoException("Só possivel quando iniciado como Comprador!");
    if (u == null)
      throw new ImovelInexistenteException("Imovel não existe");
    c.getFavoritos().put(idImovel,u);
  }

  /** Função que devolve um set com os favoritos do utilizador
  @return Set com todos os imoveis favoritos*/
  public Set<Imovel> getFavoritos ()
  throws SemAutorizacaoException{
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie Sessão.");
    if (!(utilizador instanceof Comprador))
      throw new SemAutorizacaoException("Só possivel quando iniciado como Comprador!");
    TreeSet<Imovel> set = new TreeSet<Imovel>();
    Comprador c = (Comprador) utilizador;
    TreeMap<String,Imovel> favoritos = (TreeMap<String,Imovel>) c.getFavoritos();
    Consulta consulta = new Consulta(utilizador.getEmail(),new GregorianCalendar());
    for(Map.Entry<String,Imovel> entry:favoritos.entrySet()){
      entry.getValue().getConsultas().add(consulta);
      set.add(entry.getValue());
    }
    return set;
  }

  public void gravar() throws IOException{
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("imoobiliaria.data"));
    oos.writeObject(this);
    oos.flush();
    oos.close();
  }
}
