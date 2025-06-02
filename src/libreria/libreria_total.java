package libreria;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import libreria.*;


public class libreria_total {

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

    public static int[] llenarVector(int n) {
        int vector[] = new int[n];
        Random x = new Random();

        for (int i = 0; i < vector.length; i++) {
            vector[i] = x.nextInt(50) + 1; // Genera un número entre 1 y 50
        }
        return vector;
    }
  

public class Metodos {
    private ArrayList<Inmueble> listaInmuebles = new ArrayList<>();
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private ArrayList<Visita> listaVisitas = new ArrayList<>();
    private ArrayList<Contrato> listaContratos = new ArrayList<>();

    public void registrarUsuario(Usuario u) {
        listaUsuarios.add(u);
    }

    public void registrarInmueble(Inmueble i) {
        listaInmuebles.add(i);
    }

    public void agendarVisita(Visita v) {
        listaVisitas.add(v);
    }

    public void registrarContrato(Contrato c) {
        listaContratos.add(c);
    }
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
}
