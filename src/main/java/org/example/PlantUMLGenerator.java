package org.example;

import padl.visitor.IGenerator;
import padl.visitor.IWalker;

public class PlantUMLGenerator extends PlantUmlVisitor implements IGenerator {

    @Override
    public String getCode() {
        return this.getPlantUMLDescription().toString();
    }
}
