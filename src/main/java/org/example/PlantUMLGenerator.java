package org.example;

import padl.visitor.IGenerator;
import padl.visitor.IWalker;

public class PlantUMLGenerator extends TextualDescriptionVisitor implements IGenerator {

    @Override
    public String getCode() {
        return String.valueOf(this.getPlantUMLDescription());
    }
}
