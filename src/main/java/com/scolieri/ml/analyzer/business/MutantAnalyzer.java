package com.scolieri.ml.analyzer.business;

import org.springframework.stereotype.Component;

/**
 * Handle the analysis of the DNA sequence.
 */
@Component
public class MutantAnalyzer {

    private static final int NEEDED_GENES_MATRIX_DIMENSIONS = 4;
    private static final int NEEDED_CONSECUTIVE_GENES = NEEDED_GENES_MATRIX_DIMENSIONS - 1;

    /**
     * Analyze a DNA sequence.
     * @param dna the DNA sequence.
     * @return true if the sequence corresponds to a mutant or false if ts just a human.
     */
    public Boolean isMutant(String[] dna){
        int numberOfMutantSequence = 0;
        if(dna.length < NEEDED_GENES_MATRIX_DIMENSIONS){
            return false;
        }
        char[][] dnaMatrix = getMatrix(dna);
        for(int i = 0;i < dna.length;i++){
            for(int j = 0;j < dna.length;j++){
                numberOfMutantSequence += getNumberOfMutantSequence(i,j,dnaMatrix);
                if(numberOfMutantSequence >= 2){
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

    private int getNumberOfMutantSequence(int i, int j, char[][] dnaMatrix){
        int numberOfValidRows = 0;
        if(validateRow(dnaMatrix,i,j) == NEEDED_CONSECUTIVE_GENES){
            numberOfValidRows++;
        }
        if(validateColumn(dnaMatrix,i,j) == NEEDED_CONSECUTIVE_GENES){
            numberOfValidRows++;
        }
        if(validateDiagonal(dnaMatrix,i,j) == NEEDED_CONSECUTIVE_GENES){
            numberOfValidRows++;
        }

        if(validateInverseDiagonal(dnaMatrix,i,j) == NEEDED_CONSECUTIVE_GENES){
            numberOfValidRows++;
        }
        return numberOfValidRows;
    }

    /**
     * Validate a column in the matrix searching for consecutive items.
     * @param dnaMatrix the DNA sequence matrix
     * @param i column position
     * @param j row position
     * @return number of consecutive identical letters
     */
    private int validateColumn(char[][] dnaMatrix,int i,int j){
        if (i+1 < dnaMatrix.length){
            char value = dnaMatrix[i][j];
            if(dnaMatrix[i+1][j] == value){
                return 1 + validateColumn(dnaMatrix,i+1,j);
            }

        }
        return 0;
    }

    /**
     * Validate a row in the matrix searching for consecutive items.
     * @param dnaMatrix the DNA sequence matrix
     * @param i column position
     * @param j row position
     * @return number of consecutive identical letters
     */
    private int validateRow(char[][] dnaMatrix,int i,int j){
        if (j+1 < dnaMatrix.length){
            char value = dnaMatrix[i][j];
            if(dnaMatrix[i][j+1] == value){
                return 1 + validateRow(dnaMatrix,i,j+1);
            }
        }
        return 0;
    }

    /**
     * Validate a diagonal going downside in the matrix searching for consecutive items.
     * @param dnaMatrix the DNA sequence matrix
     * @param i column position
     * @param j row position
     * @return number of consecutive identical letters
     */
    private int validateDiagonal(char[][] dnaMatrix,int i,int j){
        if (i+1 < dnaMatrix.length && j+1 < dnaMatrix.length){
            char value = dnaMatrix[i][j];
            if(dnaMatrix[i+1][j+1] == value){
                return 1 + validateDiagonal(dnaMatrix,i+1,j+1);
            }
        }
        return 0;
    }

    /**
     * Validate a diagonal going upside in the matrix searching for consecutive items.
     * @param dnaMatrix the DNA sequence matrix
     * @param i column position
     * @param j row position
     * @return number of consecutive identical letters
     */
    private int validateInverseDiagonal(char[][] dnaMatrix,int i,int j){
        if (i+1 < dnaMatrix.length && j-1 >= 0){
            char value = dnaMatrix[i][j];
            if(dnaMatrix[i+1][j-1] == value){
                return 1 + validateInverseDiagonal(dnaMatrix,i+1,j-1);
            }
        }
        return 0;
    }
}
