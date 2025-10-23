public class EsperimentoRilevamento extends Esperimento{
    String nome;
    double energia;
    String tipoParticella;

    public EsperimentoRilevamento(String nome, double energia, String tipoParticella) throws DatiEsperimentoNonValidiException{
        super(nome, energia);
        if(tipoParticella == null){
            throw new DatiEsperimentoNonValidiException("Tipo di particella non valido");
        }
        this.tipoParticella = tipoParticella;
    }

    public String descrizione(){
        StringBuilder descrizione = new StringBuilder();
        descrizione.append("Esperimento di rilevamento ");
        descrizione.append(nome);
        descrizione.append(", Energia: ");
        descrizione.append(energia);
        descrizione.append("TeV, Tipo di particella: ");
        descrizione.append(tipoParticella);

        return descrizione.toString();
    }
}
