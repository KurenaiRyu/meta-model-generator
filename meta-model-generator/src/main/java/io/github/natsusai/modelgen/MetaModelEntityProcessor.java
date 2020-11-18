package io.github.natsusai.modelgen;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;

public class MetaModelEntityProcessor extends AbstractProcessor {

    private HashMap<String, EntityModel> classMap;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        classMap = new HashMap<>();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 因为扫描会有多轮，所以需要清空一下,classMap在init方法中初始化
        classMap.clear();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Entity.class)) {
            checkModel(element);
        }

        for (EntityModel model : classMap.values()) {
            ClassWriter.generateJavaFile(processingEnv.getFiler(), model);
        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Entity.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void checkModel(Element element) {
        // 获取当前类
        if (ElementKind.CLASS.equals(element.getKind())) {
            TypeElement classElement = (TypeElement) element;
            String qualifiedName = classElement.getQualifiedName().toString();
            // 查看是否已经保存在classMap中了，如果没有就新创建一个
            EntityModel model = classMap.get(qualifiedName);
            if (model == null) {
                model = new EntityModel(classElement);
                classMap.put(qualifiedName, model);
            }
        }
    }
}