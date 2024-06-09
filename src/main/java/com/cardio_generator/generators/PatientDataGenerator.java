package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
/**
 * Interface used for generating various kinds of patient data in the project
 */
public interface PatientDataGenerator {
    /**
     * 
     * @param patientId The ID of a existing patient 
     * @param outputStrategy The output strategy used to format the generated data
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
