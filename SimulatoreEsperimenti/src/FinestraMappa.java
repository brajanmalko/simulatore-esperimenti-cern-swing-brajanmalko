import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.*;

/**
 * Finestra per la visualizzazione della mappa del CERN tramite Google Maps
 * Utilizza JavaFX WebView per incorporare contenuti HTML con Google Maps Embed API
 */
public class FinestraMappa extends JFrame {

    private JFXPanel jfxPanel;
    private WebView webView;
    private WebEngine webEngine;

    /**
     * Costruttore della finestra mappa
     */
    public FinestraMappa() {
        initComponents();
    }

    /**
     * Inizializza i componenti della finestra
     */
    private void initComponents() {
        setTitle("Mappa CERN - Simulatore Esperimenti");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Inizializza JavaFX Panel
        jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);

        // Aggiungi pannello di controllo
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);

        // Inizializza JavaFX WebView sul thread JavaFX
        Platform.runLater(() -> initFX());
    }

    /**
     * Crea il pannello di controllo con pulsanti
     */
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(0, 81, 165));
        panel.setPreferredSize(new Dimension(1000, 60));

        JButton btnRicarica = new JButton("Ricarica Mappa");
        btnRicarica.setFont(new Font("Arial", Font.BOLD, 12));
        btnRicarica.setBackground(Color.WHITE);
        btnRicarica.setForeground(new Color(0, 81, 165));
        btnRicarica.setFocusPainted(false);
        btnRicarica.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRicarica.addActionListener(e -> ricaricaMappa());

        JButton btnChiudi = new JButton("Chiudi");
        btnChiudi.setFont(new Font("Arial", Font.BOLD, 12));
        btnChiudi.setBackground(new Color(220, 53, 69));
        btnChiudi.setForeground(Color.WHITE);
        btnChiudi.setFocusPainted(false);
        btnChiudi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChiudi.addActionListener(e -> dispose());

        JLabel lblInfo = new JLabel("CERN - Geneva, Switzerland  ");
        lblInfo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInfo.setForeground(Color.WHITE);

        panel.add(lblInfo);
        panel.add(btnRicarica);
        panel.add(btnChiudi);

        return panel;
    }

    /**
     * Inizializza JavaFX WebView e carica la mappa
     */
    private void initFX() {
        webView = new WebView();
        webEngine = webView.getEngine();

        // Abilita JavaScript
        webEngine.setJavaScriptEnabled(true);

        // Carica la mappa HTML inline
        caricaMappaCERN();

        // Crea la scena JavaFX
        Scene scene = new Scene(webView);
        jfxPanel.setScene(scene);
    }

    /**
     * Carica la mappa del CERN con Google Maps Embed API
     */
    private void caricaMappaCERN() {
        String htmlContent = "<!DOCTYPE html>" +
                "<html lang='it'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <title>Mappa CERN</title>" +
                "    <style>" +
                "        body {" +
                "            margin: 0;" +
                "            padding: 0;" +
                "            font-family: Arial, sans-serif;" +
                "            background-color: #f0f0f0;" +
                "        }" +
                "        .header {" +
                "            background: linear-gradient(135deg, #0051a5 0%, #0066cc 100%);" +
                "            color: white;" +
                "            padding: 20px;" +
                "            text-align: center;" +
                "            box-shadow: 0 2px 4px rgba(0,0,0,0.1);" +
                "        }" +
                "        .header h1 {" +
                "            margin: 0;" +
                "            font-size: 28px;" +
                "            font-weight: bold;" +
                "        }" +
                "        .header p {" +
                "            margin: 5px 0 0 0;" +
                "            font-size: 14px;" +
                "            opacity: 0.9;" +
                "        }" +
                "        .map-container {" +
                "            width: 100%;" +
                "            height: calc(100vh - 120px);" +
                "            display: flex;" +
                "            justify-content: center;" +
                "            align-items: center;" +
                "            padding: 0;" +
                "            position: relative;" +
                "        }" +
                "        iframe {" +
                "            width: 100%;" +
                "            height: 100%;" +
                "            border: none;" +
                "        }" +
                "        .info-box {" +
                "            position: absolute;" +
                "            bottom: 30px;" +
                "            left: 30px;" +
                "            background-color: rgba(255, 255, 255, 0.97);" +
                "            padding: 20px;" +
                "            border-radius: 10px;" +
                "            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);" +
                "            max-width: 350px;" +
                "            z-index: 1000;" +
                "            border: 2px solid #0051a5;" +
                "        }" +
                "        .info-box h3 {" +
                "            margin-top: 0;" +
                "            color: #0051a5;" +
                "            font-size: 18px;" +
                "            border-bottom: 2px solid #0051a5;" +
                "            padding-bottom: 8px;" +
                "        }" +
                "        .info-box p {" +
                "            margin: 8px 0;" +
                "            font-size: 14px;" +
                "            line-height: 1.6;" +
                "            color: #333;" +
                "        }" +
                "        .info-box strong {" +
                "            color: #0051a5;" +
                "        }" +
                "        .badge {" +
                "            display: inline-block;" +
                "            background-color: #0051a5;" +
                "            color: white;" +
                "            padding: 4px 10px;" +
                "            border-radius: 12px;" +
                "            font-size: 12px;" +
                "            margin-top: 8px;" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class='header'>" +
                "        <h1>Mappa del CERN</h1>" +
                "        <p>European Organization for Nuclear Research - Geneva, Switzerland</p>" +
                "    </div>" +
                "    <div class='map-container'>" +
                "        <iframe " +
                "            id='gmap_canvas' " +
                "            src='https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2762.61316185491!2d6.055975976722662!3d46.23321307109381!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x478c7af86c6f8ab7%3A0xbbb6aa8a0c0fd565!2sCERN!5e0!3m2!1sit!2sch!4v1700000000000' "  +
                "            frameborder='0' " +
                "            referrerpolicy='no-referrer-when-downgrade" +
                "            scrolling='no' " +
                "            marginheight='0' " +
                "            marginwidth='0' " +
                "            allowfullscreen>" +
                "        </iframe>" +
                "        <div class='info-box'>" +
                "            <h3>CERN - Centro di Ricerca</h3>" +
                "       s     <p><strong>Indirizzo:</strong><br>Esplanade des Particules 1<br>1211 Meyrin, Switzerland</p>" +
                "            <p><strong>Coordinate:</strong> 46.2333° N, 6.0557° E</p>" +
                "            <p><strong>Fondazione:</strong> 29 Settembre 1954</p>" +
                "            <p>Il più grande laboratorio di fisica delle particelle al mondo, sede del Large Hadron Collider (LHC).</p>" +
                "            <span class='badge'>⚛️ Fisica delle Particelle</span>" +
                "            <span class='badge'>🔬 Ricerca Scientifica</span>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";

        webEngine.loadContent(htmlContent);
    }

    /**
     * Ricarica la mappa
     */
    private void ricaricaMappa() {
        Platform.runLater(() -> {
            webEngine.reload();
        });
    }

    /**
     * Mostra la finestra della mappa
     */
    public void mostraMappa() {
        setVisible(true);
    }

    /**
     * Main per test standalone
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinestraMappa finestra = new FinestraMappa();
            finestra.mostraMappa();
        });
    }
}