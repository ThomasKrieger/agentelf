We use mutliple layers to make the decisions the llm must take as small as possible

Conceptual, Requirements -> Model -> Intermidioate -> SourceCode

We allow the LLm to genarate the Model using a DSL like for eaxmple
multiple UML Claass files

In the Model is basically defined to map it rule based to the intermediate model
we use marker (state,... ) to define the rule based transformation where not clear
allowing the llm to change the result of the intermediate Model

The intermediate Model resembles the source code with llm prompts
So here is clear what methods and classes shcould exists, only the implenetation of the mehods
must be translated from the llm

We need to give the llm a good prompt, e.g., know which classes to include and probably static method
inlining 


