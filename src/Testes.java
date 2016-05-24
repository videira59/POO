

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import java.util.ArrayList;

/**
 * The test class Testes.
 *
 * Ã‰ necessÃ¡rio completar os teste, colocando os parÃ¢metros nos construtores.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Testes
{
    private Imoobiliaria imo;
    private Vendedor v;
    private Terreno t;

    /**
     * Teste principal
     */
    @Test
    public void mainTest() {
        imo = new Imoobiliaria();
        try {
            imo.iniciaSessao("",null);
			fail();
        } catch(SemAutorizacaoException e) {

        } catch(Exception e) {
            fail();
        }

        try {
            v = new Vendedor();  // Preencher parÃ¢metros do construtor
            imo.registarUtilizador(v);
        } catch(Exception e) {
            fail();
        }

        String email = v.getEmail();
        String password = v.getPassword();

        try {
            imo.iniciaSessao(email, password);
        } catch(Exception e) {
            fail();
        }
        ArrayList<Consulta> test = new ArrayList<>();
        t = new Terreno("Moradias",300,200,true,100,"Rua Engenheiro José Sócrates",10000,5000,test,"Venda","2");  // Preencher parÃ¢metros do construtor
        try {
            imo.registaImovel(t);
        } catch (Exception e) {
            fail();
        }

        int s = imo.getImovel("Terreno", Integer.MAX_VALUE).size();
        assertTrue(s>0);
        Set<String> ids = imo.getTopImoveis(0);
        assertTrue(ids.contains(t.getId()));
        assertTrue(imo.getMapeamentoImoveis().keySet().contains(t));
        try {
            assertTrue(imo.getConsultas().size()>0);
        } catch(Exception e) {
            fail();
        }

        imo.fechaSessao();
        Comprador c = new Comprador();  // Preencher parÃ¢metros do construtor
        try {
            imo.registarUtilizador(c);
        } catch(Exception e) {
            fail();
        }
        email = c.getEmail();
        password = c.getPassword();
        try {
            imo.iniciaSessao(email, password);
            imo.setFavorito(t.getId());
            assertTrue(imo.getFavoritos().contains(t));
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
