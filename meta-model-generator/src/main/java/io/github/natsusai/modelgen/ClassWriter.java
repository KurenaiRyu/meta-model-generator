package io.github.natsusai.modelgen;

import com.google.common.base.CaseFormat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import javax.annotation.processing.Filer;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.FileObject;

/**
 * @author liufuhong
 * @since 2020-11-18 13:35
 */

public class ClassWriter {

    /**
     * 生成Java文件
     */
    public static void generateJavaFile(Filer filer, EntityModel entityModel) {
        TypeElement classElement = entityModel.getClassElement();
        PackageElement packageElement = entityModel.getPackageElement();
        HashSet<VariableElement> variableElements = entityModel.getVariableElements();
        try {
            FileObject jfo = filer.createSourceFile(classElement.getQualifiedName().toString() + "_", classElement);
            BufferedWriter bw = new BufferedWriter(jfo.openWriter());
            bw.append("package ").append(packageElement.getQualifiedName()).append(";\n");
            bw.newLine();
            bw.append("import ").append(String.valueOf(classElement.getQualifiedName())).append(";\n");
            bw.newLine();
            bw.append("public abstract class ").append(classElement.getSimpleName()).append("_ {\n");
            bw.newLine();
            bw.append(getAttributeNameDeclarationString(variableElements));
            bw.newLine();
            bw.append("}");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成字段
     */
    private static String getAttributeNameDeclarationString(
            Iterable<VariableElement> variableElements) {
        StringBuilder sb = new StringBuilder();
        for (VariableElement variableElement : variableElements) {
            String fieldName = variableElement.getSimpleName().toString();

            sb.append(" public static final String ")
                    .append(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, fieldName))
                    .append(" = \"")
                    .append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName))
                    .append("\";\n");
        }
        return sb.toString();
    }

}
