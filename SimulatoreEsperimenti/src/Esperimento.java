import java.io.Serializable;


//Classe base astratta Esperimento, non possono essere create sue istanze
public abstract class Esperimento implements Serializable {
    private String nome;
    private double energia;

    //costruttore con i throws, che permette al compilarore di sapere che può essere lanciata una eccezione
    public Esperimento(String nome, double energia) throws DatiEsperimentoNonValidiException {
       //controllo delle variabili, in caso di non validità viene lanciata una eccessione personalizzata
        if(nome == null){
            throw new DatiEsperimentoNonValidiException("Nome non valido");
        }
        if(energia <= 0){
            throw new DatiEsperimentoNonValidiException("Energia non valido");
        }
        this.nome = nome;
        this.energia = energia;
    }

    //dirma del metodo descrizione astratto che deve essere implementato dalle classi astratte
    abstract String descrizione();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
         this.nome = nome;
    }
    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia) {
        this.energia = energia;
    }
}