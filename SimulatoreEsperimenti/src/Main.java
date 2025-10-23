import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        EsperimentoCollisione e1 = new EsperimentoCollisione("e1", 200, 10);
        EsperimentoCollisione e2 = new EsperimentoCollisione("e2", 200, 10);

        EsperimentoRilevamento e3 = new EsperimentoRilevamento("e3", 100, "fotone");
        EsperimentoRilevamento e4 = new EsperimentoRilevamento("e4", 500, "quark top");

        EsperimentoSimulazione e5 = new EsperimentoSimulazione("e5", 600, 2024);
        EsperimentoSimulazione e6 = new EsperimentoSimulazione("e6", 450, 2025);

        ArchivioEsperimenti archivioEsperimenti = new ArchivioEsperimenti();
        archivioEsperimenti.aggiungiScoperta(e1);
        archivioEsperimenti.aggiungiScoperta(e2);
        archivioEsperimenti.aggiungiScoperta(e3);
        archivioEsperimenti.aggiungiScoperta(e4);
        archivioEsperimenti.aggiungiScoperta(e5);
        archivioEsperimenti.aggiungiScoperta(e6);

        try{
            archivioEsperimenti.salvaSuFile();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

        MappaScoperte mappaScoperte = new MappaScoperte();
        mappaScoperte.aggiungiScoperta("Bosone di Higgs", new EsperimentoSimulazione("Bosone di Higgs", 500, 2018));
        mappaScoperte.aggiungiScoperta("Antimateria", new EsperimentoSimulazione("Antimateria", 1000, 2016));

        try{
            archivioEsperimenti.caricaDaFile();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

        //iterator sull'array letto da file
        Iterator<Esperimento> iterator = archivioEsperimenti.iterator();
        while(iterator.hasNext()){
            Esperimento esperimento = iterator.next();
            esperimento.descrizione();
        }
    }
}