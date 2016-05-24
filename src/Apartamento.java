import java.util.List;
/**
 * Write a description of class Apartamento here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Apartamento extends Imovel implements Habitavel{

   private String tipo;
   private int quartos;
   private int wc;
   private int porta;
   private int andar;
   private boolean garagem;

   public Apartamento (){
	   super ();
	   tipo = "n/a";
	   quartos = 0;
	   wc = 0;
	   porta = 0;
	   andar = 0;
	   garagem = false;
   }

   public Apartamento (String tipo,int quartos,int wc,int porta,int andar,boolean garagem, double areaT,String rua,int precoP,int precoM,List<Consulta> consultas,String estado,String id){
	   super (areaT,rua,precoP,precoM,consultas,estado,id);
	   this.tipo = tipo;
	   this.quartos = quartos;
	   this.wc = wc;
	   this.porta = porta;
	   this.andar = andar;
	   this.garagem = garagem;
   }

   public Apartamento (Apartamento a){
     super (a);
	   this.tipo = a.getTipo();
	   this.quartos = a.getQuartos();
	   this.wc = a.getWC();
	   this.porta = a.getPorta();
	   this.andar = a.getAndar();
	   this.garagem = a.existeGaragem();
   }

   public String getTipo (){
	   return tipo;
   }

   public void setTipo (String tipo){
     this.tipo = tipo;
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

   public int getAndar (){
	   return andar;
   }

   public void setAndar (int andar){
     this.andar = andar;
   }

   public boolean existeGaragem (){
	   return garagem;
   }

   public void setGaragem (boolean garagem){
     this.garagem = garagem;
   }

   public Apartamento clone (){
     return new Apartamento (this);
   }

   public boolean equals (Object obj){
     if (obj == this){
       return true;
     }
     if (obj == null || obj.getClass () != this.getClass()){
       return false;
     }
     Apartamento a = (Apartamento) obj;
     return a.getTipo().equals(tipo)
     && a.existeGaragem() == garagem
     && a.getQuartos() == quartos
     && a.getWC() == wc
     && a.getPorta() == porta
     && a.getAndar() == andar;
   }

   public String toString (){
     StringBuilder sb = new StringBuilder();
     sb.append("Tipo:(").append(tipo).append(")\n");
     sb.append("Número de quartos:(").append(quartos).append(")\n");
     sb.append("Número de casas de banho:(").append(wc).append(")\n");
     sb.append("Número da porta:(").append(porta).append(")\n");
     sb.append("Andar do apartamento:(").append(andar).append(")\n");
     if (existeGaragem()) sb.append("Existe garagem?: Sim.\n");
     else sb.append("Existe garagem?: Não.\n");
     return sb.toString();
   }
}
