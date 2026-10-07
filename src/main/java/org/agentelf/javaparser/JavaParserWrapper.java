package org.agentelf.javaparser;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.Path;

public class JavaParserWrapper {

    public CompilationUnit parse(Path path) throws IOException {
        StaticJavaParser.getParserConfiguration()
                .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_21);
        return StaticJavaParser.parse(path);
    }

    public CompilationUnitWrapper parse(String source) throws IOException {
        StaticJavaParser.getParserConfiguration()
                .setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_21);
        return new CompilationUnitWrapper(StaticJavaParser.parse(source));
    }

}
