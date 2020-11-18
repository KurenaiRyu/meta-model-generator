package io.github.natsusai.modelgen;

import java.util.HashSet;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class EntityModel {
    /**
     * 成员变量
     */
    private final HashSet<VariableElement>   variableElements;
    /**
     * 类方法
     */
    private final HashSet<ExecutableElement> executableElements;
    /**
     * 包
     */
    private final PackageElement             packageElement;
    /**
     * 类
     */
    private final TypeElement                classElement;

    public EntityModel(TypeElement classElement) {
        this.classElement = classElement;
        packageElement = (PackageElement) classElement.getEnclosingElement();
        variableElements = new HashSet<>();
        executableElements = new HashSet<>();
        for (Element element : classElement.getEnclosedElements()) {
            ElementKind kind = element.getKind();
            if (ElementKind.FIELD.equals(kind)) {
                variableElements.add((VariableElement) element);
            } else if (ElementKind.METHOD.equals(kind)) {
                executableElements.add((ExecutableElement) element);
            }
        }
    }

    public void addVariableElement(VariableElement element) {
        variableElements.add(element);
    }

    public void addExecutableElement(ExecutableElement element) {
        executableElements.add(element);
    }

    public HashSet<VariableElement> getVariableElements() {
        return this.variableElements;
    }

    public HashSet<ExecutableElement> getExecutableElements() {
        return this.executableElements;
    }

    public PackageElement getPackageElement() {
        return this.packageElement;
    }

    public TypeElement getClassElement() {
        return this.classElement;
    }
}