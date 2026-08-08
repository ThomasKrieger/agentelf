we have multiple types of task pipielines:
   single element: createClass
   multipleElements: TransformModell


All Variables are store in one class independent of the type
Later we can use a map for this

Model Processing:
modelFile load



open composite models in one folder
processing order?
list of models?


model -> classTextMap (Type Handle)
modelList foreach create class
modelList foreach create test



Start:
Object model the loaded model

task: createClassTextMap
task foreach createClassFromModel/createTest
(includes create prompt)




open: Exception Handling:
ActionWrapperBean
} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
throw new RuntimeException(e);
}

as the call is by using reflection, we need to handle InvocationTargetException here


logging/messages/help