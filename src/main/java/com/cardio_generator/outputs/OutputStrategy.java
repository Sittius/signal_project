package com.cardio_generator.outputs;
/**
 * Interface for handling different types of data while normalizing the output across the project
 */
public interface OutputStrategy {
    /**
     * 
     * @param patientId The unique ID of a patient
     * @param timestamp The exact time the data was generated
     * @param label The label describing what the data generated is
     * @param data The data that is generated by the output
     */
    void output(int patientId, long timestamp, String label, String data);
}
