/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster ;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;






public class Main
{
	public static void main(String[] args ) {
	   /* Scanner lectura = new Scanner (System.in);

        System.out.println("Ingrese su nombre: ");
        
        String nombre = lectura.next();
        
        System.out.println("Ingrese su edad: ");
        
        int edad = lectura.nextInt();
        */
        //Con la sintaxis de arriba se pueden pedir valores por consola
        double xInicial = -1.6;
        double yInicial = -1 ;
        double rangoX = 2.2;
        double rangoY = 2.2;
        int width = 1024;
        int height = 1024;
        int cantIteraciones = 500;
	    long inicio = System.currentTimeMillis();
		BufferedImage bi = new BufferedImage(width , height , BufferedImage.TYPE_INT_RGB );
        WritableRaster raster = bi.getRaster();
        Convertor cover = new Convertor();
        int colores[][] = cover.convertir(cantIteraciones);
        
        //Buffer buffer = new Buffer(5);
        
        
        double xUsada = xInicial ;
        double yUsada = yInicial;
        
        for(int yy = 0 ; yy < height ; yy++){
            /*Cada thread tendria un valor de x que le corrzponda y hace el  run de la parte de abajo? */
            for( int xx = 0 ; xx < width ; xx++ ){
                
                int n = 0;
                Complejo numero = new Complejo(xUsada, yUsada);
                Complejo resul = new Complejo(0.0, 0.0);
                while( n < cantIteraciones && calcularDistancia(resul) <= 4.0 ){

                    resul = resul.elevarAlCuadrado() ;
                    resul = resul.sumar(numero);
                    n++;
                }; 

                xUsada = xUsada + ((rangoX*1.0) / width); 
                
                int coloresRGB[] = {colores[0][n], colores[1][n], colores[2][n]};

                raster.setPixel(xx,yy,coloresRGB);

            }
            xUsada = xInicial;

            yUsada = yUsada + ((rangoY*1.0)/height); 
           
            
        };
        
        
        
        File outputfile = new File ("salida.png");
        try {
            ImageIO.write( bi, "png", outputfile );
        } catch ( IOException e ) {
        // TODO Auto - generated catch block
            
            e.printStackTrace ();
        }
       

        long actual = System.currentTimeMillis();
       
        System.out.println(inicio);
        System.out.println(actual);
        System.out.println(actual - inicio);
	}

	public static double calcularDistancia(Complejo n){
        return Math.sqrt(n.getReal() * n.getReal() + n.getImaginario() * n.getImaginario()) ;
    }

}


