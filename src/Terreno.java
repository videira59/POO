import java.util.List;
/**
 * Write a description of class Terreno here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Terreno extends Imovel
{
    private String tipoConstrucao;
    private double canalizacao;
    private double eletricidade;
    private boolean esgotos;

	public Terreno (){
		super ();
		tipoConstrucao = "n/a";
		canalizacao = 0;
		eletricidade = 0;
		esgotos = false;
	}

	public Terreno (Terreno t){
		super (t);
		this.tipoConstrucao = t.getTipoConstrucao();
		this.canalizacao = t.getCanalizacao();
		this.eletricidade = t.getEletricidade();
		this.esgotos = t.getEsgotos();
	}

	public Terreno (String tipoConstrucao,double canalizacao,double eletricidade,boolean esgotos,double areaT,String rua,int precoP,int precoM,List<Consulta> consultas,String estado){
		super (areaT,rua,precoP,precoM,consultas,estado);
		this.tipoConstrucao = tipoConstrucao;
		this.canalizacao = canalizacao;
		this.eletricidade = eletricidade;
		this.esgotos = esgotos;
	}

	public String getTipoConstrucao (){
		return tipoConstrucao;
	}

	public void setTipoConstrucao (String tipoConstrucao){
		this.tipoConstrucao = tipoConstrucao;
	}

	public double getCanalizacao (){
		return canalizacao;
	}

	public void setCanalizacao (double canalizacao){
		this.canalizacao = canalizacao;
	}

	public double getEletricidade (){
		return eletricidade;
	}

	public void setEletricidade (double eletricidade){
		this.eletricidade = eletricidade;
	}

	public boolean getEsgotos (){
		return esgotos;
	}

	public void setEsgotos (boolean esgotos){
		this.esgotos = esgotos;
	}

	public Terreno clone (){
		return new Terreno (this);
	}

  public boolean equals (Object obj){
    if (obj == this){
      return true;
    }
    if (obj == null || obj.getClass () != this.getClass()){
      return false;
    }
    Terreno t = (Terreno) obj;
    return t.getTipoConstrucao().equals(tipoConstrucao)
    && t.getCanalizacao() == canalizacao
    && t.getEletricidade() == eletricidade
    && t.getEsgotos() == esgotos;
  }

  public String toString (){
    StringBuilder sb = new StringBuilder ();
    sb.append("O tipo da Construção é:(").append(tipoConstrucao).append(")\n");
    sb.append("O diametro da canalização (em mm) é:(").append(canalizacao).append(")\n");
    sb.append("Os kWh máximos suportados pela rede elétrica é:(").append(eletricidade).append(")\n");
    if (getEsgotos()) sb.append("O terreno tem acesso à rede de esgotos\n");
    else sb.append("O terreno não tem acceso à rede de esgotos");
    return sb.toString();
  }
}
