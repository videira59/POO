import java.util.List;
/**
 * Write a description of class Moradia here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Moradia extends Imovel implements Habitavel{

     private String tipo;
     private double areaI;
     private double areaE;
     private int quartos;
     private int wc;
     private int porta;

	 public Moradia (){
		 super();
		 tipo = "n/a";
		 areaI = 0;
		 areaE = 0;
		 quartos = 0;
		 wc = 0;
		 porta = 0;
	 }

	 public Moradia (String tipo,double areaI,double areaE,int quartos,int wc, int porta,double areaT,String rua,int precoP,int precoM,List<Consulta> consultas,String estado){
		 super (areaT,rua,precoP,precoM,consultas,estado);
		 this.tipo = tipo;
		 this.areaI = areaI;
		 this.areaE = areaE;
		 this.quartos = quartos;
		 this.wc = wc;
		 this.porta = porta;
	 }

	 public Moradia (Moradia m){
		 this.tipo = m.getTipo();
		 this.areaI = m.getAreaImplantacao();
		 this.areaE = m.getAreaEnvolvente();
		 this.quartos = m.getQuartos();
		 this.wc = m.getWC();
		 this.porta = m.getPorta();
	 }

	 public String getTipo (){
		 return tipo;
	 }

	 public void setTipo (String tipo){
		 this.tipo = tipo;
	 }

	 public double getAreaImplantacao (){
		 return areaI;
	 }

	 public void setAreaImplantacao (double areaI){
		 this.areaI = areaI;
	 }

	 public double getAreaEnvolvente (){
		 return areaE;
	 }

	 public void setAreaEnvolvente (double areaE){
		 this.areaE = areaE;
	 }

	 public int getQuartos (){
		 return quartos;
	 }

	 public void setQuartos (int quartos){
		 this.quartos = quartos;
	 }

	 public int getWC (){
		 return wc;
	 }

	 public void setWC (int wc){
		 this.wc = wc;
	 }

	 public int getPorta (){
		 return porta;
	 }

	 public void setPorta (int porta){
		 this.porta = porta;
	 }

	 public Moradia clone (){
		 return new Moradia (this);
	 }

   public boolean equals (Object obj){
     if (obj == this){
       return true;
     }
     if (obj == null || obj.getClass ()!= this.getClass()){
       return false;
     }
     Moradia m = (Moradia) obj;
     return m.getTipo().equals(tipo)
     && m.getAreaImplantacao() == areaI
     && m.getAreaEnvolvente () == areaE
     && m.getQuartos () == quartos
     && m.getWC () == wc
     && m.getPorta () == porta;
   }

   public String toString (){
     StringBuilder sb = new StringBuilder ();
     sb.append("Tipo:(").append(tipo).append(")\n");
     sb.append("A area de implantação é:(").append(areaI).append(")\n");
     sb.append("A area envolvente é:(").append(areaE).append(")\n");
     sb.append("O número de quartos é:(").append(quartos).append(")\n");
     sb.append("O número de casas de banho é:(").append(wc).append(")\n");
     sb.append("O número da porta é:(").append(porta).append(")\n");
     return sb.toString();
   }
}
