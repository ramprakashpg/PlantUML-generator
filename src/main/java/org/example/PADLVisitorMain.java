package org.example;

import padl.kernel.ICodeLevelModel;
import java.util.Scanner;

/**
 * This class contains the main method to demonstrate the usage of PADLModel and PlantUMLGenerator.
 */
public class PADLVisitorMain {

    /**
     * The main method to create a PADL model from a folder and generate PlantUML code.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize Scanner for user input
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the folder:");
        // Hardcoded folder name for demonstration purposes
        String folderName = "PlantUMLGenerator/out/padl/event/";
        // Read folder name from user input if needed
        // String folderName = scanner.nextLine();

        // Create PADL model from the specified folder
        final ICodeLevelModel models = PADLModel.createPADLModel(folderName);
        // Ensure models is not null
        assert models != null;

        // Generate PlantUML code from the PADL model using PlantUMLGenerator
        final String generatedString = models.generate(new PlantUMLGenerator());
        // Print the generated PlantUML code
        System.out.println(generatedString);
    }
}
