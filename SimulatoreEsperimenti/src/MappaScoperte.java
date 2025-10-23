import java.io.*;
import java.util.*;

public class MappaScoperte implements Serializable {
    Map<String, Esperimento> mappa;

    public MappaScoperte() {
        this.mappa = new HashMap<>();
    }

    public void aggiungiScoperta(String chiave, Esperimento valore) throws DatiEsperimentoNonValidiException{
        if(chiave == null || valore == null){
            throw new DatiEsperimentoNonValidiException("Valori inseriti non validi");
        }
        this.mappa.put(chiave, valore);
    }

    public Esperimento rimuoviScoperta(String chiave) throws DatiEsperimentoNonValidiException{
        if(chiave == null){
            throw new DatiEsperimentoNonValidiException("Chiave non validi");
        }

        return this.mappa.remove(chiave);
    }

    public Set<String> elencoChiavi(){
        return this.mappa.keySet();
    }

    public ArrayList<Esperimento> elencoEsperimenti(){
        return new ArrayList<>(this.mappa.values());
    }

    public void caricaDaFile() throws IOException, ClassNotFoundException{
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivio.txt"))){
            this.mappa = (HashMap<String, Esperimento>) in.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void salvaSuFile() throws IOException{
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivio.txt"))){
            out.writeObject(this.mappa);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
