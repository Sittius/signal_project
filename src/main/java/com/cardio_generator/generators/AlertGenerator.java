package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Generates alerts for patients based on probability 
 */

public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();
    private boolean[] alertSpaces; // false = resolved, true = pressed

    /**
     * Creates a AlertGenerator for a given number of patients
     * @param patientCount The number of patients to create generators for
     */

    public AlertGenerator(int patientCount) {
        //Changed variable name to lowerCamelCase
        alertSpaces = new boolean[patientCount + 1];
    }

    /**
     * Generates random alerts for a specific patients.
     * The alert has a 90% chance to resolve and a 10% to trigger
     * 
     * @param patientID The specific patient to generate alerts for
     * @param outputStrategy The specific form in which the data is outputted(files,TCP,WebSockets or Console)
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertSpaces[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    alertSpaces[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                //Changed variable name to lowerCamelCase
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertSpaces[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
