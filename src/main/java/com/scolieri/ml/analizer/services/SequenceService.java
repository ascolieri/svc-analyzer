package com.scolieri.ml.analizer.services;

import com.google.common.hash.Hashing;
import com.scolieri.ml.analizer.models.database.Sequence;
import com.scolieri.ml.analizer.repositories.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class SequenceService {

    private SequenceRepository sequenceRepository;

    @Autowired
    public SequenceService(final SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    private static final int NEEDED_GENES_MATRIX_DIMSENSIONS = 4;
    private static final int NEEDED_CONSECUTIVE_GENES = 3;


    public Boolean validateSequence(String[] dna){
        Boolean mutantResult;
        String dnaHash = getHash(dna);
        Optional<Sequence> existingSequence = sequenceRepository.findBySequence(dnaHash);

        if(existingSequence.isPresent()){
            return existingSequence.get().isMutant();
        }

        mutantResult = isMutant(dna);
        Sequence newSequence = new Sequence(dnaHash,mutantResult);
        sequenceRepository.insertSequence(newSequence.getSequence(),newSequence.isMutant());
        return mutantResult;
    }

    private String getHash(String[] dna){
        return Hashing.sha512().hashString(String.join("",dna),StandardCharsets.UTF_8).toString();
    }

    private boolean isMutant(String[] dna){
        int numberOfMutantSequence = 0;
        if(dna.length < NEEDED_GENES_MATRIX_DIMSENSIONS){
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
        return numberOfValidRows;
    }

    private int validateColumn(char[][] dnaMatrix,int i,int j){
        if (i+1 < dnaMatrix.length){
            char value = dnaMatrix[i][j];
            if(dnaMatrix[i+1][j] == value){
                return 1 + validateColumn(dnaMatrix,i+1,j);
            }

        }
        return 0;
    }

    private int validateRow(char[][] dnaMatrix,int i,int j){
        if (j+1 < dnaMatrix.length){
            char value = dnaMatrix[i][j];
            if(dnaMatrix[i][j+1] == value){
                return 1 + validateRow(dnaMatrix,i,j+1);
            }
        }
        return 0;
    }

    private int validateDiagonal(char[][] dnaMatrix,int i,int j){
        if (i+1 < dnaMatrix.length && j+1 < dnaMatrix.length){
            char value = dnaMatrix[i][j];
            if(dnaMatrix[i+1][j+1] == value){
                return 1 + validateDiagonal(dnaMatrix,i+1,j+1);
            }
        }
        return 0;
    }
}
