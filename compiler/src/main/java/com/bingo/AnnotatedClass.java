package com.bingo;

import java.lang.annotation.ElementType;
import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * Author by tuyx
 *
 * @Created at : 2016/11/4 0004
 * @Description :
 * @VersionName :
 */
public class AnnotatedClass {
    public final String annotatedClassName;
    public final List<String> variableNames;
    public final TypeElement typeElement;

    public AnnotatedClass(TypeElement typeElement, List<String> variableNames) {
        this.annotatedClassName = typeElement.getSimpleName().toString();
        this.variableNames = variableNames;
        this.typeElement = typeElement;
    }

    public TypeMirror getType() {
        return typeElement.asType();
    }
}
