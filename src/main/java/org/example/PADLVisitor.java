package org.example;

import padl.kernel.IEntity;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IRelationship;
import padl.visitor.IVisitor;

interface PADLVisitor extends IVisitor {
    void visit(IFirstClassEntity entity);

    void visit(IRelationship relationship);

    void visit(IEntity entity);

    String generateDescription();

}
