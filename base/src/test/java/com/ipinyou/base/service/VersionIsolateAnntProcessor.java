package com.ipinyou.base.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import lombok.eclipse.handlers.EclipseHandlerUtil;

@SupportedAnnotationTypes(value={"*"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class VersionIsolateAnntProcessor extends AbstractProcessor {
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		try(PrintWriter pw=new PrintWriter(new File("/home/zhyhang/annotationProc1.txt"));){
			pw.println(annotations);
			pw.println(roundEnv);
			Set<? extends Element> root = roundEnv.getRootElements();
			pw.println(root);
			for (Element element : root) {
				pw.println(element.getSimpleName());
			}
		} catch (FileNotFoundException e) {
			EclipseHandlerUtil.error(null, this.getClass().getName()+"创建文件出错", e);
		}
		return true;
	}

}
