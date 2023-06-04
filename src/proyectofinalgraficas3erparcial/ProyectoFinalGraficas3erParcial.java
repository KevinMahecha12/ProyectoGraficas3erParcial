package proyectofinalgraficas3erparcial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kevin
 */
public class ProyectoFinalGraficas3erParcial extends JFrame {

    private BufferedImage buffer;
    private Graphics2D buffer2D;

    double[][] Cuadrado = {
        {100, 100, 100}, // Vértice A
        {200, 100, 100}, // Vértice B
        {200, 200, 100}, // Vértice C
        {100, 200, 100}, // Vértice D
        {100, 100, 200}, // Vértice E
        {200, 100, 200}, // Vértice F
        {200, 200, 200}, // Vértice G
        {100, 200, 200} // Vértice H
    };
    double[][] Volcan = {
        {90, 170, 100}, // Vértice A
        {180, 170, 100}, // Vértice B
        {340, 400, 100}, // Vértice C
        {-30, 400, 100}, // Vértice D
        {190, 260, 200}, // Vértice E
        {360, 260, 200}, // Vértice F
        {700, 570, 200}, // Vértice G
        {-130, 540, 200} // Vértice H
    };
    double[][] BaseMeteoro = {
        //X,Y,Z
        {100, 100, 100}, // Vértice A
        {200, 100, 100}, // Vértice B
        {200, 200, 100}, // Vértice C
        {100, 200, 100}, // Vértice D
        {100, 100, 200}, // Vértice E
        {200, 100, 200}, // Vértice F
        {200, 200, 200}, // Vértice G
        {100, 200, 200} // Vértice H
    };
    double[][] FuegoMeteoro = {
        //X,Y,Z
        {200, 400, 100}, // Vértice A
        {400, 100, 100}, // Vértice B
        {600, 400, 100}, // Vértice C
        {400, 900, 100}, // Vértice D
        {200, 100, 200}, // Vértice E
        {400, 100, 200}, // Vértice F
        {600, 200, 200}, // Vértice G
        {400, 200, 200} // Vértice H
    };

    List<Figura> MeteoroIncendiado = new ArrayList<Figura>();

    ProyectoFinalGraficas3erParcial() {
        setTitle("Rotación 3D, Kevin Giovanni Mahecha Cabuto, 20310027, 6P");
        setSize(1200, 900);
        this.setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        buffer2D = (Graphics2D) buffer.createGraphics();

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                buffer2D.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
                buffer2D.setBackground(new Color(238, 238, 238));

                ColocarFondoDetras();
                ColocarFondoPiso(0, 600, 300);
                ColocarVolcan();
                ColocarMeteoro();
                imprimirPuntosYdibujarContorno(false);

                g.drawImage(buffer, 0, 0, null);
                g.dispose();
            }
        };

        panel.setPreferredSize(new Dimension(1000, 900));
        this.add(panel);
        this.pack();
    }

    public static void main(String[] args) throws InterruptedException {
        ProyectoFinalGraficas3erParcial rotacion = new ProyectoFinalGraficas3erParcial();
        Thread rotar = new Thread(() -> rotacion.rotacion(rotacion.Cuadrado, 45, 1));
        Thread rotar2 = new Thread(() -> rotacion.rotacion(rotacion.Cuadrado, 45, 2));
        Thread rotar3 = new Thread(() -> rotacion.rotacion(rotacion.Cuadrado, 45, 3));

    }

    public void ColocarFondoPiso(int x, int y, int altura) {
        int r1 = 0;
        int g1 = 128;
        int b1 = 64;
        int r2 = 0;
        int g2 = 198;
        int b2 = 99;

        // Degradado Azul
        for (int i = 0; i < altura; i++) {
            int r = r1 + i * (r2 - r1) / altura;
            int g = g1 + i * (g2 - g1) / altura;
            int b = b1 + i * (b2 - b1) / altura;
            Color c = new Color(r, g, b);
            putLineaDDA(x, i + y, getWidth() + x, i + y, c);
        }
    }

    public void ColocarFondoDetras() {
        int altura = 600;
        int r1 = 0;
        int g1 = 50;
        int b1 = 150;
        int r2 = 0;
        int g2 = 160;
        int b2 = 230;

        // Degradado Azul
        for (int i = 0; i < altura; i++) {
            int r = r1 + i * (r2 - r1) / altura;
            int g = g1 + i * (g2 - g1) / altura;
            int b = b1 + i * (b2 - b1) / altura;
            Color c = new Color(r, g, b);
            putLineaDDA(0, i, getWidth(), i, c);
        }
    }

    public Figura HacerRectangulo(int x1, int y1, int x2, int y2, int Ancho, int Alto, Color c) {

        // Crear objeto Forma con los puntos correspondientes al rectángulo
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        Figura figura = new Figura(minX, minY, minX + Ancho, minY, minX + Ancho, minY + Alto, minX, minY + Alto);
        return figura;
    }

    public void ColocarVolcan() {
        Color cafe = new Color(105, 65, 44);
        Color cafeclaro = new Color(185, 122, 87);
        RellenarObjeto(Volcan, cafe, cafe, cafe, cafe, cafe, cafe, true, cafeclaro);
        RellenarElipse(buffer, 500, 373, 112, 50, Color.BLACK);

    }

    public void ColocarMeteoro() {
        FuegoOrtogonal(150, 200, 5, 3);
        FuegoOrtogonal(210, 180, 5, 2);
        FuegoOrtogonal(220, 210, 5, 3);
        colocarFiguraOrtogonal(10, 20, 1);
        colocarFiguraOrtogonal(45, 130, 2);
        colocarFiguraOrtogonal(85, 50, 2);



    }

    public void colocarFiguraOrtogonal(int x, int y, double tamaño) {
        // Obtener los puntos proyectados en 2D
        double[][] cord2d = ProyeccionOrtogonal(BaseMeteoro);

        // Imprimir los puntos proyectados en 2D
        System.out.println("Puntos Ortogonales en 2D:");
        for (int i = 0; i < cord2d.length; i++) {
            System.out.println("Punto " + (i + 1) + ": (" + cord2d[i][0] + ", " + cord2d[i][1] + ")");
            // Dibujar los puntos en la ventana
            int pixelX = (int) (cord2d[i][0] / tamaño) + x;
            int pixelY = (int) (cord2d[i][1] / tamaño) + y;
            addPixel(pixelX, pixelY, Color.BLACK);

            // Conectar los puntos de manera automática
            if (i > 0) {
                int prevPixelX = (int) (cord2d[i - 1][0] / tamaño) + x;
                int prevPixelY = (int) (cord2d[i - 1][1] / tamaño) + y;
                putLineaDDA(pixelX, pixelY, prevPixelX, prevPixelY, Color.BLACK);
            }

            // Conectar el último punto con el primer punto para cerrar el contorno
            if (i == cord2d.length - 1) {
                int firstPixelX = (int) (cord2d[0][0] / tamaño) + x;
                int firstPixelY = (int) (cord2d[0][1] / tamaño) + y;
                putLineaDDA(pixelX, pixelY, firstPixelX, firstPixelY, Color.BLACK);
            }
        }

        // Crear la figura BaseMeteoro2D con los puntos 2D obtenidos
        Figura BaseMeteoro2D = new Figura(
                (int) (cord2d[0][0] / tamaño) + x, (int) (cord2d[0][1] / tamaño) + y, // Punto 1
                (int) (cord2d[1][0] / tamaño) + x, (int) (cord2d[1][1] / tamaño) + y, // Punto 2
                (int) (cord2d[2][0] / tamaño) + x, (int) (cord2d[2][1] / tamaño) + y, // Punto 3
                (int) (cord2d[3][0] / tamaño) + x, (int) (cord2d[3][1] / tamaño) + y // Punto 4
        );
        MeteoroIncendiado.add(BaseMeteoro2D);
        RellenarFiguraScanLine(BaseMeteoro2D, Color.BLACK, Color.BLACK, Color.BLACK);
    }

    public void FuegoOrtogonal(int x, int y, double tamaño, double ancho) {
        // Obtener los puntos proyectados en 2D
        double[][] cord2d = ProyeccionOrtogonal(FuegoMeteoro);

        // Imprimir los puntos proyectados en 2D
        System.out.println("Puntos Ortogonales del fuego en 2D:");
        for (int i = 0; i < cord2d.length; i++) {
            System.out.println("Punto " + (i + 1) + ": (" + cord2d[i][0] + ", " + cord2d[i][1] + ")");
            // Dibujar los puntos en la ventana
            int pixelX = (int) (-cord2d[i][0] / (tamaño * ancho)) + x;  // Invierte la coordenada X y ajusta el ancho
            int pixelY = (int) (-cord2d[i][1] / tamaño) + y;  // Invierte la coordenada Y

            // Dibuja el punto en la ventana con las coordenadas invertidas
            addPixel(pixelX, pixelY, Color.BLACK);
        }

        // Crear la figura BaseMeteoro2D con los puntos 2D obtenidos
        Figura FuegoOrtogonal = new Figura(
                (int) (-cord2d[0][0] / (tamaño * ancho)) + x, (int) (-cord2d[0][1] / tamaño) + y, // Punto 1
                (int) (-cord2d[1][0] / (tamaño * ancho)) + x, (int) (-cord2d[1][1] / tamaño) + y, // Punto 2
                (int) (-cord2d[2][0] / (tamaño * ancho)) + x, (int) (-cord2d[2][1] / tamaño) + y, // Punto 3
                (int) (-cord2d[3][0] / (tamaño * ancho)) + x, (int) (-cord2d[3][1] / tamaño) + y // Punto 4
        );
        RellenarFiguraScanLine(FuegoOrtogonal, Color.BLACK, Color.RED, Color.RED);
        MeteoroIncendiado.add(FuegoOrtogonal);
    }

    public double[][] ProyeccionOrtogonal(double[][] objeto) {
        int numPuntos = objeto.length;
        double[][] cord2d = new double[numPuntos][2];

        for (int i = 0; i < numPuntos; i++) {
            double x = objeto[i][0];
            double y = objeto[i][1];
            // Ignoramos el eje z para proyección ortogonal
            cord2d[i][0] = x;
            cord2d[i][1] = y;
        }

        return cord2d;
    }

    public void RellenarElipse(BufferedImage image, int xc, int yc, int rx, int ry, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage tempImage = new BufferedImage(width, height, image.getType());
        Graphics2D g = tempImage.createGraphics();
        g.drawImage(image, 0, 0, null);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (Math.pow((x - xc), 2) / Math.pow(rx, 2) + Math.pow((y - yc), 2) / Math.pow(ry, 2) <= 1) {
                    int pixel = image.getRGB(x, y);
                    if (pixel != Color.BLACK.getRGB()) {
                        tempImage.setRGB(x, y, color.getRGB());
                    } else {
                        tempImage.setRGB(x, y, color.getRGB());
                    }
                }
            }
        }

        image.setData(tempImage.getData());
    }

    public void hacerElipse(int xc, int yc, int rx, int ry, Color color) {
        double ang;
        int x1, y1, x2, y2;
        for (int i = 0; i < 360; i++) {
            ang = Math.toRadians(i);
            x1 = (int) Math.round(xc + rx * Math.cos(ang));
            y1 = (int) Math.round(yc + ry * Math.sin(ang));
            ang = Math.toRadians(i + 1);
            x2 = (int) Math.round(xc + rx * Math.cos(ang));
            y2 = (int) Math.round(yc + ry * Math.sin(ang));
            putLineaDDA(x1, y1, x2, y2, color);
        }
    }

    public void imprimirPuntosYdibujarContorno(Boolean OcultarCaras) {

        double xp = 4;
        double yp = 6;
        double zp = 3;
        double[][] cordenadas2d = hacerProyeccionOblicua(Volcan, 60, xp, yp, zp, Color.BLACK, OcultarCaras);

        // Imprimir coordenadas 2D
        for (int i = 0; i < cordenadas2d.length; i++) {
            double[] punto = cordenadas2d[i];
            System.out.println("Punto " + (i + 1) + ": (" + punto[0] + ", " + punto[1] + ")");
        }
        System.out.println(); // Agregar una línea en blanco después del punto 8

    }

    public void RellenarObjeto(double[][] Objeto, Color Superior, Color Inferior, Color Izquierda, Color Derecha, Color Frontal, Color Trasera, Boolean OcultarCaras, Color Final) {
        double xp = 4;
        double yp = 6;
        double zp = 3;
        boolean carasocultas = true;
        double[][] puntos2D = hacerProyeccionOblicua(Objeto, 60, xp, yp, zp, Color.white, carasocultas);

        // Imprimir coordenadas 2D
        for (int i = 0; i < puntos2D.length; i++) {
            double[] punto = puntos2D[i];
            System.out.println("Punto " + (i + 1) + ": (" + punto[0] + ", " + punto[1] + ")");
        }
        Figura cuadradoTrasero = new Figura(
                (int) puntos2D[4][0], (int) puntos2D[4][1], // Punto 5
                (int) puntos2D[5][0], (int) puntos2D[5][1], // Punto 6
                (int) puntos2D[6][0], (int) puntos2D[6][1], // Punto 7
                (int) puntos2D[7][0], (int) puntos2D[7][1] // Punto 8
        );
        Figura cuadradoFrontal = new Figura(
                (int) puntos2D[0][0], (int) puntos2D[0][1], // Punto 1
                (int) puntos2D[1][0], (int) puntos2D[1][1], // Punto 2
                (int) puntos2D[2][0], (int) puntos2D[2][1],// Punto 3
                (int) puntos2D[3][0], (int) puntos2D[3][1]// Punto 4
        );
        Figura cuadradoLateralIzquierdo = new Figura(
                (int) puntos2D[5][0], (int) puntos2D[5][1], // Punto 6
                (int) puntos2D[1][0], (int) puntos2D[1][1], // Punto 2
                (int) puntos2D[2][0], (int) puntos2D[2][1], // Punto 3        
                (int) puntos2D[6][0], (int) puntos2D[6][1]// Punto 7
        );
        Figura cuadradoLateralDerecho = new Figura(
                (int) puntos2D[4][0], (int) puntos2D[4][1], // Punto 5
                (int) puntos2D[0][0], (int) puntos2D[0][1], // Punto 1
                (int) puntos2D[3][0], (int) puntos2D[3][1], // Punto 4
                (int) puntos2D[7][0], (int) puntos2D[7][1] // Punto 8
        );
        Figura cuadradoSuperiorTecho = new Figura(
                (int) puntos2D[0][0], (int) puntos2D[0][1], // Punto 1
                (int) puntos2D[4][0], (int) puntos2D[4][1], // Punto 5
                (int) puntos2D[5][0], (int) puntos2D[5][1], // Punto 6
                (int) puntos2D[1][0], (int) puntos2D[1][1] // Punto 2
        );
        Figura cuadradoinferior = new Figura(
                (int) puntos2D[3][0], (int) puntos2D[3][1], // Punto 4
                (int) puntos2D[7][0], (int) puntos2D[7][1], // Punto 8
                (int) puntos2D[6][0], (int) puntos2D[6][1], // Punto 7
                (int) puntos2D[2][0], (int) puntos2D[2][1] // Punto 3
        );
        if (OcultarCaras == true) {
            RellenarFiguraScanLine(cuadradoSuperiorTecho, Color.BLACK, Superior, Final);
            RellenarFiguraScanLine(cuadradoTrasero, Color.BLACK, Trasera, Final);
            RellenarFiguraScanLine(cuadradoLateralDerecho, Color.BLACK, Derecha, Final);
            RellenarFiguraScanLine(cuadradoLateralIzquierdo, Color.BLACK, Inferior, Final);
            RellenarFiguraScanLine(cuadradoSuperiorTecho, Color.BLACK, Superior, Final);
            RellenarFiguraScanLine(cuadradoFrontal, Color.BLACK, Frontal, Final);
        } else {
            carasocultas = false;
            RellenarFiguraScanLine(cuadradoinferior, Color.BLACK, Inferior, Final);
            RellenarFiguraScanLine(cuadradoLateralIzquierdo, Color.BLACK, Inferior, Final);
            RellenarFiguraScanLine(cuadradoFrontal, Color.BLACK, Frontal, Final);
            RellenarFiguraScanLine(cuadradoSuperiorTecho, Color.BLACK, Superior, Final);
            RellenarFiguraScanLine(cuadradoTrasero, Color.BLACK, Trasera, Final);
            RellenarFiguraScanLine(cuadradoLateralDerecho, Color.BLACK, Derecha, Final);
        }

        System.out.println(); // Agregar una línea en blanco después del punto 8
    }

    public void RellenarFiguraScanLine(Figura figura, Color colorBorde, Color colorRellenoInicial, Color colorRellenoFinal) {
        // Obtenemos los puntos de la figura
        Puntos punto1 = figura.obtenerPT1();
        Puntos punto2 = figura.obtenerPT2();
        Puntos punto4 = figura.obtenerPT3();
        Puntos punto3 = figura.obtenerPT4();

        // Se crea una lista de puntos ordenados por coordenada Y
        List<Puntos> puntosOrdenados = new ArrayList<>();
        puntosOrdenados.add(punto1);
        puntosOrdenados.add(punto2);
        puntosOrdenados.add(punto4);
        puntosOrdenados.add(punto3);

        // Obtenemos las coordenadas mínimas y máximas
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Puntos punto : puntosOrdenados) {
            int x = punto.getposX();
            int y = punto.getposY();

            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }

        // Scanline dentro del cuadrado
        for (int y = minY + 1; y < maxY; y++) {
            List<Integer> intersecciones = new ArrayList<>();

            for (int i = 0; i < puntosOrdenados.size(); i++) {
                Puntos puntoActual = puntosOrdenados.get(i);
                Puntos puntoSiguiente = puntosOrdenados.get((i + 1) % puntosOrdenados.size());

                int x1 = puntoActual.getposX();
                int y1 = puntoActual.getposY();
                int x2 = puntoSiguiente.getposX();
                int y2 = puntoSiguiente.getposY();

                if ((y1 <= y && y < y2) || (y2 <= y && y < y1)) {
                    int xInterseccion = (int) (x1 + ((double) (y - y1) / (double) (y2 - y1)) * (x2 - x1));
                    intersecciones.add(xInterseccion);
                }
            }

            intersecciones.sort(Comparator.naturalOrder());

            for (int i = 0; i < intersecciones.size(); i += 2) {
                int xInicio = intersecciones.get(i);
                int xFin = intersecciones.get(i + 1);

                Color colorRelleno = interpolarColor(colorRellenoInicial, colorRellenoFinal, (float) (y - minY) / (float) (maxY - minY));

                for (int x = xInicio; x <= xFin; x++) {
                    buffer.setRGB(x, y, colorRelleno.getRGB());
                }
            }
        }

        colocarforma(figura, colorBorde);
    }

    private Color interpolarColor(Color colorInicial, Color colorFinal, float fraccion) {
        int rInicial = colorInicial.getRed();
        int gInicial = colorInicial.getGreen();
        int bInicial = colorInicial.getBlue();
        int rFinal = colorFinal.getRed();
        int gFinal = colorFinal.getGreen();
        int bFinal = colorFinal.getBlue();

        int rInterpolado = (int) (rInicial + (rFinal - rInicial) * fraccion);
        int gInterpolado = (int) (gInicial + (gFinal - gInicial) * fraccion);
        int bInterpolado = (int) (bInicial + (bFinal - bInicial) * fraccion);

        return new Color(rInterpolado, gInterpolado, bInterpolado);
    }

    public void colocarforma(Figura figura, Color colorBorde) {
        Puntos[] vertices = {figura.obtenerPT1(), figura.obtenerPT2(), figura.obtenerPT3(), figura.obtenerPT4()};
        for (int i = 0; i < vertices.length; i++) {
            Puntos puntoActual = vertices[i];
            Puntos puntoSiguiente = vertices[(i + 1) % vertices.length];
            putLineaDDA(puntoActual.getposX(), puntoActual.getposY(), puntoSiguiente.getposX(), puntoSiguiente.getposY(), colorBorde);
        }
    }

    private double[][] hacerProyeccionOblicua(double[][] cord3d, int tamaño, double xp, double yp, double zp, Color Borde, boolean PonerContorno) {
        int[][] conexiones = {
            {0, 1}, {1, 2}, {2, 3}, {3, 0}, // Lados horizontales inferiores
            {4, 5}, {5, 6}, {6, 7}, {7, 4}, // Lados horizontales superiores
            {0, 4}, {1, 5}, {2, 6}, {3, 7} // Conexiones verticales
        };

        double[][] cord2d = new double[cord3d.length][2]; // Arreglo para almacenar las coordenadas 2D

        for (int i = 0; i < conexiones.length; i++) {
            int p1Index = conexiones[i][0];
            int p2Index = conexiones[i][1];

            double[] p1 = cord3d[p1Index];
            double[] p2 = cord3d[p2Index];

            double u = -zp / (p1[2] - zp);
            double x1 = xp + (p1[0] - xp) * u;
            double y1 = yp + (p1[1] - yp) * u;

            u = -zp / (p2[2] - zp);
            double x2 = xp + (p2[0] - xp) * u;
            double y2 = yp + (p2[1] - yp) * u;

            int ptX = ((int) (x1 * tamaño) + getWidth() / 2);
            int ptY = getHeight() / 2 - (int) (y1 * tamaño);

            int ptX2 = (int) (x2 * tamaño) + getWidth() / 2;
            int ptY2 = getHeight() / 2 - (int) (y2 * tamaño);

            cord2d[p1Index][0] = ptX; // Guarda la coordenada X del punto en cord2d
            cord2d[p1Index][1] = ptY; // Guarda la coordenada Y del punto en cord2d
            cord2d[p2Index][0] = ptX2; // Guarda la coordenada X del punto en cord2d
            cord2d[p2Index][1] = ptY2; // Guarda la coordenada Y del punto en cord2d

            if (PonerContorno == true) {
                putLineaDDA(ptX, ptY, ptX2, ptY2, Borde);
            }

        }

        return cord2d; // Devuelve el arreglo de coordenadas 2D
    }

    public int obtenerpuntos(double[][] cord3d) {
        int puntos = cord3d.length;
        return puntos;
    }

    public void putLineaDDA(Graphics gr, int x1, int y1, int x2, int y2, Color color) {
        float x = x1;
        float y = y1;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int pasos = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = (float) dx / (float) pasos;
        float yIncrement = (float) dy / (float) pasos;

        for (int i = 0; i <= pasos; i++) {
            int px = Math.round(x);
            int py = Math.round(y);
            putPixel(gr, px, py, color);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void putPixel(Graphics g, int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        g.drawImage(buffer, x, y, this);
    }

    public void rotacion(double[][] cord3d, int angulo, int eje) {

        while (true) {
            try {
                double x = 0;
                double y = 0;
                double z = 0;

                for (int i = 0; i < cord3d.length; i++) {
                    x += cord3d[i][0];
                    y += cord3d[i][1];
                    z += cord3d[i][2];
                }

                x /= obtenerpuntos(cord3d);
                y /= obtenerpuntos(cord3d);
                z /= obtenerpuntos(cord3d);

                int i = 0;
                do {
                    cord3d[i][0] -= x;
                    cord3d[i][1] -= y;
                    cord3d[i][2] -= z;

                    rotarPunto(cord3d[i], eje, angulo);

                    cord3d[i][0] += x;
                    cord3d[i][1] += y;
                    cord3d[i][2] += z;

                    i++;
                } while (i < cord3d.length);

                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    public void rotarPunto(double[] punto, int eje, double angulo) {
        double x = punto[0];
        double y = punto[1];
        double z = punto[2];

        double rotacionEnX = x;
        double rotacionEnY = y;
        double rotacionEnZ = z;

        if (eje == 1) {  //rotar en x
            rotacionEnX = x;
            rotacionEnY = y * Math.cos(angulo) - z * Math.sin(angulo);
            rotacionEnZ = y * Math.sin(angulo) + z * Math.cos(angulo);
        } else if (eje == 2) { //rotar en y
            rotacionEnX = x * Math.cos(angulo) + z * Math.sin(angulo);
            rotacionEnY = y;
            rotacionEnZ = -x * Math.sin(angulo) + z * Math.cos(angulo);
        } else if (eje == 3) { //rotar en z
            rotacionEnX = x * Math.cos(angulo) - y * Math.sin(angulo);
            rotacionEnY = x * Math.sin(angulo) + y * Math.cos(angulo);
            rotacionEnZ = z;
        }

        punto[0] = rotacionEnX;
        punto[1] = rotacionEnY;
        punto[2] = rotacionEnZ;
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    public class Puntos {

        int posx, posy;

        public Puntos(int x, int y) {
            this.posx = x;
            this.posy = y;
        }

        public int getposX() {
            return posx;
        }

        public void setposX(int x) {
            this.posx = x;
        }

        public int getposY() {
            return posy;
        }

        public void setposY(int y) {
            this.posy = y;
        }

    }

    public class Figura {

        Puntos puntoFig1, puntoFig2, puntoFig3, puntoFig4;

        public Figura(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
            int[][] puntos = new int[][]{{x1, y1}, {x2, y2}, {x3, y3}, {x4, y4}};
            for (int i = 0; i < puntos.length; i++) {
                switch (i) {
                    case 0:
                        puntoFig1 = new Puntos(puntos[i][0], puntos[i][1]);
                        break;
                    case 1:
                        puntoFig2 = new Puntos(puntos[i][0], puntos[i][1]);
                        break;
                    case 2:
                        puntoFig3 = new Puntos(puntos[i][0], puntos[i][1]);
                        break;
                    case 3:
                        puntoFig4 = new Puntos(puntos[i][0], puntos[i][1]);
                        break;
                    default:
                        break;
                }
            }
        }

        public Figura(Puntos pt1, Puntos pt2, Puntos pt3, Puntos pt4) {
            this.puntoFig1 = pt1;
            this.puntoFig2 = pt2;
            this.puntoFig3 = pt3;
            this.puntoFig4 = pt4;
        }

        public Puntos obtenerPT1() {
            return puntoFig1;
        }

        public void definirPT1(Puntos v1) {
            this.puntoFig1 = v1;
        }

        public Puntos obtenerPT2() {
            return puntoFig2;
        }

        public void definirPT2(Puntos v2) {
            this.puntoFig2 = v2;
        }

        public Puntos obtenerPT3() {
            return puntoFig3;
        }

        public void definirPT3(Puntos v3) {
            this.puntoFig3 = v3;
        }

        public Puntos obtenerPT4() {
            return puntoFig4;
        }

        public void definirPT4(Puntos v4) {
            this.puntoFig4 = v4;
        }

    }

    public void putLineaDDA(int x1, int y1, int x2, int y2, Color color) {
        float x = x1;
        float y = y1;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int pasos = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = (float) dx / (float) pasos;
        float yIncrement = (float) dy / (float) pasos;

        for (int i = 0; i <= pasos; i++) {
            int px = Math.round(x);
            int py = Math.round(y);
            addPixel(px, py, color);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void addPixel(int x, int y, Color color) {
        buffer.setRGB(x, y, color.getRGB());
    }

    public class Poligono {

        private Puntos[] vertices;
        private int numVertices;

        public Poligono(Puntos[] vertices) {
            this.vertices = vertices;
            this.numVertices = vertices.length;
        }

        public Puntos[] getVertices() {
            return vertices;
        }

        public void setVertices(Puntos[] vertices) {
            this.vertices = vertices;
        }

        public int getNumVertices() {
            return numVertices;
        }

        public void setNumVertices(int numVertices) {
            this.numVertices = numVertices;
        }
    }
    //PROYECTO GRAFICAS EN CURSO....
}
