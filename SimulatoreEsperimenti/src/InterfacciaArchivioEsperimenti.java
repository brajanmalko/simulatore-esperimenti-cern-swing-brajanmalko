import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfacciaArchivioEsperimenti extends JFrame {
    private ArchivioEsperimenti archivio = new ArchivioEsperimenti();
    private MappaScoperte preferiti = new MappaScoperte();

    private DefaultListModel<Esperimento> modelloArchivio = new DefaultListModel<>();
    private DefaultListModel<String> modelloPreferiti = new DefaultListModel<>();

    private JList<Esperimento> listaArchivio;
    private JList<String> listaPreferiti;

    public InterfacciaArchivioEsperimenti() {
        super("Gestione esperimenti fisici");
        // Configurazione base della pagina
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout(10, 10));

        //Crea le due liste e i modelli
        listaArchivio = new JList<>(modelloArchivio);
        listaArchivio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaPreferiti = new JList<>(modelloPreferiti);
        listaPreferiti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Pannello con le due liste
        JPanel pannelloListe = new JPanel(new GridLayout(1, 2, 10, 10));

        //pannello archivio
        JPanel pannelloArchivio = new JPanel(new BorderLayout());
        pannelloArchivio.setBorder(BorderFactory.createTitledBorder("Archivio esperimenti"));
        pannelloArchivio.add(new JScrollPane(listaArchivio), BorderLayout.CENTER);

        //pannello preferiti
        JPanel pannelloPreferiti = new JPanel(new BorderLayout());
        pannelloPreferiti.setBorder(BorderFactory.createTitledBorder("Preferiti"));
        pannelloPreferiti.add(new JScrollPane(listaPreferiti), BorderLayout.CENTER);

        pannelloListe.add(pannelloArchivio);
        pannelloListe.add(pannelloPreferiti);

        add(pannelloListe, BorderLayout.CENTER);

        // Pannello con pulsanti
        JPanel pannelloBottoni = new JPanel();

        JButton btnCarica = new JButton("Carica");
        JButton btnSalva = new JButton("Salva");
        JButton btnAggiungiPref = new JButton("Aggiungi preferiti");
        JButton btnRimuoviPref = new JButton("Rimuovi preferiti");
        JButton btnMappa = new JButton("Apri mappa");


        pannelloBottoni.add(btnCarica);
        pannelloBottoni.add(btnSalva);
        pannelloBottoni.add(btnAggiungiPref);
        pannelloBottoni.add(btnRimuoviPref);
        pannelloBottoni.add(btnMappa);

        add(pannelloBottoni, BorderLayout.SOUTH);

        // Eventi per doppio clic sulle liste
        listaArchivio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // doppio click
                    Esperimento selezionato = listaArchivio.getSelectedValue();
                    if (selezionato != null)
                        new FinestraDettaglio(selezionato, () -> aggiornaListaArchivio());
                }
            }
        });

        listaPreferiti.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String chiave = listaPreferiti.getSelectedValue();
                    if (chiave != null)
                        new FinestraDettaglio(preferiti.mappa.get(chiave), () -> aggiornaListaPreferiti());
                }
            }
        });

        // Eventi pulsanti
        btnAggiungiPref.addActionListener(e -> aggiungiAiPreferiti());
        btnRimuoviPref.addActionListener(e -> rimuoviDaiPreferiti());
        btnSalva.addActionListener(e -> salvaArchivio());
        btnCarica.addActionListener(e -> caricaArchivio());
        btnMappa.addActionListener(e-> apriMappaCERN());

        // Dati di test
        archivio.aggiungiScoperta(new EsperimentoSimulazione("e1", 500, 2024));
        archivio.aggiungiScoperta(new EsperimentoRilevamento("e2", 300, "quark top"));
        archivio.aggiungiScoperta(new EsperimentoCollisione("e3", 200, 5));

        aggiornaListaArchivio();
    }


    //metodi di supporto dell'interfaccia
    private void aggiornaListaArchivio() {
        modelloArchivio.clear();
        for (Esperimento e : archivio.lista)
            modelloArchivio.addElement(e);
    }

    private void aggiornaListaPreferiti() {
        modelloPreferiti.clear();
        for (String chiave : preferiti.elencoChiavi())
            modelloPreferiti.addElement(chiave);
    }

    private void aggiungiAiPreferiti() {
        Esperimento sel = listaArchivio.getSelectedValue();
        if (sel == null) return;
        String nome = JOptionPane.showInputDialog(this, "Nome scoperta:", sel.getNome());
        if (nome != null && !nome.isBlank()) {
            try {
                preferiti.aggiungiScoperta(nome, sel);
                aggiornaListaPreferiti();
            } catch (DatiEsperimentoNonValidiException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    private void rimuoviDaiPreferiti() {
        String chiave = listaPreferiti.getSelectedValue();
        if (chiave != null) {
            try {
                preferiti.rimuoviScoperta(chiave);
                aggiornaListaPreferiti();
            } catch (DatiEsperimentoNonValidiException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    private void salvaArchivio() {
        try {
            archivio.salvaSuFile();
            JOptionPane.showMessageDialog(this, "Archivio salvato correttamente!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore nel salvataggio: " + e.getMessage());
        }
    }

    private void caricaArchivio() {
        try {
            archivio.caricaDaFile();
            aggiornaListaArchivio();
            JOptionPane.showMessageDialog(this, "Archivio caricato correttamente!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore nel caricamento: " + e.getMessage());
        }
    }

    private void apriMappaCERN() {
        try {
            FinestraMappa finestraMappa = new FinestraMappa();
            finestraMappa.mostraMappa();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Errore nell'apertura della mappa: " + ex.getMessage(),
                    "Errore Mappa CERN",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfacciaArchivioEsperimenti().setVisible(true));
    }
}
