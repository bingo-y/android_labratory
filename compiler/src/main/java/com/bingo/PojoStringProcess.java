package com.bingo;

import com.example.PojoString;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Author by tuyx
 *
 * @Created at : 2016/11/4 0004
 * @Description :
 * @VersionName :
 */
@AutoService(Processor.class)
public class PojoStringProcess extends AbstractProcessor {
    private static final String ANNOTATION = "@" + PojoString.class.getSimpleName();
    private static final String CLASS_NAME = "StringUtil";
    private Messager messager;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        ArrayList<AnnotatedClass> annotatedClasses = new ArrayList<>();
        for (Element element : roundEnv.getElementsAnnotatedWith(PojoString.class)) {
            TypeElement typeElement = (TypeElement) element;
            if (!isValidClass(typeElement)) {
                return true;
            }
            annotatedClasses.add(buildAnnotatedClass(typeElement));
        }

        try {
            generate(annotatedClasses);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(PojoString.class.getCanonicalName());
    }

    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

    public boolean isValidClass(TypeElement typeElement) {
        if (!isPublic(typeElement)) {
            String message = String.format("Class annotated with %s must not be public.", ANNOTATION);
            messager.printMessage(Diagnostic.Kind.ERROR, message);
            return false;
        }
        if (isAbstract(typeElement)) {
            String message = String.format("Class annotated with %s must not be abstract.", ANNOTATION);
            messager.printMessage(Diagnostic.Kind.ERROR, message);
            return false;
        }
        return true;
    }

    public boolean isPublic(TypeElement annotatedClass) {
        return annotatedClass.getModifiers().contains(Modifier.PUBLIC);
    }

    public boolean isAbstract(TypeElement annotatedClass) {
        return annotatedClass.getModifiers().contains(Modifier.ABSTRACT);
    }

    public AnnotatedClass buildAnnotatedClass(TypeElement typeElement) {
        ArrayList<String> variableNames = new ArrayList<>();
        for (Element element : typeElement.getEnclosedElements()) {
            if (!(element instanceof VariableElement)) {
                continue;
            }
            VariableElement variableElement = (VariableElement) element;
            variableNames.add(variableElement.getSimpleName().toString());
        }
        return new AnnotatedClass(typeElement, variableNames);
    }

    public void generate(List<AnnotatedClass> list) throws IOException {
        if (list == null || list.size() == 0) {
            return;
        }
        String packageName = generatePkgName(processingEnv.getElementUtils(), list.get(0).typeElement);
        TypeSpec generateClass = generateClass(list);

        JavaFile javaFile = JavaFile.builder(packageName, generateClass).build();
        javaFile.writeTo(processingEnv.getFiler());
    }

    public String generatePkgName(Elements elementsUtil, TypeElement typeElement) {
        PackageElement packageElement = elementsUtil.getPackageOf(typeElement);
        if (packageElement.isUnnamed()) {
            return null;
        }
        return packageElement.getQualifiedName().toString();
    }

    public TypeSpec generateClass(List<AnnotatedClass> list) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(CLASS_NAME).addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (AnnotatedClass annotatedClass : list) {
            builder.addMethod(makeCreateStringMethod(annotatedClass));
        }
        return builder.build();
    }

    public MethodSpec makeCreateStringMethod(AnnotatedClass annotatedClass) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("return \"%s{\" + ", annotatedClass.annotatedClassName));
        for (String variableName : annotatedClass.variableNames) {
            builder.append(String.format(" \"%s='\" + String.valueOf(instance.%s) + \"',\" + ",
                    variableName, variableName));
        }
        builder.append("\"}\"");
        return MethodSpec.methodBuilder("createString")
                .addJavadoc("@return string suitable for {@param instance}'s toString()")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get(annotatedClass.getType()), "instance")
                .addStatement(builder.toString())
                .returns(String.class)
                .build();
    }
}
