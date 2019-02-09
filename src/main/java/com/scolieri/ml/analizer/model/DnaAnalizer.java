package com.scolieri.ml.analizer.model;

public class DnaAnalizer {

    public boolean isMutan(String[] dna){
        int numberOfMutanSecuence = 0;
        if(dna.length <= 2){
            return false;
        }
        char[][] dnaMatrix = getMatrix(dna);
        for(int i = 0;i < dna.length;i++){
            for(int j = 0;j < dna.length;j++){
                numberOfMutanSecuence += getNumberOfMutanSecuence(dnaMatrix[i][j],i,j,dnaMatrix);
                if(numberOfMutanSecuence >= 2){
                    return true;
                }
            }
        }
        return false;
    }

    private char[][] getMatrix(String[] dna){
        char[][] dnaMatrix = new char[dna.length][dna.length];
        for(int i = 0; i < dna.length;i++){
            String dnaRow = dna[i];
            for(int j = 0;j<dna.length;j++){
                dnaMatrix[i][j] = dnaRow.charAt(j);
            }
        }
        return dnaMatrix;
    }

    private int getNumberOfMutanSecuence(char value, int i,int j,char[][] dnaMatrix){
        int numberOfValidRows = 0;
        if(dnaMatrix[i+1][j] == value){
            if(validateRow(dnaMatrix[i+1][j],i+1,j,dnaMatrix)){
                numberOfValidRows++;
            }
        }

        if(dnaMatrix[i][j+1] == value){
            if(validateColumn(dnaMatrix[i][j+1],i,j+1,dnaMatrix)){
                numberOfValidRows++;
            }
        }

        if(dnaMatrix[i+1][j+1] == value){
            if(validateDiagonal(dnaMatrix[i+1][j+1],i+1,j+1,dnaMatrix)){
                numberOfValidRows++;
            }
        }
        return numberOfValidRows;
    }

    private boolean validateRow(char secondValue, int i,int j,char[][] dnaMatrix){
        return dnaMatrix[i+1][j] == secondValue && dnaMatrix[i+2][j] == secondValue;
    }

    private boolean validateColumn(char secondValue, int i,int j,char[][] dnaMatrix){
        return dnaMatrix[i][j+1] == secondValue && dnaMatrix[i][j+2] == secondValue;
    }

    private boolean validateDiagonal(char secondValue, int i,int j,char[][] dnaMatrix){
        return dnaMatrix[i+1][j+1] == secondValue && dnaMatrix[i+2][j+2] == secondValue;
    }

}
