package org.example;

import padl.analysis.UnsupportedSourceModelException;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.statement.creator.classfiles.ConditionalModelAnnotator;
import padl.statement.creator.classfiles.LOCModelAnnotator;
import padl.util.ModelStatistics;

/**
 * This class provides a utility method for creating a PADL (Program Analysis and Description Language) model from a given file.
 */
public class PADLModel {

    /**
     * Creates a PADL model from the given file.
     *
     * @param fileName The name of the file to create the PADL model from.
     * @return The PADL model created from the file.
     */
    public static ICodeLevelModel createPADLModel(final String fileName) {
        ICodeLevelModel model = Factory.getInstance().createCodeLevelModel("");
        final ModelStatistics statisticModelListener = new ModelStatistics();
        model.addModelListener(statisticModelListener);
        try {
            // Create the initial model using CompleteClassFileCreator
            model.create(new CompleteClassFileCreator(new String[]{fileName}, true));
            // Annotate the model with conditional statements
            final ConditionalModelAnnotator annotator = new ConditionalModelAnnotator(new String[]{fileName});
            final ICodeLevelModel annotatedCodeLevelModel = (ICodeLevelModel) annotator.invoke(model);
            // Annotate the model with lines of code information
            final LOCModelAnnotator annotator2 = new LOCModelAnnotator(new String[]{fileName});
            // Update the model with the annotated information
            model = (ICodeLevelModel) annotator2.invoke(annotatedCodeLevelModel);
            // Return the updated model
            return model;
        } catch (final CreationException | UnsupportedSourceModelException e) {
            // Handle any exceptions during model creation
            e.printStackTrace();
        }
        return null; // Return null if model creation fails
    }
}
