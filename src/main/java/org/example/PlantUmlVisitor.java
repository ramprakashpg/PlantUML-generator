package org.example;

import padl.kernel.*;
import padl.visitor.IGenerator;
import util.io.ProxyConsole;

import java.util.Iterator;

/**
 * This class represents a Visitor implementation for generating a textual description of a PADL model.
 * The description includes PADL kernel entities such as IFirstClassEntity and IRelationship.
 * The output conforms to the syntax and semantics of PlantUML class diagrams.
 */
public class PlantUmlVisitor implements IGenerator {
    /**
     * Get the PlantUML description generated by the visitor.
     *
     * @return The PlantUML description as a StringBuilder.
     */

    boolean isGhostNeeded = false;
    public PlantUmlVisitor(boolean flag){
        this.isGhostNeeded = flag;
    }
    public StringBuffer getPlantUMLDescription() {
        return plantUMLDescription;
    }

    /**
     * StringBuilder object to store the PlantUML description.
     */
    private final StringBuffer plantUMLDescription = new StringBuffer();
    /**
     * StringBuilder object to store relationships between entities.
     */
    private final StringBuilder relationships = new StringBuilder("\n");

    public String generateDescription() {
        return String.valueOf(plantUMLDescription);
    }

    @Override
    public void close(final IAbstractModel anAbstractModel) {
        String.valueOf(plantUMLDescription.append(relationships).append("\n@enduml\n"));

    }

    @Override
    public void close(final IClass aClass) {

        this.printTopEntityClose(aClass);
    }

    @Override
    public void close(final IConstructor aConstructor) {

    }

    @Override
    public void close(final IDelegatingMethod aDelegatingMethod) {

    }

    @Override
    public void close(final IGetter aGetter) {
    }

    @Override
    public void close(final IGhost aGhost) {
        if(isGhostNeeded)
            this.printTopEntityClose(aGhost);
    }

    @Override
    public void close(final IInterface anInterface) {

        this.printTopEntityClose(anInterface);
    }

    @Override
    public void close(final IMemberClass aMemberClass) {


    }

    @Override
    public void close(final IMemberGhost aMemberGhost) {

    }

    @Override
    public void close(final IMemberInterface aMemberInterface) {


    }

    @Override
    public void close(final IMethod aMethod) {
    }

    @Override
    public void close(final IPackage aPackage) {
        plantUMLDescription.append("\n");
        plantUMLDescription.append("}\n");
    }

    @Override
    public void close(final IPackageDefault aPackage) {
        plantUMLDescription.append("\n");
        plantUMLDescription.append("}\n");
    }

    @Override
    public void close(final ISetter aSetter) {
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void open(final IAbstractModel anAbstractModel) {
        this.plantUMLDescription.append("@startuml plantuml");
        plantUMLDescription.append("\n");

    }

    @Override
    public void open(final IClass aClass) {

        this.printTopClassOpen(aClass);

    }

    /**
     * Opens the visitor for the given class, appending necessary opening statements to the PlantUML description.
     * This method is called when visiting a class entity during the traversal of the PADL model.
     * It adds the class declaration to the PlantUML description, including modifiers such as 'abstract' if applicable,
     * and starts the class block with '{'.
     * Additionally, it iterates over the inherited entities and implemented interfaces of the class,
     * adding relationship information to the StringBuilder 'relationships'.
     *
     * @param aClass The class being opened.
     */
    private void printTopClassOpen(IClass aClass) {
        String className = String.valueOf(aClass.getName());

        plantUMLDescription.append("\n");
        plantUMLDescription.append("\n");
        plantUMLDescription.append("\t\t\t\t\t");

        if (aClass.isAbstract()) {
            this.plantUMLDescription.append("abstract ");
        }

        this.plantUMLDescription.append("class " + className + " {");
        plantUMLDescription.append("\n");

        relationships.append("\t\t\t\t\t");

        Iterator<?> iterator = aClass.getIteratorOnInheritedEntities();
        if (iterator.hasNext()) {
            while (iterator.hasNext()) {

                IFirstClassEntity entity = (IFirstClassEntity) iterator.next();

                if (String.valueOf(entity.getName()).equals("Object")) {
                    continue;
                }

                relationships.append("\n");
                this.relationships.append(className);
                // extends relationship

                this.relationships.append(" --^ ");
                this.relationships.append(entity.getName());
                if (iterator.hasNext()) {
                    relationships.append("\n");
                }
            }
        }

        iterator = aClass.getIteratorOnImplementedInterfaces();
        if (iterator.hasNext()) {
            while (iterator.hasNext()) {
                relationships.append("\n");
                this.relationships.append(className);
                this.relationships.append(" ..^ ");
                this.relationships.append(((IFirstClassEntity) (iterator.next())).getName());
                if (iterator.hasNext()) {
                    relationships.append("\n");
                }
            }
        }
    }

    @Override
    public void open(final IConstructor aConstructor) {
    }

    @Override
    public void open(final IDelegatingMethod aDelegatingMethod) {

    }

    @Override
    public void open(final IGetter aGetter) {
    }

    @Override
    public void open(final IGhost aGhost) {
        if(isGhostNeeded) {
            plantUMLDescription.append("\n");
            plantUMLDescription.append("\t\t\t");
            plantUMLDescription.append("class ").append(String.valueOf(aGhost.getName())).append(" {");
            plantUMLDescription.append("\n");
        }


    }

    @Override
    public void open(final IInterface anInterface) {

        this.printTopEntityOpen(anInterface);

    }

    @Override
    public void open(final IMemberClass aMemberClass) {


    }

    @Override
    public void open(final IMemberGhost aMemberGhost) {


    }

    @Override
    public void open(final IMemberInterface aMemberInterface) {


    }

    @Override
    public void open(final IMethod aMethod) {
    }

    @Override
    public void open(final IPackage aPackage) {
        plantUMLDescription.append("\n");
        this.plantUMLDescription.append("package " + String.valueOf(aPackage.getName()) + " {");
    }

    @Override
    public void open(final IPackageDefault aPackage) {
        plantUMLDescription.append("\n");
        this.plantUMLDescription.append("package " + String.valueOf(aPackage.getName()) + " {");    }

    @Override
    public void open(final ISetter aSetter) {

    }
    public void reset() {
        System.out.print("RESET");
        this.plantUMLDescription.setLength(0);
    }

    /**
     * Closes the visitor for the given entity, appending necessary closing statements to the PlantUML description.
     *
     * @param entity The entity being closed.
     */
    void printTopEntityClose(final IFirstClassEntity entity) {
        plantUMLDescription.append("\n");
        plantUMLDescription.append("}\n");
    }

    /**
     * Opens the visitor for the given entity, appending necessary opening statements to the PlantUML description.
     *
     * @param entity The entity being opened.
     */
    void printTopEntityOpen(final IInterface entity) {
        String interfaceName = String.valueOf(entity.getName());

        plantUMLDescription.append("\n");
        plantUMLDescription.append("\n");
        plantUMLDescription.append("\t\t\t\t\t");
        this.plantUMLDescription.append("interface " + interfaceName + " {");
        plantUMLDescription.append("\n");
        Iterator<?> iterator = entity.getIteratorOnConstituents();
        if (iterator.hasNext()) {
            while (iterator.hasNext()) {
                IConstituent constituent = (IConstituent) iterator.next();
                System.out.println(constituent);
            }

        }

        relationships.append("\n");
        iterator = entity.getIteratorOnInheritingEntities();
        if (iterator.hasNext()) {
            while (iterator.hasNext()) {

                IConstituent entity1 = (IConstituent) iterator.next();

                relationships.append("\n");
                this.relationships.append(entity1.getName());
                this.relationships.append(" --^ ");
                this.relationships.append(interfaceName);
                if (iterator.hasNext()) {
                    relationships.append("\n");
                }
            }
        }
    }

    @Override
    public final void unknownConstituentHandler(
            final String aCalledMethodName,
            final IConstituent aConstituent) {

        ProxyConsole
                .getInstance()
                .debugOutput()
                .print(this.getClass().getName());
        ProxyConsole
                .getInstance()
                .debugOutput()
                .print(" does not know what to do for \"");
        ProxyConsole.getInstance().debugOutput().print(aCalledMethodName);
        ProxyConsole.getInstance().debugOutput().print("\" (");
        ProxyConsole
                .getInstance()
                .debugOutput()
                .print(aConstituent.getDisplayID());
        ProxyConsole.getInstance().debugOutput().println(')');
    }

    @Override
    public void visit(final IAggregation anAggregation) {

    }

    @Override
    public void visit(final IAssociation anAssociation) {

    }

    @Override
    public void visit(final IComposition aComposition) {

    }

    @Override
    public void visit(final IContainerAggregation aContainerAggregation) {

    }

    @Override
    public void visit(final IContainerComposition aContainerComposition) {

    }

    @Override
    public void visit(final ICreation aCreation) {
    }

    @Override
    public void visit(final IField aField) {
    }

    @Override
    public void visit(final IMethodInvocation aMethodInvocation) {
    }

    @Override
    public void visit(final IParameter aParameter) {

    }

    @Override
    public void visit(final IPrimitiveEntity aPrimitiveEntity) {
        plantUMLDescription.append("class ").append(aPrimitiveEntity.getName()).append(" {\n");
    }

    @Override
    public void visit(final IUseRelationship aUse) {

    }

    @Override
    public String getCode() {
        return this.getPlantUMLDescription().toString();
    }
}
