import java.util.List;
/**
 * Write a description of class LojaHabitavel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LojaHabitavel extends Loja implements Habitavel{
    private String tipo;
    private int quartos;
    private int wcApartamento;
    private int andar;
    private boolean garagem;

    public LojaHabitavel (){
      super();
      tipo = "n/a";
      quartos = wcApartamento =  andar = 0;
      garagem = false;
    }

    public LojaHabitavel (LojaHabitavel lh){
      super (lh);
      this.tipo = lh.getTipo();
      this.quartos = lh.getQuartos();
      this.wcApartamento = lh.getWcApartamento();
      this.andar = lh.getAndar();
      this.garagem = lh.existeGaragem();
    }

    public LojaHabitavel(String tipo,int quartos, int wcApartamento, int porta, int andar, boolean garagem, boolean wc,String tipoNegocio,double areaT,String rua,int precoP,int precoM,List<Consulta> consultas,String estado,String id){
      super (wc,tipoNegocio,porta,areaT,rua,precoP,precoM,consultas,estado,id);
      this.tipo = tipo;
      this.quartos = quartos;
      this.wcApartamento = wcApartamento;
      this.andar = andar;
      this.garagem = garagem;
    }

    public String getTipo (){
      return tipo;
    }

    public void setTipo (String tipo){
      this.tipo = tipo;
    }

    public int getQuartos(){
      return quartos;
    }

    public void setQuartos (int quartos){
      this.quartos = quartos;
    }

    public int getWcApartamento(){
      return wcApartamento;
    }

    public void setWcApartamento (int wcApartamento){
      this.wcApartamento = wcApartamento;
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

    public void setGaragem(boolean garagem){
      this.garagem = garagem;
    }

    public LojaHabitavel clone(){
      return new LojaHabitavel(this);
    }

    public boolean equals(Object obj){
      if (obj == this)
        return true;
      if (obj == null || obj.getClass() != this.getClass())
        return false;
      LojaHabitavel lh = (LojaHabitavel) obj;
      return lh.getTipo().equals(tipo)
      && lh.getQuartos() == quartos
      && lh.getWcApartamento() == wcApartamento
      && lh.getAndar() == andar
      && lh.existeGaragem() == garagem;
    }

    public String toString (){
      StringBuilder sb = new StringBuilder();
      sb.append("Tipo:(").append(tipo).append(")\n");
      sb.append("Número de quartos:(").append(quartos).append(")\n");
      sb.append("Número de casas de banho:(").append(wcApartamento).append(")\n");
      sb.append("Andar do apartamento:(").append(andar).append(")\n");
      if (existeGaragem()) sb.append("Existe garagem?: Sim.\n");
      else sb.append("Existe garagem?: Não.\n");
      return sb.toString();
    }
}
