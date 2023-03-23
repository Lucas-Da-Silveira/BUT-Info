/**
 * Created by DA SILVEIRA Lucas and NOURRY Mathys 
 * Groupe n°6
 */


import java.util.Scanner;

import javax.swing.text.CompositeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StuckWin {
    static final Scanner input = new Scanner(System.in);
    private static final double BOARD_SIZE = 7;

    enum Result {OK, BAD_COLOR, DEST_NOT_FREE, EMPTY_SRC, TOO_FAR, EXT_BOARD, EXIT}
    enum ModeMvt {REAL, SIMU}
    final char[] joueurs = {'B', 'R'};
    final int SIZE = 8;
    final char VIDE = '.';
    // 'B'=bleu 'R'=rouge '.'=vide '-'=n'existe pas
    static char[][] state = {
        {'-', '-', '-', '-', 'R', 'R', 'R', 'R'},
        {'-', '-', '-', '.', 'R', 'R', 'R', 'R'},
        {'-', '-', '.', '.', '.', 'R', 'R', 'R'},
        {'-', 'B', 'B', '.', '.', '.', 'R', 'R'},
        {'-', 'B', 'B', 'B', '.', '.', '.', '-'},
        {'-', 'B', 'B', 'B', 'B', '.', '-', '-'},
        {'-', 'B', 'B', 'B', 'B', '-', '-', '-'},
    };


    /**
     * Déplace un pion ou simule son déplacement
     * @param couleur couleur du pion à déplacer
     * @param lcSource case source Lc
     * @param lcDest case destination Lc
     * @param mode ModeMVT.REAL/SIMU selon qu'on réalise effectivement le déplacement ou qu'on le simule seulement.
     * @return enum {OK, BAD_COLOR, DEST_NOT_FREE, EMPTY_SRC, TOO_FAR, EXT_BOARD, EXIT} selon le déplacement
    */
    Result deplace(char couleur, String lcSource, String lcDest,  ModeMvt mode) {

        int[] posDepart = new int[]{0,0}; 
        int[] posArrivee = new int[]{0,0};

        posDepart[0] = lcSource.charAt(0) - 'A';
        posDepart[1] = Integer.parseInt(lcSource.substring(1));
        posArrivee[0] = lcDest.charAt(0) - 'A';
        posArrivee[1] = Integer.parseInt(lcDest.substring(1));

        if(posDepart[0] < 0 || posDepart[0] >= SIZE 
        || posDepart[1] < 0 || posDepart[1] >= SIZE) {
            return Result.EXT_BOARD;

        } else if(posArrivee[0] < 0 || posArrivee[0] >= SIZE 
        || posArrivee[1] < 0 || posArrivee[1] >= SIZE) {
            return Result.EXT_BOARD;

        } else if(state[posDepart[0]][posDepart[1]] == VIDE) {
            return Result.EMPTY_SRC;

        } else if(state[posDepart[0]][posDepart[1]] != couleur) {
            return Result.BAD_COLOR;

        } else if(state[posArrivee[0]][posArrivee[1]] != VIDE && state[posArrivee[0]][posArrivee[1]] != couleur) {
            return Result.DEST_NOT_FREE;

        } else if(Math.abs(posDepart[0] - posArrivee[0]) > 1 
        || Math.abs(posDepart[1] - posArrivee[1]) > 1) {
            return Result.TOO_FAR;

        } else if(couleur == 'B' && (posArrivee[0] > posDepart[0] 
        || posArrivee[1] < posDepart[1])) {
            return Result.TOO_FAR;

        } else if(couleur == 'R' && (posArrivee[0] < posDepart[0] 
        || posArrivee[1] > posDepart[1])) {
            return Result.TOO_FAR;

        } else {
            if(mode == ModeMvt.REAL) {
                state[posDepart[0]][posDepart[1]] = VIDE;
                state[posArrivee[0]][posArrivee[1]] = couleur;
            }
            return Result.OK;
        }
    }

    /**
     * Construit les trois chaînes représentant les positions accessibles
     * à partir de la position de départ [idLettre][idCol].
     * @param couleur couleur du pion à jouer
     * @param idLettre id de la ligne du pion à jouer
     * @param idCol id de la colonne du pion à jouer
     * @return tableau des trois positions jouables par le pion (redondance possible sur les bords)
    */
    String[] possibleDests(char couleur, int idLettre, int idCol){
        String[] dests = new String[3];
        int[] posDepart = new int[2];
        posDepart[0] = idLettre;
        posDepart[1] = idCol;

        if(couleur == 'B') {
            dests[0] = "" + (char)(posDepart[0] + 'A') + (posDepart[1] + 1);
            dests[1] = "" + (char)(posDepart[0] + 'A' + 1) + (posDepart[1] + 1);
            dests[2] = "" + (char)(posDepart[0] + 'A' + 1) + (posDepart[1]);
        } else if(couleur == 'R') {
            dests[0] = "" + (char)(posDepart[0] + 'A') + (posDepart[1] - 1);
            dests[1] = "" + (char)(posDepart[0] + 'A' - 1) + (posDepart[1] - 1);
            dests[2] = "" + (char)(posDepart[0] + 'A' - 1) + (posDepart[1]);
        } 
        return dests;
    }



    /**
     * Affiche le plateau de jeu dans la configuration portée par
     * l'attribut d'état "state"
     * @param tab
    */
    void affiche(char[][] tab) {
        for(int i = tab[0].length - 1; i > (-(tab[0].length)); i--) {
            String ligneTab = "";
            int spaceCpt = 5;
            for(int j = 0; j < tab.length; j++) {
                if((i + j) >= 0 && (i + j) < tab[0].length && tab[j][i+j] != '-') {

                    if(tab[j][i+j] == '.') {
                        ligneTab += "" + ConsoleColors.BLACK + ConsoleColors.WHITE_BACKGROUND 
                        + (char)(j + 'A') + (i+j) + ConsoleColors.RESET + "  ";

                    } else if(tab[j][i+j] == 'B') {
                        ligneTab += "" + ConsoleColors.BLUE_BACKGROUND 
                        + (char)(j + 'A') + (i+j) + ConsoleColors.RESET + "  ";

                    } else if(tab[j][i+j] == 'R') {
                        ligneTab += "" + ConsoleColors.RED_BACKGROUND 
                        + (char)(j + 'A') + (i+j) + ConsoleColors.RESET + "  ";

                    } else {
                        ligneTab += " ";
                    }
                    spaceCpt--;
                }
            }
            if(!ligneTab.toString().equals("")) {
                for(int k = 0; k < spaceCpt * 2; k++) {
                    System.out.print(" ");
                }
                System.out.println(ligneTab);
            }
        }
    }


    /**
     * Joue un tour
     * @param couleur couleur du pion à jouer
     * @return tableau contenant la position de départ et la destination du pion à jouer.
    */
    String[] jouerIA1(char couleur) {
        String[] mvt = new String[2];
        Random rand = new Random();

        List<int[]> casesLibres = new ArrayList<>();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] == '.') {
                    // Recherche des pions de la couleur donnée autour de la case libre
                    if (i > 0 && state[i-1][j] == couleur) {
                        casesLibres.add(new int[] { i-1, j });
                    }
                    if (i < state.length-1 && state[i+1][j] == couleur) {
                        casesLibres.add(new int[] { i+1, j });
                    }
                    if (j > 0 && state[i][j-1] == couleur) {
                        casesLibres.add(new int[] { i, j-1 });
                    }
                    if (j < state[i].length-1 && state[i][j+1] == couleur) {
                        casesLibres.add(new int[] { i, j+1 });
                    }
                }
            }
        }

        if(!casesLibres.isEmpty()){
            int index = rand.nextInt(casesLibres.size());
            int[] pion = casesLibres.get(index);
            int i = pion[0];
            int j = pion[1];
        
            // Recherche des destinations possibles pour la pièce en utilisant la méthode existante
            String[] destinationsPossibles = possibleDests(couleur, i, j);
            if (destinationsPossibles.length > 0) {
                int indexDest = rand.nextInt(destinationsPossibles.length);
                String destination = destinationsPossibles[indexDest];
                mvt[0] = (char)(i+'A') + "" + j;
                mvt[1] = destination;
            }
        }
        return mvt;
    }

    String[] jouerIA2(char couleur) {
        Random rand = new Random();
        String[] mvt = new String[2];
        boolean found = false;
        while(!found) {
            int idLettre = rand.nextInt(6);
            int idCol = rand.nextInt(6);
            if(state[idLettre][idCol] == couleur) {
                String[] dests = possibleDests(couleur, idLettre, idCol);
                for(int i = 0; i < dests.length; i++) {
                    if(state[dests[i].charAt(0) - 'A'][dests[i].charAt(1) - '0'] == '.') {
                        mvt[0] = "" + (char)(idLettre + 'A') + idCol;
                        mvt[1] = dests[i];
                        found = true;
                        break;
                    }
                }
            }
        }
        return mvt;
    }




    /**
     * gère le jeu en fonction du joueur/couleur
     * @param couleur
     * @return tableau de deux chaînes {source,destination} du pion à jouer
    */
    String[] jouer(char couleur){
        String src = "";
        String dst = "";
        String[] mvtIa;
        switch(couleur) {
            case 'B':
                System.out.println("Mouvement " + couleur);
                src = input.next();
                dst = input.next();
                //mvtIa = jouerIA2(couleur);
                //src = mvtIa[0];
                //dst = mvtIa[1];
                System.out.println(src + "->" + dst);
                break;
            case 'R':
                System.out.println("Mouvement " + couleur);
                // src = input.next();
                // dst = input.next();
                mvtIa = jouerIA2(couleur);
                src = mvtIa[0];
                dst = mvtIa[1];
                System.out.println(src + "->" + dst);
                break;
        }
        return new String[]{src, dst};
    }

    /**
     * retourne 'R' ou 'B' si vainqueur, 'N' si partie pas finie
     * @param couleur
     * @return
    */
    char finPartie(char couleur) {
        int cpt;
        if(couleur == 'B') {
            cpt = 0;
            for(int i = 0; i < state.length; i++) {
                if(state[i][0] == 'B') {
                    cpt++;
                }
            }
            if(cpt == 1) {
                return 'B';
            }
        } else if(couleur == 'R') {
            cpt = 0;
            for(int i = 0; i < state.length; i++) {
                if(state[0][i] == 'R') {
                    cpt++;
                }
            }
            if(cpt == 1) {
                return 'R';
            }
        }
        return 'N';
    }


    public static void main(String[] args) {
        StuckWin jeu = new StuckWin();
        String src = "";
        String dest;
        String[] reponse;
        Result status;
        char partie = 'N';
        char curCouleur = jeu.joueurs[0];
        char nextCouleur = jeu.joueurs[1];
        char tmp;
        int cpt = 0;
        List<String> csvData = new ArrayList<String>();
        
        // version console
        do {
            // séquence pour Bleu ou rouge
            jeu.affiche(state);
            do {
                status = Result.EXIT;
                reponse = jeu.jouer(curCouleur);
                src = reponse[0];
                dest = reponse[1];
                if("q".equals(src))
                    return;
                status = jeu.deplace(curCouleur, src, dest, ModeMvt.REAL);
                partie = jeu.finPartie(nextCouleur);
                System.out.println("Status: "+ status + ", Partie: " + partie);
            } while(status != Result.OK && partie=='N');
            tmp = curCouleur;
            curCouleur = nextCouleur;
            nextCouleur = tmp;
            cpt ++;
            csvData.add("Couleur: " + tmp + ", Déplacement: " + src + " -> " + dest);
            try(PrintWriter writer = new PrintWriter(new File("StuckWin_IA2_1.csv"))) {
                for(String s : csvData) {
                    writer.write(s);
                    writer.write("\r");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } while(partie =='N');
        System.out.printf("Victoire: " + partie + " (" + (cpt/2) + " coups)");
    }
}