import java.io.Serializable;
/**
 * Write a description of class Utilizadores here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Utilizador implements Serializable{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private String dataNascimento;

    public Utilizador (){
        email = "n/a";
        nome = "n/a";
        password = "n/a";
        morada = "n/a";
        dataNascimento = "n/a";
    }

    public Utilizador (String email,String nome,String password,String morada,String dataNascimento){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }

    public Utilizador (Utilizador a){
        this.email = a.getEmail();
        this.nome = a.getNome();
        this.password = a.getPassword();
        this.morada = a.getMorada();
        this.dataNascimento = a.getDataNascimento();
    }

    public String getEmail (){
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getNome (){
        return nome;
    }

    public void setNome (String nome){
        this.nome = nome;
    }

    public String getPassword (){
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public String getMorada (){
        return morada;
    }

    public void setMorada (String morada){
        this.morada = morada;
    }

    public String getDataNascimento (){
        return dataNascimento;
    }

    public void setDataNascimento (String dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public abstract Utilizador clone();

    public boolean equals(Object obj){
      if (obj == this)
        return true;
      if (obj == null || obj.getClass() != this.getClass())
        return false;
      Utilizador u = (Utilizador) obj;
      return u.getEmail().equals(email)
      && u.getNome().equals(nome)
      && u.getPassword().equals(password)
      && u.getMorada().equals(morada)
      && u.getDataNascimento().equals(dataNascimento);
    }

    public String toString (){
      StringBuilder sb = new StringBuilder ();
      sb.append("Email:").append(email).append("\n");
      sb.append("Nome:").append(nome).append("\n");
      sb.append("Password:").append(password).append("\n");
      sb.append("Morada:").append(morada).append("\n");
      sb.append("Data de nascimento:").append(dataNascimento).append("\n");
      return sb.toString();
    }
}
