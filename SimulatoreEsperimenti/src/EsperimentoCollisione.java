public class EsperimentoCollisione extends Esperimento {
    String nome;
    double energia;
    int numeroCollisioni;

    public EsperimentoCollisione(String nome, int energia, int numeroCollisioni) throws DatiEsperimentoNonValidiException{
        super(nome, energia);
        if(numeroCollisioni < 0){
            throw new DatiEsperimentoNonValidiException("Numero collisioni non valido");
        }
        this.numeroCollisioni = numeroCollisioni;
    }

    public String descrizione(){
        StringBuilder descrizione = new StringBuilder();
        descrizione.append("Esperimento di collisione ");
        descrizione.append(nome);
        descrizione.append(", Energia: ");
        descrizione.append(energia);
        descrizione.append("TeV, Numero collisioni: ");
        descrizione.append(numeroCollisioni);

        return descrizione.toString();
    }

    public void setNumeroCollisioni(int numeroCollisioni){
        this.numeroCollisioni = numeroCollisioni;
    }
    public int getNumeroCollisioni(){
        return this.numeroCollisioni;
    }
}
