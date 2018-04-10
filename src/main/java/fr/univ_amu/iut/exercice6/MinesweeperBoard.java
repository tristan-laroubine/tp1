package fr.univ_amu.iut.exercice6;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperBoard {
    List<String> inputBoard;
    List<String> annotatedRepresentation;
    char[][] inputMatrix;
    int[][] annotationMatrix;

    public MinesweeperBoard(List<String> inputBoard) {
        if (inputBoard == null)
            throw new IllegalArgumentException("Input board may not be null.");
        verifierInputBoard(inputBoard);

        this.inputBoard=inputBoard;
        this.annotatedRepresentation = new ArrayList<>();

        int nbCol = 0;
        int nbLig = inputBoard.size();

        if(!inputBoard.isEmpty()) {
            nbCol = inputBoard.get(0).length();
        }

        inputMatrix = new char[nbLig+2][nbCol+2];
        annotationMatrix = new int[nbLig+2][nbCol+2];

        buildInputMatrix();
        buildAnnotationMatrix();
    }

    private void verifierInputBoard(List<String> inputBoard) {
        int taille = inputBoard.get(0).length();
        for (String ligne: inputBoard) {
            if(ligne.matches(".*[^ *].*")){
                throw new IllegalArgumentException("Input board can only contain the characters ' ' and '*'.");
            }
            if(ligne.length() != taille)
                throw new IllegalArgumentException("Input board rows must all have the same number of columns.");

        }
    }


    private void buildAnnotationMatrix() {
        for (int i = 1; i < annotationMatrix.length-1; i++) {
            for (int j = 1; j < annotationMatrix[i].length-1; j++) {
                annotationMatrix[i][j] = nombreVoisin(i,j);
            }
        }
    }

    private int nombreVoisin(int i, int j) {
        if(inputMatrix[i][j] == '*')
            return 0;

        int nombreVoisins=0;

        for (int k = i-1; k <= i+1; k++) {
            for (int l = j-1; l <= j+1; l++) {
                if (inputMatrix[k][l] == '*')
                    nombreVoisins++;
            }
        }

        return nombreVoisins;
    }

    private void buildInputMatrix() {
        int j=1;
        for (String ligne: inputBoard) {
            annotatedRepresentation.add(ligne);
            for (int i=0; i < ligne.length(); i++)
            {
                inputMatrix[j][i+1] = ligne.charAt(i);
            }
            j++;
        }
    }

    public List<String> getAnnotatedRepresentation() {
        ArrayList<String> list = new ArrayList<>();

        for (int i=1; i < annotationMatrix.length-1; i++)
        {
            String str = new String();
            for (int j = 1; j < annotationMatrix[i].length-1; j++) {
                if (inputMatrix[i][j] == '*')
                    str += '*';
                else if (annotationMatrix[i][j] == 0)
                    str += ' ';
                else
                    str += annotationMatrix[i][j];
            }

            list.add(str);
        }

        annotatedRepresentation = list;
        return annotatedRepresentation;
    }
}
