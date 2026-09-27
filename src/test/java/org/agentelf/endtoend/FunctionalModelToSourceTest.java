package org.agentelf.endtoend;

import org.agentelf.cli.LoadTasks;
import org.agentelf.cli.RunTask;
import org.agentelf.model.functional.FunctionWithPromptFunctional;
import org.agentelf.model.functional.FunctionalModel;
import org.agentelf.model.functional.Variations;
import org.agentelf.taskandaction.action.LoadFunctionalModel;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.StringReader;
import java.util.Collections;

import static org.agentelf.util.ResourceReader.asString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FunctionalModelToSourceTest extends AbstractEndToEndTest  {

    @Value("classpath:/endtoend/TestClass.java")
    private Resource testClassJava;

    @Value("classpath:/endtoend/functionalModelToSource.yml")
    private Resource functionalModelToSource;

    @MockitoBean
    private LoadFunctionalModel loadFunctionalModel;

    @Test
    public void adtAndFunction() throws Exception {
        when(callLLMList.callLarge(anyString())).thenReturn(asString(testClassJava));
        FunctionalModel model = new FunctionalModel("org.agentelf",
                 Collections.singletonList(new Variations("Variation" , Collections.singletonList("VariationOne"))) ,
                null,
                Collections.singletonList(new FunctionWithPromptFunctional("method( variation : Variation) : String" , "create class" , "")) );

        when(loadFunctionalModel.loadFunctionalModel(anyString())).thenReturn(model);

        ArgumentCaptor<String> promptCaptor =
                ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<String> contentCaptor =
                ArgumentCaptor.forClass(String.class);

        new RunTask( new LoadTasks().getYamlFilesFromClassPath(),
                new TaskAndActionFactorySpring(applicationContext)).run(new StringReader(asString(functionalModelToSource)));

        verify(callLLMList,times(3)).callLarge(promptCaptor.capture());
     /*   verify(fileOutput).writeFile(contentCaptor.capture(),
                eq("TestClass.java") ,
                eq(Path.of("src/main/java/org/agentelf/taskandaction/action")));
      */
       System.out.println(promptCaptor.getAllValues());
        // assertTextEquals("/endtoend/createClassPrompt.txt",promptCaptor.getValue());
       // assertTextEquals("/endtoend/createClassContent.txt",contentCaptor.getValue());
    }

}
