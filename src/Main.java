

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
        double xInicial = -1.6; //valores para verlo completo -1.6
        double yInicial = -1 ;  // -1
        double rangoX = 2.2; // 2.2
        double rangoY = 2.2; //2.2
        int width = 1024;
        int height = 1024;
        int cantIteraciones = 1000;
        int threads = 5;
	    long inicio = System.currentTimeMillis();
        System.out.println(inicio);
		BufferedImage bi = new BufferedImage(width , height , BufferedImage.TYPE_INT_RGB );
        WritableRaster raster = bi.getRaster();
        Convertor cover = new Convertor(cantIteraciones);
        Buffer buffer = new Buffer(5);
        ThreadPool pool = new ThreadPool(threads, buffer) ;
        WorkerCounter workerCounter = new WorkerCounter(threads) ;
        
       pool.startWorkers();
        
       double yUsada = yInicial;
       for(int yy = 0 ; yy < height ; yy++){
            MandelbrotTask task = new MandelbrotTask(yUsada,yy,xInicial,rangoX,raster,width,cantIteraciones,cover) ;
            try{
                buffer.write(task);
            }catch(InterruptedException e) {}
            
            yUsada = yUsada + ((rangoY)/height); 

        }

        for(int x = 0; x < threads; x++){
            PoisonPill task = new PoisonPill(workerCounter) ;
            try{
                buffer.write(task);
            }catch(InterruptedException e) {}
        }

        workerCounter.revisarParaTerminar();
        
        /*double xUsada = xInicial ;
        double yUsada = yInicial;
        /*Esto hace el dibujo solo hay que ahcerlo concurrente 
        for(int yy = 0 ; yy < height ; yy++){
            /*Cada thread tendria un valor de y que le corrzponda y hace el  run de la parte de abajo? 
            for( int xx = 0 ; xx < width ; xx++ ){
                
                int n = 0;
                Complejo numero = new Complejo(xUsada, yUsada);
                Complejo resul = new Complejo(0.0, 0.0);
                while( n < cantIteraciones && resul.calcularDistancia() <= 4.0 ){

                    resul = resul.elevarAlCuadrado() ;
                    resul = resul.sumar(numero);
                    n++;
                }; 

                xUsada = xUsada + ((rangoX*1.0) / width); 
                
                

                raster.setPixel(xx,yy,cover.devolverColores(n));

            }
            xUsada = xInicial;

            yUsada = yUsada + ((rangoY*1.0)/height); 
           
            
        };
        
        */
    
        File outputfile = new File ("salida.png");
        try {
            ImageIO.write( bi, "png", outputfile );
        } catch ( IOException e ) {
        
            e.printStackTrace ();
        }
       

        long actual = System.currentTimeMillis();
       
        System.out.println(inicio);
        System.out.println(actual);
        System.out.println(actual - inicio);
	}



}


