import java.io.*;
import java.util.*;

public class ArchivioEsperimenti implements Serializable {
    List<Esperimento> lista;

    public ArchivioEsperimenti(){
        this.lista = new ArrayList<>();
    }

    public void aggiungiScoperta(Esperimento obj){
        lista.add(obj);
    }

    public void rimuoviEsperimento(int i){
        lista.remove(i);
    }

    public int dimensione(){
        return lista.size();
    }

    public Iterator<Esperimento> iterator(){
        return lista.iterator();
    }


    public void caricaDaFile() throws IOException, ClassNotFoundException{
        //try with resources che permette di chiudere in un modo automatico una risorsa
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("archivio.txt"))){
            this.lista = (ArrayList<Esperimento>) in.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void salvaSuFile() throws IOException{
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivio.txt"))){
            out.writeObject(this.lista);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Esperimento> ricercaEsperimentoDaEnergia(int energia) throws DatiEsperimentoNonValidiException{
        int max = 0, min = 0;
        ArrayList<Esperimento> listaRicerca = new ArrayList<>();
        Iterator<Esperimento> it = lista.iterator();

        if(energia > 0) {
            max = energia + (energia * 10) / 100;
            min =  energia - (energia * 10) / 100;
            if(min < 0){
                throw new DatiEsperimentoNonValidiException("Energia inserita non valida");
            }
        }

        while(it.hasNext()){
            Esperimento obj = it.next();
            if(obj.getEnergia() < min && obj.getEnergia() < max){
                listaRicerca.add(obj);
            }

        }

        return listaRicerca;
    }



}
