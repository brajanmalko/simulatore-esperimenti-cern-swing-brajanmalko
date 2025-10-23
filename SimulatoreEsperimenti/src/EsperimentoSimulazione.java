public class EsperimentoSimulazione extends Esperimento{
    String nome;
    double energia;
    int annoSimulazione;

    public EsperimentoSimulazione(String nome, double energia, int annoSimulazione) throws DatiEsperimentoNonValidiException{
        super(nome, energia);
        if(annoSimulazione < 0 || annoSimulazione > 2025){
            throw new DatiEsperimentoNonValidiException("Anno di simulazione non valido");
        }
        this.annoSimulazione = annoSimulazione;
    }

    public String descrizione(){
        StringBuilder descrizione = new StringBuilder();
        descrizione.append("Esperimento di simulazione ");
        descrizione.append(nome);
        descrizione.append(", Energia: ");
        descrizione.append(energia);
        descrizione.append("TeV, anno di simulazione : ");
        descrizione.append(annoSimulazione);

        return descrizione.toString();
    }
}
