package dev.agentelf.meta;

import dev.agentelf.model.uml.ClassModel;
import dev.agentelf.model.uml.MethodModel;
import dev.agentelf.model.uml.UMLModel;
import dev.agentelf.model.uml.VariabelModel;


/**
 * createClass consists of the following steps:
 *      search
 *
 *      loadContext
 *      callLLM
 *
 *      removeUnnecessaryText
 *      parseResponseWithJavaParser
 *          here we have the class name
 *      loadContext
 *
 *      write to output dir
 *
 *      callLLM? -> struktur erzeugen (Interface...)
 *
 */
public class CreateClassFactory {

    public  UMLModel create() {
        String STRING = "String";

        UMLModel umlModel = new UMLModel();

        VariabelModel prompt = new VariabelModel("prompt" ,STRING );
        VariabelModel llmResponse = new VariabelModel("llmResponse" ,STRING );
        VariabelModel packageName = new VariabelModel("packageName" ,STRING );
        VariabelModel className = new VariabelModel("className" ,STRING );

      /*  ClassModel classModel = new ClassModel("RemoveUnnecessaryText" , "RemoveUnnecessaryText remove removes all ''' using regexp");
        MethodModel methodModel = new MethodModel("remove" , STRING);
        methodModel.getArguments().add(llmResponse);

        ClassModel classModel = new ClassModel("LoadContext" , "LoadContext loads all files from the contextFolder " +
                ", adds them to the prompt variable and returns the new prompt");
        MethodModel methodModel = new MethodModel("loadContext" , STRING);
        methodModel.getArguments().add(prompt);

        classModel.getMethods().add(methodModel);
        classModel.getFields().add(new VariabelModel("contextFolder","Path"));
       */

        ClassModel classModel = new ClassModel("RemoveMarkdownTags" ,
                """ 
                        RemoveMarkdownTags removes all lines from llmResponse
                        starting with ``` using regexp  and returns the new llmResponse
                        """);
        MethodModel methodModel = new MethodModel("removeMarkdownTags" , STRING);
        methodModel.getArguments().add(llmResponse);

        classModel.getMethods().add(methodModel);


        umlModel.getClasses().add(classModel);
        return umlModel;
    }


}
