package libreria;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import libreria.*;


public class libreria_total {
    
public static void abrirVideo(String rutaVideo) {
    try {
        File archivo = new File(rutaVideo);
        if (archivo.exists()) {
            Desktop.getDesktop().open(archivo);
        } else {
            JOptionPane.showMessageDialog(null, "El archivo no existe: " + rutaVideo);
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al abrir el video: " + e.getMessage());
    }
}
    private void imagen_botton(JButton btn, String ruta) {//hay que cargar las imagenes desde cada boton 
        ImageIcon img = new ImageIcon(ruta);
        Image imgEscalada = img.getImage().getScaledInstance(btn.getWidth(), btn.getHeight(), Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(imgEscalada));
    }

    

    public void imagen_Label(JLabel labelname, String ruta) {
        ImageIcon img = new ImageIcon(ruta);
        Icon icon = new ImageIcon(img.getImage().getScaledInstance(labelname.getWidth(), labelname.getHeight(), 0));
        labelname.setIcon(icon);
    }
  
class FondoPanel extends JPanel {

    private Image imagen;

    @Override
    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource("/imagenes/FONDO.PNG")).getImage();

        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        setOpaque(false);
        super.paint(g);
    }
}
public static void cargarVentas(javax.swing.JTable tabla) {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabla.getModel();

    Object[][] datos = {
        {"60319991", "Luis Ramon", "3011234567", "Casa", "C101", "Venta", 180000000, "Cra 12 #34-56","src/imagenes/casa1.png"},
        {"119314619", "Luis Ramon", "3109876543", "Apartamento", "A205", "Venta", 220000000, "Cll 45 #23-11","src/imagenes/apartamento1.png"},
        {"109044675", "Luis Ramon", "3125566778", "Local", "L303", "Venta", 95000000, "Av 3 #9-21","src/imagenes/local1.png"},
        {"60234551", "Luis Ramon", "3001122334", "Apartamento", "A306", "Arriendo", 200000000, "Cra 7 #66-90","src/imagenes/apartamento2.png"},
        {"120096413", "Sara López", "3209988776", "Casa", "C404", "Arriendo", 185000000, "Cll 10 #5-32","src/imagenes/casa2.png"},
        {"562314697", "Sara López", "3114455667", "Casa", "C505", "Arriendo", 195000000, "Cll 33 #44-12","src/imagenes/casa3.png"},
        {"125535354", "Sara López", "3007788990", "Apartamento", "A707", "Venta", 210000000, "Av 1 #2-45","src/imagenes/apartamento3.png"},
        {"109048812", "Sara López", "3165544332", "Local", "L808", "Arriendo", 98000000, "Cll 90 #77-10","src/imagenes/local2.png"},
        {"114477883", "Pedro Ruiz", "3186677889", "Casa", "C909", "Venta", 175000000, "Cra 40 #18-99","src/imagenes/casa4.png"},
        {"60232355", "Pedro Ruiz", "3133344556", "Apartamento", "A010", "Arriendo", 230000000, "Av 6 #5-20","src/imagenes/apartamento5.png"},
        {"125425632", "Javier Salas", "3045566788", "Casa", "C111", "Arriendo", 200000000, "Cra 17 #45-88","src/imagenes/casa7.png"},
        {"121234567", "Javier Salas", "3028899000", "Local", "L121", "Venta", 89000000, "Cll 20 #99-01","src/imagenes/local5.png"}
    };

    for (Object[] fila : datos) {
        model.addRow(fila);
    }
}
public static void cargarCitas(javax.swing.JTable tabla) {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabla.getModel();

    model.setRowCount(0);

    Object[][] datos = {
        {"Carlos Pérez", "carlos@gmail.com", "3011234567", "2025-06-10", "Barranquilla", "Centro", "agente01"},
        {"María Gómez", "maria@gmail.com", "3109876543", "2025-06-11", "Barranquilla", "Norte", "agente02"},
        {"Luis Herrera", "luis@hotmail.com", "3125566778", "2025-06-12", "Soledad", "Centro", "agente03"},
        {"Ana Ramírez", "ana@gmail.com", "3001122334", "2025-06-13", "Barranquilla", "Sur", "agente01"},
        {"Jorge Díaz", "jorge@gmail.com", "3209988776", "2025-06-14", "cucuta", "Norte", "agente02"},
        {"Sandra López", "sandra@gmail.com", "3114455667", "2025-06-15", "cucuta", "Este", "agente04"},
        {"David Rojas", "david@gmail.com", "3007788990", "2025-06-16", "Barranquilla", "Oeste", "agente03"},
        {"Sofía Torres", "sofia@gmail.com", "3165544332", "2025-06-17", "bogota", "Centro", "agente01"},
        {"Andrea Méndez", "andrea@hotmail.com", "3186677889", "2025-06-18", "Barranquilla", "Norte", "agente02"},
        {"Camilo Vargas", "camilo@gmail.com", "3133344556", "2025-06-19", "cartagena", "Sur", "agente04"},
        {"Valeria Peña", "valeria@gmail.com", "3045566788", "2025-06-20", "Barranquilla", "Centro", "agente03"},
        {"Esteban Salas", "esteban@gmail.com", "3028899000", "2025-06-21", "Soledad", "Norte", "agente01"}
    };

    for (Object[] fila : datos) {
        model.addRow(fila);
    }
}
public static void cargarContratos(javax.swing.JTable tabla) {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabla.getModel();
    model.setRowCount(0); // Limpia filas existentes

    Object[][] datos = {
        {"C001", "ARRENDAMIENTO", "Casa", "CASA101", "Carlos Pérez", "agente01", "Activo", 1200000, "2025-01-01", "2025-12-31"},
        {"C002", "VENTA", "Apartamento", "APT202", "María Gómez", "agente02", "Finalizado", 200000000, "2024-03-15", "2024-05-10"},
        {"C003", "ARRENDAMIENTO", "Local", "LOC303", "Luis Herrera", "agente03", "Activo", 950000, "2025-04-01", "2026-03-31"},
        {"C004", "VENTA", "Casa", "CASA404", "Ana Ramírez", "agente01", "Pendiente", 185000000, "2025-06-01", "2025-07-15"},
        {"C005", "ARRENDAMIENTO", "Apartamento", "APT505", "Jorge Díaz", "agente02", "Renovado", 1100000, "2024-12-01", "2025-11-30"}
    };

    for (Object[] fila : datos) {
        model.addRow(fila);
    }
}public static void cargarSeguimientos(javax.swing.JTable tabla) {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabla.getModel();
    model.setRowCount(0); 

    Object[][] datos = {
        
        {"Casa",        "C101",    "Carlos Pérez", "agente01", "Activo",     "En reparacion", "2025-01-01", "2025-12-31"},
        {"Apartamento", "A205",    "María Gómez",  "agente02", "Finalizado", "activa", "2024-03-01", "2024-05-31"},
        {"Local",       "L303",    "Luis Herrera", "agente03", "Pendiente",   "mantenimeinto", "2025-04-01", "2026-03-31"},
        {"Apartamento", "A306",    "Ana Ramírez",  "agente01", "En revisión","arreglo de caño", "2025-06-10", "2025-12-10"},
        {"Apartamento", "A010",    "Camilo Vargas","agente04", "Activo",     "cambio de vidrio", "2025-02-01", "2025-08-01"},
        {"Local",       "L121",    "Esteban Salas","agente01", "Cancelado",   "activa", "2024-11-01", "2025-03-01"}
    };

    for (Object[] fila : datos) {
        model.addRow(fila);
    }
}
public static ImageIcon cargarYEscalarImagen(String ruta, int ancho, int alto) {
    ImageIcon icono = new ImageIcon(ruta);
    Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(imagen);
}
}