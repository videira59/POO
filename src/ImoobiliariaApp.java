 import java.io.IOException;
public class ImoobiliariaApp {
  private ImoobiliariaApp () {}
  private static Imoobiliaria imo;
  private static Menu menumain,menuvendedores,menucompradores,menusessao;

  public static void main(String [] args){
    carregarMenus();
    carregarDados();
    imprimeMenuPrincipal();
    try {
      imo.gravar();
    }
    catch(IOException e){
      System.out.println("Não consegui gravar os dados!");
    }
  }

  private static void carregarMenus(){
    String [] principal= {
                          "Iniciar Sessão",
                          "Registar Utilizador",
                          "Procura Imóveis por tipo",
                          "Procurar Imóveis Habitaveis",
                          "Mapeamento Imoveis"
    };
    String [] vendedores = {
                            "Procura Imóveis por tipo",
                            "Procurar Imóveis Habitaveis",
                            "Mapeamento Imoveis",
                            "Registar Imovel",
                            "Obter ultimas 10 consultas",
                            "Imoveis mais consultados",
                            "Alterar estado do imovel",
                            "Terminar Sessão"
    };
    String [] compradores = {
                            "Procura Imóveis por tipo",
                            "Procurar Imóveis Habitaveis",
                            "Mapeamento Imoveis",
                            "Adicionar imovel a favoritos",
                            "Lista de imoveis favoritos"
                            "Terminar Sessão"
    };
    String [] registar = {
                          "Registar Comprador",
                          "Registar Vendedor"
    };
    String [] registarImo = {
                              "Moradia",
                              "Terreno",
                              "Loja",
                              "Loja Habitável",
                              "Apartamento"
    }
    menumain = new Menu(principal);
    menuvendedores = new Menu(compradores);
    menucompradores = new Menu(vendedores);
    menuregistar = new Menu(registar);
    menuregistarImo = new Menu(registarImo);
  }

  private static void imprimeMenuPrincipal() {
    do{
      menumain.executa();
      switch(menumain.getOpcao()){
        case 1: sessao();
                break;
        case 2: regiUti();
                break;
        case 3: procImoTipo();
                break;
        case 4: procImoHab();
                break;
        case 5; mapImo();
      }
    } while (menumain.getOpcao()!= 0);
  }

  private static void imprimeMenuVendedores (){
    do{
      menuvendedores.executa();
      switch(menuvendedores.getOpcao()){
        case 1: procImoTipo();
                break;
        case 2: procImoHab();
                break;
        case 3: mapImo();
                break;
        case 4: regiImo();
                break;
        case 5: lastConsultas();
                break;
        case 6: maisConsultas();
                break;
        case 7: estadoImo();
                break;
        case 8: imo.fechaSessao();
      }
    }while (menuvendedores.getOpcao()!=0);
  }

  private static void imprimeMenuCompradores (){
    do{
      menucompradores.executa();
      switch(menucompradores.getOpcao()){
        case 1: procImoTipo();
                break;
        case 2: procImoHab();
                break;
        case 3: mapImo();
                break;
        case 4: addImoFav();
                break;
        case 5: listFav();
                break;
        case 6: imo.fechaSessao();
      }
    } while (menucompradores.getOpcao() != 0);
  }


  private static void carregarDados(){
    try {
      imo = Imoobiliaria.initApp();
    }
    catch (IOException e){
      imo = new Imoobiliaria();
      System.out.println("Não foi possivel ler os dados!\nErro de leitura!");
    }
    catch (ClassNotFoundException e){
      imo = new Imoobiliaria();
      System.out.println("Não foi possivel ler os dados!\nFicheiro com formato desconhecido!");
    }
    catch(ClassCastException e){
      imo = new Imoobiliaria();
      System.out.println("Não foi possivel ler os dados!\nErro de formato!");
    }
  }
  private static void sessao(){
    Scanner input = new Scanner(System.in);
    String email,password;

    System.out.println("Email: ");
    email = input.nextLine();

    System.out.println("Password: ");
    password = input.nextLine();

    try{
      imo.registarUtilizador(u);
    }
    catch (UtilizadorExistenteException)
      System.out.println(e.getMessage());
    finally
      intput.close();

    switch(imo.getTipoUtilizador()){
      case 1: executaMenuComprador(); break;
      case 2: executaMenuVendedor(); break;
    }
  }

  private static void regiUti(){
    String email,nome,password,morada,data;
    Scanner input = new Scanner(System.in);
    Utilizador u = null;
    menuregistar.executa();
    if(menuregistar.getOpcao()==0){
      System.out.println("Registo Cancelado");
      return;
    }
    System.out.println("Insira o email: ");
    email = input.nextLine();

    System.out.println("Insira o nome: ");
    nome = input.nextLine();

    System.out.println("Insira a password: ");
    password = input.nextLine();

    System.out.println("Insira a morada: ");
    morada = input.nextLine();

    System.out.println("Insira a data de nascimento no formato (dd-MM-yyyy): ");
    data = input.nextLine();

    switch(menuregistar.getOpcao()){
      case 0: input.close(); return;
      case 1: u = new Comprador(); break;
      case 2: u = new Vendedor(); break;
    }

    u.setEmail(email);
    u.setNome(nome);
    u.setPassword(password);
    u.setMorada(morada);
    u.setDataNascimento(data);

    try{
      imo.registarUtilizador(u);
    }
    catch (UtilizadorExistenteException e){
      System.out.println(e.getMessage());
    }
    finally{
      input.close();
    }
  }

  private static void procImoTipo(){
    int preco;
    String tipo;
    Scanner input = new Scanner(System.in);
    ArrayList<Imovel> lista;
    System.out.println("Insira o tipo do Imóvel: ");
    tipo = input.nextLine;

    preco = lerInt("Insira o preço máximo:");
    lista = imo.getImovel(tipo,preco);
    for(Imovel im : lista)
      System.out.println(im.toString());
  }

  private static void procImoHab(){
    int preco;
    ArrayList<Habitavel> lista;
    preco = lerInt("Insira o preço máximo: ");
    lista = imo.getHabitaveis(preco);
    for(Habitavel im: lista)
      System.out.println(im.toString());
  }

  private static void mapImo(){
      Set<Map.Entry<Imovel,Vendedor>> map = imo.getMapeamentoImoveis();

      for (Map.Entry<Imovel,Vendedor> entry:map){
        System.out.println(entry.getValue().toString());
        System.out.println(entry.getKey().toString());
      }
  }

  private static void regiImo(){
    Imovel imovel = null;
    menuregistarImo.executa();
    if (menuregistarImo.getOpcao()==0){
      System.out.println("Registo Cancelado!");
      return;
    }

    switch (menuregistarImo.getOpcao()){
      case 1: addMoradia(); break;
      case 2: addTerreno(); break;
      case 3: addLoja(); break;
      case 4: addLojaHabitavel(); break;
      case 5: addApartamento(); break;
    }

    try {
      imo.registaImovel(imovel);
    }
    catch (ImovelExisteException|SemAutorizacaoException e){
      System.out.println(e.getMessage());
    }
  }

  private static void lastConsultas(){
    System.out.println("Ainda não implementado!!!");
  }

  private static void maisConsultas(){
    System.out.println("Ainda não implementado!!!");
  }

  private static void estadoImo(){
    System.out.println("Ainda não implementado!!!");
  }

  private static void addImoFav(){
    System.out.println("Ainda não implementado!!!");
  }
  private static Imovel addMoradia(){
    Scanner input = new Scanner(System.in);
    double areaT,areaI,areaE;
    String rua,estado,tipo;
    ArrayList<Consulta> consultas = new ArrayList<> ();
    int precoP,precoM,quartos,wc,porta;

    System.out.println("Insira a rua: ");
    rua = input.nextLine();

    do{
      System.out.println("Insira o estado do imovel(Venda/Vendido): ");
      estado = input.nextLine();
    } while (estado.compareTo("Venda")!=0 || estado.compareTo("Vendido") != 0);

    System.out.println("Insira o tipo: ");
    tipo = input.nextLine();

    areaT = lerDouble("Insira a area total da moradia: ");
    areaI = lerDouble("Insira a area de implantação da moradia: ");
    areaE = lerDouble("Insira a area do terreno envolvente: ");
    precoP = lerInt("Insira o preço do Imóvel: ");
    precoM = lerInt("Insira o preço minimo do Imóvel: ");
    quartos = lerInt("Insira o número de quartos: ");
    wc = lerInt("Insira o número de casas de banho: ");
    porta = lerInt("Insira o número da porta:");
    return new Moradia(tipo,areaI,areaE,quartos,wc,porta,areaT,rua,precoP,precoM,consultas,estado);
  }

  private static Imovel addTerreno(){
    Scanner input = new Scanner(System.input);
    double areaT,canalizacao,eletricidade;
    boolean esgotos;
    String rua,estado,tipoConstrucao;
    int precoP,precoM;
    ArrayList<Consulta> consultas = new ArrayList<>();

    System.out.println("Insira a rua: ");
    rua = input.nextLine();

    do{
      System.out.println("Insira o estado do imovel(Venda/Vendido): ");
      estado = input.nextLine();
    } while (estado.compareTo("Venda")!=0 || estado.compareTo("Vendido") != 0);

    System.out.println("Insira o tipo de construção possivel: ");
    tipoConstrucao = input.nextLine();

    areaT = lerDouble("Insira a área do terreno: ");
    canalizacao = lerDouble("Insira o diametro das canalizações(em milímetros): ");
    eletricidade = lerDouble("Insira os kWh máximos suportados pela rede elétrica: ");
    esgotos = lerBoolean("O terreno tem acesso à rede de esgotos?(s/n): ");
    precoP = lerInt("Insira o preço do terreno: ");
    precoM = lerInt("Insira o preço minimo aceite pelo terreno: ");
    return new Terreno(tipoConstrucao,canalizacao,eletricidade,esgotos,areaT,rua,pprecoP,precoM,consultas,estado);
  }

  private static Imovel addApartamento(){
    Scanner input = new Scanner(System.in);
    double areaT;
    String tipo,rua,estado;
    int precoP,precoM,quartos,wc,porta,andar;
    boolean garagem;
    ArrayList<Consulta> consultas = new ArrayList<>();

    System.out.println ("Insira a rua:")
    rua = input.nextLine();

    do{
      System.out.println("Insira o estado do imovel(Venda/Vendido): ");
      estado = input.nextLine();
    } while (estado.compareTo("Venda")!=0 || estado.compareTo("Vendido") != 0);

    System.out.println("Insira o tipo do apartamento(Simples/Duplex/Triplex): ");
    tipo = input.nextLine();

    areaT = lerDouble("Insira a área total do apartamento: ");
    precoP = lerInt("Insira o preço do apartamento: ");
    precoM = lerInt("Insira o preço minimo do apartamento: ");
    quartos = lerInt("Insira o número de quartos: ");
    wc = lerInt("Insira o número de casas de banho: ");
    porta = lerInt("Insira o número da porta: ");
    andar = lerInt("Insira o andar do apartamento: ");
    garagem = lerBoolean("O apartamento tem garagem?(s/n): ");
    return new Apartamento(tipo,quartos,wc,porta,andar,garagem,areaT,rua,precoP,precoM,consultas,estado);
  }

  private static Imovel addLoja(){
    Scanner input =  new Scanner(System.in);
    double areaT;
    boolean wc;
    int precoP,precoM,porta;
    ArrayList<Consulta> consultas = new ArrayList<>();
    String rua, estado,tipoNegocio;

    System.out.println("Insira a rua: ");
    rua = input.nextLine();

    do{
      System.out.println("Insira o estado do imovel(Venda/Vendido): ");
      estado = input.nextLine();
    } while (estado.compareTo("Venda")!=0 || estado.compareTo("Vendido") != 0);

    System.out.println("Insira o tipo de negócio: ");
    tipoNegocio= input.nextLine();

    areat = lerDouble("Insira a area total da loja: ");
    wc = lerBoolean("A loja tem casa de banho?(s/n): ");
    precoP = lerInt("Insira o preço do imovel: ");
    precoM = lerInt("Insira o preço minimo do imovel: ");
    porta = lerInt("Indique o número da porta: ");
    return new Loja(wc,tipoNegocio,porta,areaT,rua,precoP,precoM,consultas,estado);
  }

  private static Imovel addLojaHabitavel(){
    Scanner input =  new Scanner (System.in);
    double areaT;
    String rua, estado,tipoNegocio,tipo;
    int precoP,precoM,porta,quartos,wcApartamento,andar;
    boolean wc,garagem;
    ArrayList<Consulta> consultas = new ArrayList<>();

    System.out.println("Insira a rua: ");
    rua = input.nextLine();

    do{
      System.out.println("Insira o estado do imovel(Venda/Vendido): ");
      estado = input.nextLine();
    } while (estado.compareTo("Venda")!=0 || estado.compareTo("Vendido") != 0);

    System.out.println("Insira o tipo de negócio da loja: ");
    tipoNegocio = nextLine();

    System.out.println("Insira o tipo da habitação (Simples/Duplex/Triplex): ");
  }

  private static void listFav(){
    System.out.println("Ainda não implementado!!!");
  }

  private static double lerDouble(String msg){
    Scanner input = new Scanner(System.in);
    double r = 0;
    System.out.println(msg);
    try{
      r= input.nextDouble();
    }
    catch(InputMismatchException e){
      System.out.println("Formato errado!");
      r = lerDouble(msg);
    }
    finally {
      input.close();
    }
    return r;
  }

  private static int lerInt(String msg){
    Scanner input = new Scanner(System.in);
    int r = 0;

    System.out.println(msg);
    try{
      r = input.nextInt();
    }
    catch (InputMismatchException e){
      System.out.println("Formato errado!");
      r = lerInt(msg);
    }
    finally{
      input.close();
    }
    return r;
  }

  private static boolean lerBoolean(String msg){
    Scanner input = new Scanner(Sytem.in);
    boolean r = true;

    s = input.nextLine();
    if(s.charAt(0) == 'n')
      r = false;
    input.close();
    return r;
  }
}
