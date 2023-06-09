package proyectofinalgraficas3erparcial;

import java.util.Comparator;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        {110, 160, 100}, // Vértice A
        {160, 150, 100}, // Vértice B
        {340, 400, 100}, // Vértice C
        {-30, 400, 100}, // Vértice D
        {190, 260, 200}, // Vértice E
        {360, 250, 200}, // Vértice F
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
    double[][] METEORITOPOLIGONAL = {
        //X,Y,Z
        {500, 700, 100}, // Vértice A
        {700, 600, 100}, // Vértice B
        {800, 550, 100}, // Vértice C
        {900, 600, 100}, // Vértice D
        {900, 500, 100}, // Vértice E
        {1000, 450, 100}, // Vértice F
        {900, 400, 100}, // Vértice G
        {1000, 300, 100}, // Vértice H
        {900, 300, 100}, // Vértice I
        {900, 200, 100}, // Vértice J
        {800, 200, 100}, // Vértice K
        {700, 150, 100}, // Vértice L
        {650, 220, 100}, // Vértice M
        {570, 150, 100}, // Vértice N
        {530, 225, 100}, // Vértice O
        {420, 200, 100}, // Vértice P
        {420, 345, 100}, // Vértice Q
        {327, 423, 100}, // Vértice R
        {448, 500, 100}, // Vértice S
        {428, 614, 100}, // Vértice T
        {498, 635, 100}, // Vértice U  21 VERTICES

        {500, 700, 200}, // Vértice A
        {700, 600, 200}, // Vértice B
        {800, 550, 200}, // Vértice C
        {900, 600, 200}, // Vértice D
        {900, 500, 200}, // Vértice E
        {1000, 450, 200}, // Vértice F
        {900, 400, 200}, // Vértice G
        {1000, 300, 200}, // Vértice H
        {900, 300, 200}, // Vértice I
        {900, 200, 200}, // Vértice J
        {800, 200, 200}, // Vértice K
        {700, 150, 200}, // Vértice L
        {650, 220, 200}, // Vértice M
        {570, 150, 200}, // Vértice N
        {530, 225, 200}, // Vértice O
        {420, 200, 200}, // Vértice P
        {420, 345, 200}, // Vértice Q
        {327, 423, 200}, // Vértice R
        {448, 500, 200}, // Vértice S
        {428, 614, 200}, // Vértice T
        {498, 635, 200}, // Vértice U
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
    double[][] Roca3D = {
        //X,Y,Z
        {200, 100, 100}, // Vértice A
        {100, 250, 100}, // Vértice B
        {100, 380, 100}, // Vértice C
        {270, 395, 100}, // Vértice D
        {368, 238, 100}, // Vértice E
        {282, 159, 100}, // Vértice F
        {200, 100, 150}, // Vértice A'
        {100, 250, 150}, // Vértice B'
        {100, 380, 150}, // Vértice C'
        {270, 395, 150}, // Vértice D'
        {368, 238, 150}, // Vértice E'
        {282, 159, 150}, // Vértice F'
    };
    double[][] MontañaPrespectiva1Punto = {
        {100, 600, 0}, // A (X, Y, Z)
        {500, 600, 0}, // B
        {100, 800, 0}, // C
        {300, 800, 0}, // D
        {-1920, 200, 500}, // E
        {-880, -550, 400}, // F
    };

    double[][] MontañaPrespectiva1Punto2 = {
        {700, 1200, 0}, // A (X, Y, Z)
        {1600, 900, 0}, // B
        {100, 800, 0}, // C
        {800, 800, 0}, // D
        {-12420, 200, 500}, // E
        {-2480, -2950, 400}, // F
    };
    double[][] MontañaPrespectiva1Punto3 = {
        {-140, 1200, 0}, // A (X, Y, Z)
        {1500, 1186, 0}, // B
        {-840, 1200, 0}, // C
        {2300, 1186, 0}, // D
        {-12120, 450, 500}, // E
        {2180, -1500, 400}, // F
    };
    double[][] ArbolOrtogonal1 = {
        {358, 734, 100}, // A (X, Y, Z)
        {518, 533, 100}, // B
        {411, 569, 100}, // C
        {510, 373, 100}, // D
        {400, 400, 100}, // E
        {500, 200, 100}, // F
        {400, 250, 100}, // G
        {400, 100, 100}, // H
        {300, 100, 100}, // I
        {300, 250, 100}, // J
        {200, 200, 100}, // K
        {300, 400, 100}, // L
        {200, 356, 100}, // N
        {300, 574, 100}, // O
        {200, 519, 100}, // P

        {358, 734, 200}, // A (X, Y, Z)
        {518, 533, 200}, // B
        {411, 569, 200}, // C
        {510, 373, 200}, // D
        {400, 400, 200}, // E
        {500, 200, 200}, // F
        {400, 250, 200}, // G
        {400, 100, 200}, // H
        {300, 100, 200}, // I
        {300, 250, 200}, // J
        {200, 200, 200}, // K
        {300, 400, 200}, // L
        {200, 356, 200}, // N
        {300, 574, 200}, // O
        {200, 519, 200}, // P
    };
    double[][] LavaAscendiente = {
        {90, 136, 100}, // Vértice A
        {270, 360, 100}, // Vértice B
        {730, 445, 100}, // Vértice C
        {860, 185, 100}, // Vértice D
        {90, 136, 200}, // Vértice A
        {270, 360, 200}, // Vértice B
        {730, 445, 200}, // Vértice C
        {860, 185, 200}, // Vértice D
    };
    double[][] LavaHumo = {
        {227, 54, 100}, // A
        {58, 64, 100}, // B
        {188, 100, 100}, // C
        {86, 120, 100}, // D
        {227, 54, 100}, // E
        {58, 64, 100}, // F
        {188, 100, 100}, // G
        {86, 120, 100} // H
    };
    List<Figura> VolcanOblicuo = new ArrayList<Figura>();
    List<Figura> FuegosMeteoroPoligonal1 = new ArrayList<Figura>();
    List<Poligono> MeteoroPoligonal1 = new ArrayList<Poligono>();
    int xMeteoro1, xMeteoro2;

    List<Figura> FuegosMeteoroPoligonal2 = new ArrayList<Figura>();
    List<Poligono> MeteoroPoligonal2 = new ArrayList<Poligono>();

    List<Figura> LavaOrtogonal = new ArrayList<Figura>();
    List<Poligono> HumoOblicua = new ArrayList<Poligono>();
    int xMeteoro12, yMeteoro22;
    int xLava, yLava;
    int xVolcan, yVolcan;
    int xHumoE, yHumoE, xHumoF, yHumoF, xHumoG, yHumoG, xHumoH, yHumoH;
    int cont = 0;
    boolean mostrarExplosion = false;

    ProyectoFinalGraficas3erParcial() {
        setTitle("Proyecto Final gráficas 3er Parcial, Kevin Giovanni Mahecha Cabuto, 20310027, 6P");
        setSize(1200, 1200);
        this.setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        buffer2D = (Graphics2D) buffer.createGraphics();

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                ColocarFondoDetras();
                ColocarMontaña1punto3(280, 210, 3);
                ColocarMontaña1punto(0, 300, 2);
                ColocarMontaña1punto(100, 400, 2);
                ColocarMontaña1punto2(480, 320, 3);
                ColocarFondoPiso(0, 600, 300);
                ColocarVolcan();

                ArbolOrtogonal1(10, 780, 5, Color.BLACK, Color.BLACK, Color.BLACK);
                ArbolOrtogonal1(20, 890, 5, Color.BLACK, Color.BLACK, Color.BLACK);
                ArbolOrtogonal1(600, 900, 5, Color.BLACK, Color.BLACK, Color.BLACK);
                ArbolOrtogonal1(200, 900, 5, Color.BLACK, Color.BLACK, Color.BLACK);
                ArbolOrtogonal1(350, 880, 5, Color.BLACK, Color.BLACK, Color.BLACK);
                ArbolOrtogonal1(460, 900, 5, Color.BLACK, Color.BLACK, Color.BLACK);
                ArbolOrtogonal1(800, 900, 5, Color.BLACK, Color.BLACK, Color.BLACK);
                imprimirPuntosYdibujarContorno(false);
                ColocarHumo(xHumoE, yHumoE, xHumoF, yHumoF, xHumoG, yHumoG, xHumoH, yHumoH, 1, Color.white, new Color(217, 14, 1), new Color(254, 102, 16), new int[]{1, 2, 3, 4}, mostrarExplosion);
                //DibujarRoca3D(true);
                //ColocarMeteoro(xMeteoro1, xMeteoro2, 7, FuegosMeteoroPoligonal1, MeteoroPoligonal1);
                //ColocarMeteoro2(xMeteoro12, xMeteoro22, 7, FuegosMeteoroPoligonal2, MeteoroPoligonal2);

                g.drawImage(buffer, 0, 0, null);
                g.dispose();

                System.out.println("CORDENADAS DE E: " + xHumoE);
                System.out.println("CORDENADAS DE H: " + xHumoH);
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

        Thread mover = new Thread(() -> rotacion.moverMeteoro(10, 150, 1, 10, rotacion.FuegosMeteoroPoligonal1, rotacion.MeteoroPoligonal1));
        Thread mover2 = new Thread(() -> rotacion.moverMeteoro2(10, 150, 10, 5, rotacion.FuegosMeteoroPoligonal2, rotacion.MeteoroPoligonal2));

        Thread moverLava = new Thread(() -> rotacion.moverLava(400, 400, 0, -1, rotacion.LavaOrtogonal));
        Thread moverVolcan = new Thread(() -> rotacion.moverVolcan(0, 0, 0, 0, rotacion.VolcanOblicuo));

        Thread contador = new Thread(() -> {
            try {
                moverLava.start();
                Thread.sleep(5000);
                moverVolcan.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        contador.start();
    }

    public void moverHumo(int posX, int posY, int desplazamientoX1, int desplazamientoY1, int desplazamientoX2, int desplazamientoY2, int desplazamientoX3, int desplazamientoY3, int desplazamientoX4, int desplazamientoY4, List<Poligono> Humo, int[] puntosAMover) {
        while (true) {
            try {
                // Actualizar las coordenadas del humo
                if (xHumoE == 0 && yHumoE == 0) {
                    xHumoE = posX + desplazamientoX1;
                    yHumoE = posY + desplazamientoY1;
                } else {
                    xHumoE += desplazamientoX1;
                    yHumoE += desplazamientoY1;
                }

                if (xHumoF == 0 && yHumoF == 0) {
                    xHumoF = posX + desplazamientoX2;
                    yHumoF = posY + desplazamientoY2;
                } else {
                    xHumoF += desplazamientoX2;
                    yHumoF += desplazamientoY2;
                }

                if (xHumoG == 0 && yHumoG == 0) {
                    xHumoG = posX + desplazamientoX3;
                    yHumoG = posY + desplazamientoY3;
                } else {
                    xHumoG += desplazamientoX3;
                    yHumoG += desplazamientoY3;
                }

                if (xHumoH == 0 && yHumoH == 0) {
                    xHumoH = posX + desplazamientoX4;
                    yHumoH = posY + desplazamientoY4;
                } else {
                    xHumoH += desplazamientoX4;
                    yHumoH += desplazamientoY4;
                }

                // Recorrer la lista de polígonos del humo y actualizar las coordenadas de los puntos seleccionados
                for (Poligono poligono : Humo) {
                    Puntos[] puntos = poligono.getVertices();

                    // Verificar si hay al menos 8 puntos en el polígono
                    if (puntos.length >= 8) {
                        for (int punto : puntosAMover) {
                            if (punto == 1) { // Mover punto E
                                puntos[4].setposX(puntos[4].getposX() + desplazamientoX1);
                                puntos[4].setposY(puntos[4].getposY() + desplazamientoY1);
                            } else if (punto == 2) { // Mover punto F
                                puntos[5].setposX(puntos[5].getposX() + desplazamientoX2);
                                puntos[5].setposY(puntos[5].getposY() + desplazamientoY2);
                            } else if (punto == 3) { // Mover punto G
                                puntos[6].setposX(puntos[6].getposX() + desplazamientoX3);
                                puntos[6].setposY(puntos[6].getposY() + desplazamientoY3);
                            } else if (punto == 4) { // Mover punto H
                                puntos[7].setposX(puntos[7].getposX() + desplazamientoX4);
                                puntos[7].setposY(puntos[7].getposY() + desplazamientoY4);
                            }
                        }
                    }
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
        }
    }

    public void ColocarHumo(int x, int y, int xf, int yf, int xg, int yg, int xh, int yh, double tamaño, Color c, Color inicial, Color colorfinal, int[] puntosAMover, Boolean Mostrar) {

        xHumoE = x;
        yHumoE = y;
        xHumoF = xf;
        yHumoF = yf;
        xHumoG = xg;
        yHumoG = yg;
        xHumoH = xh;
        yHumoH = yh;
        double xp = 4;
        double yp = 6;
        double zp = 3;
        double[][] cordenadas2d = hacerProyeccionOblicua(LavaHumo, 30, xp, yp, zp, Color.RED, false);

        // Escala proporcional al tamaño inverso
        double escala = 1.0 / tamaño;

        // Imprimir coordenadas 2D
        for (int i = 0; i < cordenadas2d.length; i++) {
            double[] punto = cordenadas2d[i];
        }

        // Obtener las coordenadas de los puntos E, F, G y H
        double xE = cordenadas2d[4][0] * escala;
        double yE = cordenadas2d[4][1] * escala;

        double xF = cordenadas2d[5][0] * escala;
        double yF = cordenadas2d[5][1] * escala;

        double xG = cordenadas2d[7][0] * escala;
        double yG = cordenadas2d[7][1] * escala;

        double xH = cordenadas2d[6][0] * escala;
        double yH = cordenadas2d[6][1] * escala;

        // Mover los puntos seleccionados
        for (int punto : puntosAMover) {
            switch (punto) {
                case 1: // Mover el punto E
                    xE += x;
                    yE += y;
                    break;
                case 2: // Mover el punto F
                    xF += xf;
                    yF += yf;
                    break;
                case 3: // Mover el punto G
                    xG += xg;
                    yG += yg;
                    break;
                case 4: // Mover el punto H
                    xH += xh;
                    yH += yh;
                    break;
                default:
                    System.out.println("Punto inválido");
                    break;
            }

            // Crear los puntos E, F, G y H con las nuevas coordenadas
            Puntos puntoE = new Puntos((int) xE, (int) yE);
            Puntos puntoF = new Puntos((int) xF, (int) yF);
            Puntos puntoG = new Puntos((int) xG, (int) yG);
            Puntos puntoH = new Puntos((int) xH, (int) yH);

            // Crear el polígono con los nuevos puntos E, F, G y H
            Puntos[] verticeslavahumo = {puntoE, puntoF, puntoG, puntoH};
            Poligono poligono = new Poligono(verticeslavahumo);

            // Agregar el polígono a la lista HumoOblicua
            HumoOblicua.add(poligono);

            if (Mostrar == true) {
                int puntoAX = (int) (cordenadas2d[0][0] * escala);
                int puntoAY = (int) (cordenadas2d[0][1] * escala);
                int puntoBX = (int) (cordenadas2d[1][0] * escala);
                int puntoBY = (int) (cordenadas2d[1][1] * escala);

                int puntoCX = (int) (cordenadas2d[2][0] * escala);
                int puntoCY = (int) (cordenadas2d[2][1] * escala);

                int puntoDX = (int) (cordenadas2d[3][0] * escala);
                int puntoDY = (int) (cordenadas2d[3][1] * escala);

                int puntoEX = ((int) (cordenadas2d[4][0] * escala) + x);
                int puntoEY = (int) ((int) (cordenadas2d[4][1] * escala) + y);

                int puntoFX = (int) (cordenadas2d[5][0] * escala) + xf;
                int puntoFY = (int) (cordenadas2d[5][1] * escala) + yf;

                int puntoGX = (int) (cordenadas2d[6][0] * escala) + xg;
                int puntoGY = (int) (cordenadas2d[6][1] * escala) + yg;

                int puntoHX = (int) (cordenadas2d[7][0] * escala) + xh;
                int puntoHY = (int) (cordenadas2d[7][1] * escala) + yh;

                putLineaDDA(puntoAX, puntoAY, puntoBX, puntoBY, Color.RED); // A a B
                putLineaDDA(puntoCX, puntoCY, puntoDX, puntoDY, Color.RED); // C a D
                putLineaDDA(puntoAX, puntoAY, puntoCX, puntoCY, Color.RED); // A a C
                putLineaDDA(puntoBX, puntoBY, puntoDX, puntoDY, Color.RED); // B a D
                putLineaDDA(puntoAX, puntoAY, puntoEX, puntoEY, Color.RED); // A a E
                putLineaDDA(puntoBX, puntoBY, puntoFX, puntoFY, Color.RED); // B a F
                putLineaDDA(puntoCX, puntoCY, puntoGX, puntoGY, Color.RED); // C a G
                putLineaDDA(puntoDX, puntoDY, puntoHX, puntoHY, Color.RED); // D a H
                putLineaDDA(puntoEX, puntoEY, puntoFX, puntoFY, Color.RED); // E a F
                putLineaDDA(puntoFX, puntoFY, puntoHX, puntoHY, Color.RED); // F a H
                putLineaDDA(puntoHX, puntoHY, puntoGX, puntoGY, Color.RED); // H a G
                putLineaDDA(puntoGX, puntoGY, puntoEX, puntoEY, Color.RED); // G a E

                Puntos[] carafrontal = {new Puntos(puntoGX, puntoGY), new Puntos(puntoHX, puntoHY), new Puntos(puntoDX, puntoDY), new Puntos(puntoCX, puntoCY)}; //G,H,D,C
                Puntos[] caralateralderecha = {new Puntos(puntoHX, puntoHY), new Puntos(puntoFX, puntoFY), new Puntos(puntoBX, puntoBY), new Puntos(puntoDX, puntoDY)}; //HFDB
                Puntos[] caralateralizquierda = {new Puntos(puntoGX, puntoGY), new Puntos(puntoEX, puntoEY), new Puntos(puntoAX, puntoAY), new Puntos(puntoCX, puntoCY)}; //GEAC
                Puntos[] piso = {new Puntos(puntoAX, puntoAY), new Puntos(puntoBX, puntoBY), new Puntos(puntoDX, puntoDY), new Puntos(puntoCX, puntoCY)}; //ABDC
                Puntos[] trasera = {new Puntos(puntoEX, puntoEY), new Puntos(puntoFX, puntoFY), new Puntos(puntoBX, puntoBY), new Puntos(puntoAX, puntoAY)}; //EFBA
                Puntos[] techo = {new Puntos(puntoEX, puntoEY), new Puntos(puntoFX, puntoFY), new Puntos(puntoHX, puntoHY), new Puntos(puntoGX, puntoGY)}; //EFGH

                Poligono pcara4 = new Poligono(piso);
                RellenarPoligono(pcara4, c, inicial, colorfinal, false);

                Poligono traseras = new Poligono(trasera);
                RellenarPoligono(traseras, c, inicial, colorfinal, false);

                Poligono pcara1 = new Poligono(carafrontal);
                RellenarPoligono(pcara1, c, inicial, colorfinal, false);

                Poligono pcara2 = new Poligono(caralateralderecha);
                RellenarPoligono(pcara2, c, new Color(217, 14, 1), new Color(239, 178, 15), false);

                Poligono pcara3 = new Poligono(caralateralizquierda);
                RellenarPoligono(pcara3, c, new Color(217, 14, 1), new Color(239, 178, 15), false);

                Poligono techos = new Poligono(techo);
                RellenarPoligono(techos, c, inicial, colorfinal, false);
            }

            System.out.println(); // Agregar una línea en blanco después de imprimir los puntos
        }
    }

    public void LavaAscendiente(int x, int y, double tamaño, Color c, Color inicial, Color colorfinal) {

        xLava = x;
        yLava = y;

        // Obtener los puntos proyectados en 2D
        double[][] cord2d = ProyeccionOrtogonal(LavaAscendiente);

        // Escala proporcional al tamaño inverso
        double escala = 1.0 / tamaño;

        // Imprimir los puntos proyectados en 2D
        System.out.println("Puntos Ortogonales Lava ascendiente en 2D:");
        for (int i = 0; i < cord2d.length; i++) {
            System.out.println("Punto " + (i + 1) + ": (" + cord2d[i][0] + ", " + cord2d[i][1] + ")");

            // Dibujar los puntos en la ventana
            int pixelX = (int) (cord2d[i][0] * escala) + x;
            int pixelY = (int) (cord2d[i][1] * escala) + y;
            addPixel(pixelX, pixelY, Color.RED);
        }
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, Color.RED);
        putLineaDDA((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, (int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, Color.RED);
        putLineaDDA((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, (int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, Color.RED);
        putLineaDDA((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, (int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, Color.RED);

        Figura Lava = new Figura(
                (int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, // Punto 1 (A)
                (int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, // Punto 2 (B)
                (int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, // Punto 3 (C)
                (int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y // Punto 4 (D)
        );
        LavaOrtogonal.add(Lava);
        RellenarFiguraScanLine(Lava, Color.BLACK, new Color(217, 14, 1), new Color(254, 102, 16), false);
    }

    public void ArbolOrtogonal1(int x, int y, double tamaño, Color c, Color inicial, Color colorfinal) {
        // Obtener los puntos proyectados en 2D
        double[][] cord2d = ProyeccionOrtogonal(ArbolOrtogonal1);

        // Escala proporcional al tamaño inverso
        double escala = 1.0 / tamaño;

        // Imprimir los puntos proyectados en 2D
        System.out.println("Puntos Ortogonales del arbol 1 en 2D:");
        for (int i = 0; i < cord2d.length; i++) {
            System.out.println("Punto " + (i + 1) + ": (" + cord2d[i][0] + ", " + cord2d[i][1] + ")");

            // Dibujar los puntos en la ventana
            int pixelX = (int) (cord2d[i][0] / tamaño) + x;  // Invierte la coordenada X y ajusta el ancho
            int pixelY = (int) (-cord2d[i][1] / tamaño) + y;  // No invierte la coordenada Y

        }

        Puntos[] verticesArbol = {
            new Puntos((int) (cord2d[0][0] * escala) + x, (int) (-cord2d[0][1] * escala) + y),
            new Puntos((int) (cord2d[1][0] * escala) + x, (int) (-cord2d[1][1] * escala) + y),
            new Puntos((int) (cord2d[2][0] * escala) + x, (int) (-cord2d[2][1] * escala) + y),
            new Puntos((int) (cord2d[3][0] * escala) + x, (int) (-cord2d[3][1] * escala) + y),
            new Puntos((int) (cord2d[4][0] * escala) + x, (int) (-cord2d[4][1] * escala) + y),
            new Puntos((int) (cord2d[5][0] * escala) + x, (int) (-cord2d[5][1] * escala) + y),
            new Puntos((int) (cord2d[6][0] * escala) + x, (int) (-cord2d[6][1] * escala) + y),
            new Puntos((int) (cord2d[7][0] * escala) + x, (int) (-cord2d[7][1] * escala) + y),
            new Puntos((int) (cord2d[8][0] * escala) + x, (int) (-cord2d[8][1] * escala) + y),
            new Puntos((int) (cord2d[9][0] * escala) + x, (int) (-cord2d[9][1] * escala) + y),
            new Puntos((int) (cord2d[10][0] * escala) + x, (int) (-cord2d[10][1] * escala) + y),
            new Puntos((int) (cord2d[11][0] * escala) + x, (int) (-cord2d[11][1] * escala) + y),
            new Puntos((int) (cord2d[12][0] * escala) + x, (int) (-cord2d[12][1] * escala) + y),
            new Puntos((int) (cord2d[13][0] * escala) + x, (int) (-cord2d[13][1] * escala) + y),
            new Puntos((int) (cord2d[14][0] * escala) + x, (int) (-cord2d[14][1] * escala) + y)
        };

        Poligono poligono = new Poligono(verticesArbol);
        Color colorBorde = new Color(37, 37, 37);
        Color colorBorde1 = new Color(135, 92, 68);
        RellenarPoligono(poligono, colorBorde, new Color(60, 172, 3), new Color(135, 92, 68), false);
    }

    public void ColocarMontaña1punto3(int x, int y, int tamaño) {

        // Parámetros del punto de fuga
        double xc = 100;
        double yc = 50;
        double zc = 30;

        // Realiza proyección del cubo en 3D a 2D
        double[][] cord2d = proyectarPunto1(MontañaPrespectiva1Punto3, xc, yc, zc);

        // Escala proporcional al tamaño inverso
        double escala = 1.0 / tamaño;

        // Unir puntos con líneas y aplicar la escala
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y);
        putLineaDDA((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, (int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y);
        putLineaDDA((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, (int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y);
        putLineaDDA((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, (int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y);
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y);
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y);
        putLineaDDA((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y);
        putLineaDDA((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, (int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y);
        putLineaDDA((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, (int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y);
        putLineaDDA((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y);

// Guardar los puntos de la montaña en el orden F, A, C
        Puntos[] vertices = new Puntos[3];
        vertices[0] = new Puntos((int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y); // F
        vertices[1] = new Puntos((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y); // A
        vertices[2] = new Puntos((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y); // C

// Guardar los puntos de la montaña en el orden F, E, B, A
        Puntos[] vertices2 = new Puntos[4];
        vertices2[0] = new Puntos((int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y); // F
        vertices2[1] = new Puntos((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y); // E
        vertices2[2] = new Puntos((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y); // B
        vertices2[3] = new Puntos((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y); // A

        // Guardar los puntos de la montaña en el orden E, D, B
        Puntos[] vertices3 = new Puntos[3];
        vertices3[0] = new Puntos((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y); // E
        vertices3[1] = new Puntos((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y); // D
        vertices3[2] = new Puntos((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y); // B

        Poligono poligono = new Poligono(vertices);
        Poligono poligono2 = new Poligono(vertices2);
        Poligono poligono3 = new Poligono(vertices3);
        Color colorBorde = new Color(37, 37, 37);
        Color colorBorde1 = new Color(135, 92, 68);
        RellenarPoligono(poligono2, colorBorde1, new Color(135, 92, 68), new Color(37, 37, 37), true);
        RellenarPoligono(poligono3, colorBorde, new Color(37, 37, 37), new Color(135, 92, 68), true);
        RellenarPoligono(poligono, colorBorde1, new Color(135, 92, 68), new Color(135, 92, 68), true);

    }

    public void ColocarMontaña1punto2(int x, int y, int tamaño) {

        // Parámetros del punto de fuga
        double xc = 100;
        double yc = 50;
        double zc = 30;

        // Realiza proyección del cubo en 3D a 2D
        double[][] cord2d = proyectarPunto1(MontañaPrespectiva1Punto2, xc, yc, zc);

        // Escala proporcional al tamaño inverso
        double escala = 1.0 / tamaño;

        // Unir puntos con líneas y aplicar la escala
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y);
        putLineaDDA((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, (int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y);
        putLineaDDA((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, (int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y);
        putLineaDDA((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, (int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y);
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y);
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y);
        putLineaDDA((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y);
        putLineaDDA((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, (int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y);
        putLineaDDA((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, (int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y);
        putLineaDDA((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y);

// Guardar los puntos de la montaña en el orden F, A, C
        Puntos[] vertices = new Puntos[3];
        vertices[0] = new Puntos((int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y); // F
        vertices[1] = new Puntos((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y); // A
        vertices[2] = new Puntos((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y); // C

// Guardar los puntos de la montaña en el orden F, E, B, A
        Puntos[] vertices2 = new Puntos[4];
        vertices2[0] = new Puntos((int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y); // F
        vertices2[1] = new Puntos((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y); // E
        vertices2[2] = new Puntos((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y); // B
        vertices2[3] = new Puntos((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y); // A

        // Guardar los puntos de la montaña en el orden E, D, B
        Puntos[] vertices3 = new Puntos[3];
        vertices3[0] = new Puntos((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y); // E
        vertices3[1] = new Puntos((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y); // D
        vertices3[2] = new Puntos((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y); // B

        Poligono poligono = new Poligono(vertices);
        Poligono poligono2 = new Poligono(vertices2);
        Poligono poligono3 = new Poligono(vertices3);
        Color colorBorde = new Color(37, 37, 37);
        Color colorBorde1 = new Color(135, 92, 68);

        RellenarPoligono(poligono2, colorBorde1, new Color(135, 92, 68), new Color(37, 37, 37), true);
        RellenarPoligono(poligono3, colorBorde, new Color(37, 37, 37), new Color(135, 92, 68), true);
        RellenarPoligono(poligono, colorBorde1, new Color(135, 92, 68), new Color(135, 92, 68), true);

    }

    public void ColocarMontaña1punto(int x, int y, int tamaño) {

        // Parámetros del punto de fuga
        double xc = 100;
        double yc = 50;
        double zc = 30;

        // Realiza proyección del cubo en 3D a 2D
        double[][] cord2d = proyectarPunto1(MontañaPrespectiva1Punto, xc, yc, zc);

        // Escala proporcional al tamaño inverso
        double escala = 1.0 / tamaño;

        // Unir puntos con líneas y aplicar la escala
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, (int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, (int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, (int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y, (int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y, (int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y, Color.BLACK);
        putLineaDDA((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y, (int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y, Color.BLACK);

// Guardar los puntos de la montaña en el orden F, A, C
        Puntos[] vertices = new Puntos[3];
        vertices[0] = new Puntos((int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y); // F
        vertices[1] = new Puntos((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y); // A
        vertices[2] = new Puntos((int) (cord2d[2][0] * escala) + x, (int) (cord2d[2][1] * escala) + y); // C

// Guardar los puntos de la montaña en el orden F, E, B, A
        Puntos[] vertices2 = new Puntos[4];
        vertices2[0] = new Puntos((int) (cord2d[5][0] * escala) + x, (int) (cord2d[5][1] * escala) + y); // F
        vertices2[1] = new Puntos((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y); // E
        vertices2[2] = new Puntos((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y); // B
        vertices2[3] = new Puntos((int) (cord2d[0][0] * escala) + x, (int) (cord2d[0][1] * escala) + y); // A

        // Guardar los puntos de la montaña en el orden E, D, B
        Puntos[] vertices3 = new Puntos[3];
        vertices3[0] = new Puntos((int) (cord2d[4][0] * escala) + x, (int) (cord2d[4][1] * escala) + y); // E
        vertices3[1] = new Puntos((int) (cord2d[3][0] * escala) + x, (int) (cord2d[3][1] * escala) + y); // D
        vertices3[2] = new Puntos((int) (cord2d[1][0] * escala) + x, (int) (cord2d[1][1] * escala) + y); // B

        Poligono poligono = new Poligono(vertices);
        Poligono poligono2 = new Poligono(vertices2);
        Poligono poligono3 = new Poligono(vertices3);
        Color colorBorde = Color.BLACK;
        Color colorRellenoInicial = new Color(37, 37, 37);
        Color colorRellenoFinal = new Color(105, 65, 44);
        RellenarPoligono(poligono2, colorBorde, new Color(37, 37, 37), new Color(135, 92, 68), false);
        RellenarPoligono(poligono3, colorBorde, colorRellenoInicial, colorRellenoFinal, false);

        RellenarPoligono(poligono, colorBorde, colorRellenoInicial, colorRellenoFinal, false);

    }

    public double[][] proyectarPunto1(double[][] cord3d, double xc, double yc, double zc) {
        int puntosObtenidos = cord3d.length;
        double[][] cord2d = new double[puntosObtenidos][2];

        for (int i = 0; i < puntosObtenidos; i++) {
            double x1 = cord3d[i][0];
            double y1 = cord3d[i][1];
            double z1 = cord3d[i][2];

            double u = zc / (zc - z1);
            double xProyectado = xc + (x1 - xc) * u;
            double yProyectado = yc + (y1 - yc) * u;

            cord2d[i][0] = xProyectado;
            cord2d[i][1] = yProyectado;
        }

        return cord2d;
    }

    public void DibujarRoca3D(Boolean OcultarCaras) {
        double xp = 4;
        double yp = 6;
        double zp = 3;
        double[][] cordenadas2d = hacerProyeccionOblicua(Roca3D, 30, xp, yp, zp, Color.RED, false);

        // Imprimir coordenadas 2D
        for (int i = 0; i < cordenadas2d.length; i++) {
            double[] punto = cordenadas2d[i];
            System.out.println("Punto " + (i + 1) + ": (" + punto[0] + ", " + punto[1] + ")");
            addPixel((int) punto[0], (int) punto[1], Color.RED);
        }

        System.out.println(); // Agregar una línea en blanco después de imprimir los puntos

        putLineaDDA((int) cordenadas2d[0][0], (int) cordenadas2d[0][1], (int) cordenadas2d[1][0], (int) cordenadas2d[1][1], Color.RED); // A -> B
        putLineaDDA((int) cordenadas2d[1][0], (int) cordenadas2d[1][1], (int) cordenadas2d[2][0], (int) cordenadas2d[2][1], Color.RED); // B -> C
        putLineaDDA((int) cordenadas2d[2][0], (int) cordenadas2d[2][1], (int) cordenadas2d[3][0], (int) cordenadas2d[3][1], Color.RED); // C -> D
        putLineaDDA((int) cordenadas2d[3][0], (int) cordenadas2d[3][1], (int) cordenadas2d[4][0], (int) cordenadas2d[4][1], Color.RED); // D -> E
        putLineaDDA((int) cordenadas2d[4][0], (int) cordenadas2d[4][1], (int) cordenadas2d[5][0], (int) cordenadas2d[5][1], Color.RED); // Une E con F
        putLineaDDA((int) cordenadas2d[5][0], (int) cordenadas2d[5][1], (int) cordenadas2d[0][0], (int) cordenadas2d[0][1], Color.RED); // Une F con A
        putLineaDDA((int) cordenadas2d[4][0], (int) cordenadas2d[4][1], (int) cordenadas2d[6][0], (int) cordenadas2d[6][1], Color.RED); // Une E con G
        putLineaDDA((int) cordenadas2d[5][0], (int) cordenadas2d[5][1], (int) cordenadas2d[6][0], (int) cordenadas2d[6][1], Color.RED); // Une F con G
        putLineaDDA((int) cordenadas2d[0][0], (int) cordenadas2d[0][1], (int) cordenadas2d[6][0], (int) cordenadas2d[6][1], Color.RED); // Une A con G
        putLineaDDA((int) cordenadas2d[2][0], (int) cordenadas2d[2][1], (int) cordenadas2d[7][0], (int) cordenadas2d[7][1], Color.RED); // Une C con H
        putLineaDDA((int) cordenadas2d[1][0], (int) cordenadas2d[1][1], (int) cordenadas2d[7][0], (int) cordenadas2d[7][1], Color.RED); // Une B con H
        putLineaDDA((int) cordenadas2d[3][0], (int) cordenadas2d[3][1], (int) cordenadas2d[6][0], (int) cordenadas2d[6][1], Color.RED); // Une D con G
        putLineaDDA((int) cordenadas2d[6][0], (int) cordenadas2d[6][1], (int) cordenadas2d[7][0], (int) cordenadas2d[7][1], Color.RED); // Une G con H
        putLineaDDA((int) cordenadas2d[0][0], (int) cordenadas2d[0][1], (int) cordenadas2d[6][0], (int) cordenadas2d[6][1], Color.RED); // Une A con G

    }

    public void ColocarMeteoro2(int x, int y, double tamaño, List<Figura> FuegosMeteoro, List<Poligono> CuerpoMeteoro) {

        xMeteoro12 = x;
        yMeteoro22 = y;
        double meteoroTamaño = 7;

        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 80, y + 75, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 100, y + 100, 5, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 110, y + 80, 6, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 115, y + 70, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 125, y + 80, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 132, y + 77, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 132, y + 60, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 112, y + 70, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 110, y + 75, 5, 6, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 110, y + 70, 5, 6, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal2, x + 100, y + 70, 5, 6, Color.RED, Color.RED, Color.ORANGE);

        double[][] cord2d = ProyeccionOrtogonal(METEORITOPOLIGONAL);

        // Aplicar transformaciones de posición (x, y) y tamaño inverso a los puntos en 2D
        for (int i = 0; i < cord2d.length; i++) {
            cord2d[i][0] = cord2d[i][0] * (1 / meteoroTamaño) + x;
            cord2d[i][1] = cord2d[i][1] * (1 / meteoroTamaño) + y;
        }

        // Definir el orden de los índices de los vértices en sentido horario
        int[] indices = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};

        Puntos[] vertices = new Puntos[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            vertices[i] = new Puntos((int) cord2d[index][0], (int) cord2d[index][1]);
        }

        Poligono poligono = new Poligono(vertices);

        MeteoroPoligonal2.add(poligono);
        Color colorBorde = Color.BLACK;
        Color colorRellenoInicial = new Color(37, 37, 37);
        Color colorRellenoFinal = new Color(105, 65, 44);

        RellenarPoligono(poligono, colorBorde, colorRellenoInicial, colorRellenoFinal, false);
    }

    public void ColocarMeteoro(int x, int y, double tamaño, List<Figura> FuegosMeteoro, List<Poligono> CuerpoMeteoro) {

        xMeteoro1 = x;
        xMeteoro2 = y;
        double meteoroTamaño = 7;

        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 80, y + 75, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 100, y + 100, 5, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 110, y + 80, 6, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 115, y + 70, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 125, y + 80, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 132, y + 77, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 132, y + 60, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 112, y + 70, 7, 4, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 110, y + 75, 5, 6, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 110, y + 70, 5, 6, Color.RED, Color.RED, Color.ORANGE);
        FuegoOrtogonal(FuegosMeteoroPoligonal1, x + 100, y + 70, 5, 6, Color.RED, Color.RED, Color.ORANGE);

        double[][] cord2d = ProyeccionOrtogonal(METEORITOPOLIGONAL);

        // Aplicar transformaciones de posición (x, y) y tamaño inverso a los puntos en 2D
        for (int i = 0; i < cord2d.length; i++) {
            cord2d[i][0] = cord2d[i][0] * (1 / meteoroTamaño) + x;
            cord2d[i][1] = cord2d[i][1] * (1 / meteoroTamaño) + y;
        }

        // Definir el orden de los índices de los vértices en sentido horario
        int[] indices = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};

        Puntos[] vertices = new Puntos[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            vertices[i] = new Puntos((int) cord2d[index][0], (int) cord2d[index][1]);
        }

        Poligono poligono = new Poligono(vertices);

        MeteoroPoligonal1.add(poligono);
        Color colorBorde = Color.BLACK;
        Color colorRellenoInicial = new Color(37, 37, 37);
        Color colorRellenoFinal = new Color(105, 65, 44);

        RellenarPoligono(poligono, colorBorde, colorRellenoInicial, colorRellenoFinal, false);
    }

    public void FuegoOrtogonal(List<Figura> Objeto, int x, int y, double tamaño, double ancho, Color c, Color inicial, Color colorfinal) {
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
        RellenarFiguraScanLine(FuegoOrtogonal, inicial, inicial, colorfinal, false);
        FuegosMeteoroPoligonal1.add(FuegoOrtogonal);
    }

    public void moverMeteoro2(int posX, int posY, int desplazamientoX, int desplazamientoY, List<Figura> FuegosMeteoro, List<Poligono> CuerpoMeteoro) {
        while (true) {
            try {
                // Construir la matriz de transformación de traslación
                double[][] matrizTranslacion = {
                    {1, 0, desplazamientoX},
                    {0, 1, desplazamientoY},
                    {0, 0, 1}
                };

                // Actualizar las coordenadas del meteoro
                if (xMeteoro12 == 0 && yMeteoro22 == 0) {
                    xMeteoro12 = posX + desplazamientoX;
                    yMeteoro22 = posY + desplazamientoY;
                } else {
                    xMeteoro12 += desplazamientoX;
                    yMeteoro22 += desplazamientoY;
                }

                // Recorrer la lista de figuras de fuego del meteoro y actualizar sus coordenadas
                List<Figura> copiaFuegosMeteoro = new ArrayList<>(FuegosMeteoroPoligonal2);
                for (Figura fuego : copiaFuegosMeteoro) {
                    double[][] puntos = {
                        {fuego.getX()[0], fuego.getX()[1], fuego.getX()[2], fuego.getX()[3]}, // coordenadas x de los vértices
                        {fuego.getY()[0], fuego.getY()[1], fuego.getY()[2], fuego.getY()[3]}, // coordenadas y de los vértices
                        {1, 1, 1, 1} // coordenada homogénea de cada vértice
                    };
                    double[][] puntosTransformados = matrizPorPuntos(matrizTranslacion, puntos);
                    fuego.setX(puntosTransformados[0]);
                    fuego.setY(puntosTransformados[1]);
                }
                List<Poligono> copiaCuerpoMeteoro = new ArrayList<>(MeteoroPoligonal2);
                // Recorrer la lista de polígonos del meteoro y actualizar sus coordenadas
                for (Poligono poligono : copiaCuerpoMeteoro) {
                    double[][] puntos = new double[3][poligono.getVertices().length];
                    for (int i = 0; i < poligono.getVertices().length; i++) {
                        puntos[0][i] = poligono.getVertices()[i].getposX();
                        puntos[1][i] = poligono.getVertices()[i].getposY();
                        puntos[2][i] = 1; // Coordenada homogénea constante
                    }
                    double[][] puntosTransformados = matrizPorPuntos(matrizTranslacion, puntos);
                    for (int i = 0; i < poligono.getVertices().length; i++) {
                        poligono.getVertices()[i].setposX((int) puntosTransformados[0][i]);
                        poligono.getVertices()[i].setposY((int) puntosTransformados[1][i]);
                    }
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
        }

    }

    public void moverVolcan(int posX, int posY, int desplazamientoX, int desplazamientoY, List<Figura> Volcan) {

        while (true) {
            System.out.println("X VOLCAN : " + xVolcan + "-  Y VOLVAN : " + yVolcan);
            try {
                // Construir la matriz de transformación de traslación
                double[][] matrizTranslacion = {
                    {1, 0, desplazamientoX},
                    {0, 1, desplazamientoY},
                    {0, 0, 1}
                };

                // Actualizar las coordenadas de la lava
                if (xVolcan == 0 && yVolcan == 0) {
                    xVolcan = posX + desplazamientoX;
                    yVolcan = posY + desplazamientoY;
                } else {
                    xVolcan += desplazamientoX;
                    yVolcan += desplazamientoY;
                }

                // Recorrer la lista de figuras de lava y actualizar sus coordenadas
                List<Figura> copiaVolcan = new ArrayList<>(Volcan);
                for (Figura volcan : copiaVolcan) {
                    double[][] puntos = {
                        {volcan.obtenerPT1().getposX(), volcan.obtenerPT2().getposX(), volcan.obtenerPT3().getposX(), volcan.obtenerPT4().getposX()}, // coordenadas x de los vértices
                        {volcan.obtenerPT1().getposY(), volcan.obtenerPT2().getposY(), volcan.obtenerPT3().getposY(), volcan.obtenerPT4().getposY()}, // coordenadas y de los vértices
                        {1, 1, 1, 1} // coordenada homogénea de cada vértice
                    };

                    // Aplicar la transformación de traslación
                    double[][] puntosTranslacion = matrizPorPuntos(matrizTranslacion, puntos);

                    // Actualizar las coordenadas de la figura de lava
                    volcan.obtenerPT1().setposX((int) puntosTranslacion[0][0]);
                    volcan.obtenerPT2().setposX((int) puntosTranslacion[0][1]);
                    volcan.obtenerPT3().setposX((int) puntosTranslacion[0][2]);
                    volcan.obtenerPT4().setposX((int) puntosTranslacion[0][3]);
                    volcan.obtenerPT1().setposY((int) puntosTranslacion[1][0]);
                    volcan.obtenerPT2().setposY((int) puntosTranslacion[1][1]);
                    volcan.obtenerPT3().setposY((int) puntosTranslacion[1][2]);
                    volcan.obtenerPT4().setposY((int) puntosTranslacion[1][3]);
                }

                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();

            int limiteX = 0;

            if (xVolcan < limiteX) {
                moverVolcan(0, 0, 2, 0, VolcanOblicuo);
            } else {
                moverVolcan(0, 0, -2, 0, VolcanOblicuo);
            }
        }
    }

    public void moverLava(int posX, int posY, int desplazamientoX, int desplazamientoY, List<Figura> Lava) {
        while (true) {
            System.out.println("X LAVA: " + xLava + " - Y LAVA: " + yLava);
            try {
                // Construir la matriz de transformación de traslación
                double[][] matrizTranslacion = {
                    {1, 0, desplazamientoX},
                    {0, 1, desplazamientoY},
                    {0, 0, 1}
                };

                // Actualizar las coordenadas de la lava
                if (xLava == 0 && yLava == 0) {
                    xLava = posX + desplazamientoX;
                    yLava = posY + desplazamientoY;
                } else {
                    xLava += desplazamientoX;
                    yLava += desplazamientoY;
                }

                // Recorrer la lista de figuras de lava y actualizar sus coordenadas
                List<Figura> copiaLava = new ArrayList<>(Lava);
                for (Figura lava : copiaLava) {
                    double[][] puntos = {
                        {lava.obtenerPT1().getposX(), lava.obtenerPT2().getposX(), lava.obtenerPT3().getposX(), lava.obtenerPT4().getposX()}, // coordenadas x de los vértices
                        {lava.obtenerPT1().getposY(), lava.obtenerPT2().getposY(), lava.obtenerPT3().getposY(), lava.obtenerPT4().getposY()}, // coordenadas y de los vértices
                        {1, 1, 1, 1} // coordenada homogénea de cada vértice
                    };

                    // Aplicar la transformación de traslación
                    double[][] puntosTranslacion = matrizPorPuntos(matrizTranslacion, puntos);

                    // Actualizar las coordenadas de la figura de lava
                    lava.obtenerPT1().setposX((int) puntosTranslacion[0][0]);
                    lava.obtenerPT2().setposX((int) puntosTranslacion[0][1]);
                    lava.obtenerPT3().setposX((int) puntosTranslacion[0][2]);
                    lava.obtenerPT4().setposX((int) puntosTranslacion[0][3]);
                    lava.obtenerPT1().setposY((int) puntosTranslacion[1][0]);
                    lava.obtenerPT2().setposY((int) puntosTranslacion[1][1]);
                    lava.obtenerPT3().setposY((int) puntosTranslacion[1][2]);
                    lava.obtenerPT4().setposY((int) puntosTranslacion[1][3]);
                }

                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
            int sacudida = -1;
            // Verificar la condición de detención
            if (yLava == 330) {
                try {
                    // Pausar durante 3 segundos
                    Thread.sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                moverLava(posX, posY, 0, sacudida, Lava);
                //EXPLOSION NUBE DE LAVA
                sacudida = 8;
                mostrarExplosion = true;
                moverHumo(0, 0, -3, -2, 3, -2, -2, -3, -1, -2, HumoOblicua, new int[]{1, 2, 3, 4});
                if(xHumoE==290){
                    break;
                }
            }

            if (yLava == 305) {
                break;

            }

        }
    }

    public void moverMeteoro(int posX, int posY, int desplazamientoX, int desplazamientoY, List<Figura> FuegosMeteoro, List<Poligono> CuerpoMeteoro) {
        while (true) {
            try {
                // Construir la matriz de transformación de traslación
                double[][] matrizTranslacion = {
                    {1, 0, desplazamientoX},
                    {0, 1, desplazamientoY},
                    {0, 0, 1}
                };

                // Actualizar las coordenadas del meteoro
                if (xMeteoro1 == 0 && xMeteoro2 == 0) {
                    xMeteoro1 = posX + desplazamientoX;
                    xMeteoro2 = posY + desplazamientoY;
                } else {
                    xMeteoro1 += desplazamientoX;
                    xMeteoro2 += desplazamientoY;
                }

                // Recorrer la lista de figuras de fuego del meteoro y actualizar sus coordenadas
                List<Figura> copiaFuegosMeteoro = new ArrayList<>(FuegosMeteoroPoligonal1);
                for (Figura fuego : copiaFuegosMeteoro) {
                    double[][] puntos = {
                        {fuego.getX()[0], fuego.getX()[1], fuego.getX()[2], fuego.getX()[3]}, // coordenadas x de los vértices
                        {fuego.getY()[0], fuego.getY()[1], fuego.getY()[2], fuego.getY()[3]}, // coordenadas y de los vértices
                        {1, 1, 1, 1} // coordenada homogénea de cada vértice
                    };
                    double[][] puntosTransformados = matrizPorPuntos(matrizTranslacion, puntos);
                    fuego.setX(puntosTransformados[0]);
                    fuego.setY(puntosTransformados[1]);
                }
                List<Poligono> copiaCuerpoMeteoro = new ArrayList<>(MeteoroPoligonal1);
                // Recorrer la lista de polígonos del meteoro y actualizar sus coordenadas
                for (Poligono poligono : copiaCuerpoMeteoro) {
                    double[][] puntos = new double[3][poligono.getVertices().length];
                    for (int i = 0; i < poligono.getVertices().length; i++) {
                        puntos[0][i] = poligono.getVertices()[i].getposX();
                        puntos[1][i] = poligono.getVertices()[i].getposY();
                        puntos[2][i] = 1; // Coordenada homogénea constante
                    }
                    double[][] puntosTransformados = matrizPorPuntos(matrizTranslacion, puntos);
                    for (int i = 0; i < poligono.getVertices().length; i++) {
                        poligono.getVertices()[i].setposX((int) puntosTransformados[0][i]);
                        poligono.getVertices()[i].setposY((int) puntosTransformados[1][i]);
                    }
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
        }

    }

    private double[][] matrizPorPuntos(double[][] matriz, double[][] puntos) {
        double[][] resultado = new double[3][puntos[0].length];
        for (int j = 0; j < puntos[0].length; j++) {
            for (int i = 0; i < 3; i++) {

                resultado[i][j] = matriz[i][0] * puntos[0][j] + matriz[i][1] * puntos[1][j] + matriz[i][2] * puntos[2][j];
            }
        }
        return resultado;
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

    public void RellenarObjeto(int x, int y, boolean Contorno, double[][] Objeto, Color Superior, Color Inferior, Color Izquierda, Color Derecha, Color Frontal, Color Trasera, boolean OcultarCaras, Color Final) {

        xVolcan = x;
        yVolcan = y;

        double xp = 4;
        double yp = 6;
        double zp = 3;
        boolean carasocultas = true;
        double[][] puntos2D = hacerProyeccionOblicua(Objeto, 60, xp, yp, zp, new Color(56, 31, 48), Contorno);

        // Imprimir coordenadas 2D
        for (int i = 0; i < puntos2D.length; i++) {
            double[] punto = puntos2D[i];
            System.out.println("Punto " + (i + 1) + ": (" + (punto[0] + x) + ", " + (punto[1] + y) + ")");
        }

        Figura cuadradoTrasero = new Figura(
                (int) puntos2D[4][0] + x, (int) puntos2D[4][1] + y, // Punto 5
                (int) puntos2D[5][0] + x, (int) puntos2D[5][1] + y, // Punto 6
                (int) puntos2D[6][0] + x, (int) puntos2D[6][1] + y, // Punto 7
                (int) puntos2D[7][0] + x, (int) puntos2D[7][1] + y // Punto 8
        );
        Figura cuadradoFrontal = new Figura(
                (int) puntos2D[0][0] + x, (int) puntos2D[0][1] + y, // Punto 1
                (int) puntos2D[1][0] + x, (int) puntos2D[1][1] + y, // Punto 2
                (int) puntos2D[2][0] + x, (int) puntos2D[2][1] + y,// Punto 3
                (int) puntos2D[3][0] + x, (int) puntos2D[3][1] + y// Punto 4
        );
        Figura cuadradoLateralIzquierdo = new Figura(
                (int) puntos2D[5][0] + x, (int) puntos2D[5][1] + y, // Punto 6
                (int) puntos2D[1][0] + x, (int) puntos2D[1][1] + y, // Punto 2
                (int) puntos2D[2][0] + x, (int) puntos2D[2][1] + y, // Punto 3        
                (int) puntos2D[6][0] + x, (int) puntos2D[6][1] + y// Punto 7
        );
        Figura cuadradoLateralDerecho = new Figura(
                (int) puntos2D[4][0] + x, (int) puntos2D[4][1] + y, // Punto 5
                (int) puntos2D[0][0] + x, (int) puntos2D[0][1] + y, // Punto 1
                (int) puntos2D[3][0] + x, (int) puntos2D[3][1] + y, // Punto 4
                (int) puntos2D[7][0] + x, (int) puntos2D[7][1] + y // Punto 8
        );
        Figura cuadradoSuperiorTecho = new Figura(
                (int) puntos2D[0][0] + x, (int) puntos2D[0][1] + y, // Punto 1
                (int) puntos2D[4][0] + x, (int) puntos2D[4][1] + y, // Punto 5
                (int) puntos2D[5][0] + x, (int) puntos2D[5][1] + y, // Punto 6
                (int) puntos2D[1][0] + x, (int) puntos2D[1][1] + y // Punto 2
        );
        Figura cuadradoinferior = new Figura(
                (int) puntos2D[3][0] + x, (int) puntos2D[3][1] + y, // Punto 4
                (int) puntos2D[7][0] + x, (int) puntos2D[7][1] + y, // Punto 8
                (int) puntos2D[6][0] + x, (int) puntos2D[6][1] + y, // Punto 7
                (int) puntos2D[2][0] + x, (int) puntos2D[2][1] + y // Punto 3
        );

        if (OcultarCaras) {
            RellenarFiguraScanLine(cuadradoSuperiorTecho, Color.BLACK, Superior, new Color(117, 24, 4), false);
            RellenarFiguraScanLine(cuadradoSuperiorTecho, Color.BLACK, Superior, Final, false);
            RellenarFiguraScanLine(cuadradoTrasero, Color.BLACK, Trasera, Final, false);
            LavaAscendiente(xLava, yLava, 5, Color.BLACK, Color.BLACK, Color.BLACK);
            RellenarFiguraScanLine(cuadradoLateralDerecho, Color.BLACK, Derecha, Final, false);
            RellenarFiguraScanLine(cuadradoLateralIzquierdo, Color.BLACK, Inferior, Final, false);
            RellenarFiguraScanLine(cuadradoFrontal, Color.BLACK, Frontal, Final, false);
        } else {
            carasocultas = false;
            RellenarFiguraScanLine(cuadradoinferior, Color.BLACK, Inferior, Final, false);
            RellenarFiguraScanLine(cuadradoLateralIzquierdo, Color.BLACK, Inferior, Final, false);
            RellenarFiguraScanLine(cuadradoFrontal, Color.BLACK, Frontal, Final, false);
            RellenarFiguraScanLine(cuadradoSuperiorTecho, Color.BLACK, Superior, new Color(117, 24, 4), false);
            RellenarFiguraScanLine(cuadradoTrasero, Color.BLACK, Trasera, Final, false);
            RellenarFiguraScanLine(cuadradoLateralDerecho, Color.BLACK, Derecha, Final, false);
        }

        VolcanOblicuo.add(cuadradoTrasero);
        VolcanOblicuo.add(cuadradoFrontal);
        VolcanOblicuo.add(cuadradoLateralIzquierdo);
        VolcanOblicuo.add(cuadradoLateralDerecho);
        VolcanOblicuo.add(cuadradoSuperiorTecho);
        VolcanOblicuo.add(cuadradoinferior);

        System.out.println(); // Agregar una línea en blanco después del punto 8
    }

    public void RellenarFiguraScanLine(Figura figura, Color colorBorde, Color colorRellenoInicial, Color colorRellenoFinal, boolean contorno) {
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
        if (contorno == true) {
            colocarforma(figura, colorBorde);
        }

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

        public double[] getX() {
            return new double[]{puntoFig1.getposX(), puntoFig2.getposX(), puntoFig3.getposX(), puntoFig4.getposX()};
        }

        public double[] getY() {
            return new double[]{puntoFig1.getposY(), puntoFig2.getposY(), puntoFig3.getposY(), puntoFig4.getposY()};
        }

        public double[] getPosicion() {
            return new double[]{puntoFig1.getposX(), puntoFig1.getposY()};
        }

        public void setX(double[] x) {
            Puntos[] puntos = {puntoFig1, puntoFig2, puntoFig3, puntoFig4};
            for (int i = 0; i < x.length; i++) {
                puntos[i].setposX((int) x[i]);
            }
        }

        public void setY(double[] y) {
            Puntos[] puntos = {puntoFig1, puntoFig2, puntoFig3, puntoFig4};
            for (int i = 0; i < y.length; i++) {
                puntos[i].setposY((int) y[i]);
            }
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

    public void putLineaDDA(int x1, int y1, int x2, int y2) {
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
            addPixel(px, py);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void addPixel(int x, int y, Color color) {
        buffer.setRGB(x, y, color.getRGB());
    }

    public void addPixel(int x, int y) {
        buffer.setRGB(x, y, 0);
    }

    public void RellenarPoligono(Poligono poligono, Color colorBorde, Color colorRellenoInicial, Color colorRellenoFinal, boolean contorno) {
        Puntos[] vertices = poligono.getVertices();
        int numVertices = poligono.getNumVertices();

        // Se crea una lista de puntos ordenados por coordenada Y
        List<Puntos> puntosOrdenados = Arrays.asList(vertices);

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

        // Scanline dentro del polígono
        for (int y = minY + 1; y < maxY; y++) {
            List<Integer> intersecciones = new ArrayList<>();

            for (int i = 0; i < puntosOrdenados.size(); i++) {
                Puntos puntoActual = puntosOrdenados.get(i);
                Puntos puntoSiguiente = puntosOrdenados.get((i + 1) % numVertices);

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
        if (contorno == true) {
            colocarformapoligonal(poligono, colorBorde);
        }

    }

    public void colocarformapoligonal(Poligono poligono, Color colorBorde) {
        Puntos[] vertices = poligono.getVertices();
        int numVertices = poligono.getNumVertices();

        for (int i = 0; i < numVertices; i++) {
            Puntos puntoActual = vertices[i];
            Puntos puntoSiguiente = vertices[(i + 1) % numVertices];
            putLineaDDA(puntoActual.getposX(), puntoActual.getposY(), puntoSiguiente.getposX(), puntoSiguiente.getposY(), colorBorde);
        }
    }

    public class Poligono {

        private Puntos[] vertices;
        private int numVertices;

        public void desplazar(int deltaX, int deltaY) {
            for (Puntos punto : vertices) {
                punto.setposX(punto.getposX() + deltaX);
                punto.setposY(punto.getposY() + deltaY);
            }
        }

        public double[][] obtenerPuntosMatriz() {
            double[][] puntosMatriz = new double[vertices.length][2];
            for (int i = 0; i < vertices.length; i++) {
                puntosMatriz[i][0] = vertices[i].getposX();
                puntosMatriz[i][1] = vertices[i].getposY();
            }
            return puntosMatriz;
        }

        public void actualizarPuntosDesdeMatriz(double[][] matriz) {
            if (matriz.length != vertices.length) {
                // Manejar el caso de error o lanzar una excepción adecuada
                // si la longitud de la matriz no coincide con la cantidad de elementos en vertices
                // por ejemplo: throw new IllegalArgumentException("La matriz y los vertices no tienen la misma longitud");
                return;
            }

            for (int i = 0; i < vertices.length; i++) {
                double x = matriz[i][0];
                double y = matriz[i][1];
                int roundedX = (int) Math.round(x);
                int roundedY = (int) Math.round(y);
                vertices[i] = new Puntos(roundedX, roundedY);
            }
        }

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

        public double[] getX() {
            double[] x = new double[vertices.length];
            for (int i = 0; i < vertices.length; i++) {
                x[i] = vertices[i].getposX();
            }
            return x;
        }

        public double[] getY() {
            double[] y = new double[vertices.length];
            for (int i = 0; i < vertices.length; i++) {
                y[i] = vertices[i].getposY();
            }
            return y;
        }

        public void setX(double[] x) {
            for (int i = 0; i < vertices.length; i++) {
                vertices[i].setposX((int) Math.round(x[i]));
            }
        }

        public void setY(double[] y) {
            for (int i = 0; i < vertices.length; i++) {
                vertices[i].setposY((int) Math.round(y[i]));
            }
        }

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

    public void ColocarVolcan() {
        Color cafe = new Color(48, 30, 42);
        Color cafeclaro = new Color(185, 122, 87);
        RellenarObjeto(xVolcan, yVolcan, false, Volcan, cafe, cafe, cafe, cafe, cafe, cafe, true, cafeclaro);

    }

    public Figura HacerRectangulo(int x1, int y1, int x2, int y2, int Ancho, int Alto, Color c) {

        // Crear objeto Forma con los puntos correspondientes al rectángulo
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        Figura figura = new Figura(minX, minY, minX + Ancho, minY, minX + Ancho, minY + Alto, minX, minY + Alto);
        return figura;
    }
    //PROYECTO GRAFICAS EN CURSO....
}
