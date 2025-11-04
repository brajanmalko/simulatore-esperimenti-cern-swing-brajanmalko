import javax.swing.*;
import java.awt.*;

public class FinestraDettaglio extends JDialog {

    public FinestraDettaglio(Esperimento esperimento, Runnable onSave) {
        setTitle("Dettagli Esperimento - " + esperimento.getNome());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField txtNome = new JTextField(esperimento.getNome());
        JTextField txtEnergia = new JTextField(String.valueOf(esperimento.getEnergia()));

        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Energia:"));
        panel.add(txtEnergia);

        // Campi dinamici per i vari esperimenti
        if (esperimento instanceof EsperimentoCollisione c) {
            JTextField txtNumeroParticelle = new JTextField(String.valueOf(c.getNumeroCollisioni()));
            panel.add(new JLabel("Numero collisioni:"));
            panel.add(txtNumeroParticelle);
        } else if (esperimento instanceof EsperimentoRilevamento r) {
            JTextField txtParticella = new JTextField(r.getTipoParticella());
            panel.add(new JLabel("Tipo particella:"));
            panel.add(txtParticella);
        } else if (esperimento instanceof EsperimentoSimulazione s) {
            JTextField txtAnno = new JTextField(String.valueOf(s.getAnno()));
            panel.add(new JLabel("Anno:"));
            panel.add(txtAnno);
        }

        JButton btnSalva = new JButton("Salva modifiche");
        btnSalva.addActionListener(e -> {
            esperimento.setNome(txtNome.getText());
            esperimento.setEnergia(Double.parseDouble(txtEnergia.getText()));
            dispose();
            onSave.run(); // aggiorna lista
        });

        add(panel, BorderLayout.CENTER);
        add(btnSalva, BorderLayout.SOUTH);
        setVisible(true);
    }
}
