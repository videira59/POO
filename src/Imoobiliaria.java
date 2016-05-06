import java.util.TreeSet;
/**
 * Write a description of class Imoobiliaria here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Imoobiliaria{
  /** Utilizador que está registado no momento */
  private Utilizador utilizador;

  /** Set com todos os imoveis do programa */
  private Set<Imovel> imoveis;

  /** Set com todos os utilizadores do programa */
  private Set<Utilizador> utilizadores;

  public Imoobiliaria (){
    utilizador = null;
    imoveis = new TreeSet <> ();
    utilizadores = new TreeSet <> ();
  }

  public Imoobiliaria (Imoobiliaria i){
    this.utilizador = i.getUtilizador();
    this.imoveis = new TreeSet<Imovel> (i.getImoveis());
    this.utilizadores = new TreeSet<Utilizador> (i.getUtilizadores());
  }

  public Imoobiliaria (Utilizador utilizador,TreeSet<Imoveis> imoveis, TreeSet<Utilizador> utilizadores){
    this.utilizador = utilizador;
    this.imoveis = new TreeSet<Imovel> (imoveis);
    this.utilizadores = new TreeSet<Imovel> (utilizadores);
  }

  public Utilizador getUtilizador (){
    return utilizador;
  }

  public void setUtilzador (Utilizador utilizador){
    this.utilizador = utilizador;
  }

  public Set<Imovel> getImoveis (){
    return new TreeSet<Imovel>(imoveis);
  }
  public void setImoveis (Set<Imovel> imoveis){
    this.imoveis = new TreeSet<Imovel>(imoveis);
  }

  public Set<Utilizador> getUtilizadores (){
    return new TreeSet<Utilizador> (utilizadores);
  }

  public void setUtilizadores (Set<Utilizador> utilizadores){
    this.utilizadores = new TreeSet<Utilizador> (utilizadores);
  }

  public static Imoobiliaria initApp (){

  }

  public void registarUtilizador (Utilizador utilizador)
  throws UtilizadorExistenteException{
    if utilizadores.contains(utilizador) {
      throw new UtilizadorExistenteException("Utilizador já existente!");
    }
    utilizadores.add(utilizador);
  }

  public void iniciaSessao (String email,String password)
  throws SemAutorizaçãoException{
    Utilizador u = this.utilizador.get(email);
    if (u == null)
      throw new SemAutorizacaoException("Utilizador nao existente");
    if (!u.getPassword().equals(password))
      throw new SemAutorizacaoException("A password nao correspondente ao utilizador");

    this.utilizador = this.utilizadores.get(email);
  }

  public void fechaSessao () {
    this.utilizador = null;
  }


/* Funções especificas aos vendedores */
  public void registaImovel (Imovel im)
  throws ImovelExisteException, SemAutorizacaoException{
    if (utilizador == null)
      throw new SemAutorizacaoException("Inicie Sessão.");
    if imoveis.contains(im)
      throw new ImovelExisteException("Imovel já existente");
    imoveis.add(im);
  }

  public List<Consulta> getConsulta ()
  throws SemAutorizacaoException{
    if(utilizadores == null)
      throw new SemAutorizacaoException("Inicie Sessão.");

  }

  public void setEstado (String idImovel, String estado)
  throws ImovelInexistenteException,SemAutorizacaoException,EstadoInvalidoException{

  }

  public Set<String> getTopImoveis (int n){

  }
/* Funções para todos os utilizadores*/

  public List<Imovel> getImovel (String classe, int preco){

  }

  public List<Habitavel> getHabitaveis (int preco){

  }

  public Map<Imovel,Vendedor> getMapeamentoImoveis (){

  }

  /** Funções para compradores registados */

  public void setFavoritos(String idImovel)
  throws ImovelInexistenteException, SemAutorizacaoException{

  }

  public Set<Imovel> getFavoritos ()
  throws SemAutorizacaoException{

  }

}
