import java.io.IOException;

/**
 * Created by zulupero on 09/11/15.
 */
public class DemoPixmap {

    public static byte[][] traceCadre(byte[][] imgIn, int e){
        final int I_DIM = imgIn.length ;
        final int J_DIM = imgIn[0].length ;

        byte[][] imgOut = new byte[I_DIM][J_DIM] ;
        
        for(int i = 0; i < I_DIM; i++) {
            for(int j = 0; j < J_DIM; j++) {
                imgOut[i][j] = imgIn[i][j] ;
            }
        }

        for(int i = 0; i < I_DIM; i++) {
            for(int j = 0; j < J_DIM; j++) {
                if(i < e || i > I_DIM - e || j < e || j > J_DIM - e) {
                    imgOut[i][j] = 0 ;
                }
            }
        }
        return imgOut;
    }

    public static byte[][] negatif(byte[][] imgIn){
        final int I_DIM = imgIn.length ;
        final int J_DIM = imgIn[0].length ;

        byte[][] imgOut = new byte[I_DIM][J_DIM] ;

        for(int i=0; i<I_DIM; i++){
            for(int j=0; j<J_DIM; j++){
                imgOut[i][j] =(byte)(255 - imgIn[i][j]) ;
            }
        }   

        return imgOut ;
    }

    public static byte[][] traceDiagos (byte[][] imgIn, int e){
        final int I_DIM = imgIn.length ;
        final int J_DIM = imgIn[0].length ;

        byte[][] imgOut = new byte[I_DIM][J_DIM] ;

        for(int i=0; i<I_DIM; i++){
            for(int j=0; j<J_DIM; j++){
                imgOut[i][j] = imgIn[i][j] ;
            }
        }

        for(int i=0; i<I_DIM; i++){
            for(int j=0; j<J_DIM; j++){
                if(i == j || i == I_DIM - j - 1) {
                    for(int k = 0; k < e; k++) {
                        if(i - k >= 0 && j - k >= 0) {
                            imgOut[i - k][j - k] = 0 ;
                        }
                        if(i - k >= 0 && j + k < J_DIM) {
                            imgOut[i - k][j + k] = 0 ;
                        }
                        if(i + k < I_DIM && j - k >= 0) {
                            imgOut[i + k][j - k] = 0 ;
                        }
                        if(i + k < I_DIM && j + k < J_DIM) {
                            imgOut[i + k][j + k] = 0 ;
                        }
                    }
                }
            }
        }
        return imgOut;
    }

    public static byte[][] averageFilter2D (byte[][] imgIn){

        final int I_DIM = imgIn.length ;
        final int J_DIM = imgIn[0].length ;
        int taille = 1;

        byte[][] imgOut = new byte[I_DIM][J_DIM] ;

        for(int i=0; i<I_DIM; i++){
            for(int j=0; j<J_DIM; j++){
                imgOut[i][j] = imgIn[i][j] ;
            }
        }

        for(int i=0; i<I_DIM; i++){
            for(int j=0; j<J_DIM; j++){
                if(i >= taille && i < I_DIM - taille && j >= taille && j < J_DIM - taille) {
                    int moyenne = 0 ;
                    for(int k = -taille; k <= taille; k++) {
                        for(int l = -taille; l <= taille; l++) {
                            moyenne += imgIn[i + k][j + l] ;
                        }
                    }
                    moyenne /= (2 * taille + 1) * (2 * taille + 1) ;
                    imgOut[i][j] = (byte)moyenne ;
                }
            }
        }
        return imgOut;
    }

    public static byte[][] sobel2D_H (byte[][] imgIn){
        final int I_DIM = imgIn.length ;
        final int J_DIM = imgIn[0].length ;

        byte[][] imgOut = new byte[I_DIM][J_DIM] ;
        
        int[][] horiz = {(-1, 0, 1), (-2, 0, 2), (-1, 0, 1)};
    }

    public static void main(String[] args) {
        BytePixmap img;
        try {
            img = new BytePixmap(args[0]);         // lecture du fichier
        } catch (IOException e) { img = null; System.exit(0); }
        System.out.println("image lue : "+ img.height+"x"+img.width);

        byte[][] imgIn = new byte[img.height][img.width] ;
        byte[][] imgOut;

         /*
         * image 1D dans imgIn 2D
         */
        for(int i=0; i< img.height; i++)
            for(int j=0; j<img.width; j++)
                imgIn[i][j] = img.data[i*img.width + j] ;

        /*
        * Appels aux fonctions de traitement
        */
        //imgOut = traceDiagos(imgIn, 3) ;
        //imgOut = traceCadre(imgOut, 15) ;
        //imgOut = averageFilter2D(imgIn) ;
	    //imgOut = negatif(imgOut) ;
        imgOut = sobel2D_H(imgIn) ;
        /*
         * imgIn 2D dans img 1D
         */
        for(int i=0; i< img.height; i++)
            for(int j=0; j<img.width; j++)
                img.data[i*img.width + j] = imgOut[i][j] ;

	    // écriture de l'image de sortie
        img.write("sobel2D"+args[0]);
    }
}

