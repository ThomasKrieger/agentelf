@JsonProperty("implements") List<String> implementsInterfaces,

action preprocess 
search additional files
action postprocesslass
generated class (String)

clarify
TypeDescriptionIntermediate

add annotation (for action)
add documentation

design pattern als rules

java doc template
+ used by
+ used in

do i need ReferenceTypeDescriptionIntermediate

propbly remove unnecessary tests

next:
javaparser
    get -> methoden liste
           daten für hash code
    set
    check
    add annotation
    add/set java doc

Generated Class
    Method Handle
    Field Handle?
    clear annotations
    add documentation
    reset compltly generated methods


method call creation
     and method creation
     example visitor
     builder...
     example call sequence
     example data flow 

search
implements
prompt
    @ -> map aus commands when function call abc(param)
    @ direct (super.. param) 
    or call()
    or xyz()
    perhaps @{program code}
    parser (regexp)
update
    link
        replace with existing
    tests komliziert -> neu hinzukommende tests
    offen ob erstmal pro class nicht pro methode?
    keep test? -> Refactoring?
    what methods is a test calling? especially unit test?


create model?

end to end test?


run

functional

test für prompt...



Refactoring -> Does doku still fits?
nicht versuchen zu smart zu sein!
eher über refactoring


N small model types:
types -> entprechend uml class diagramm -> record,...
sequence diagram -> propably mostly rule based?
functional 
state diagram
open: variations?
productline views?
test (end to end?)
modules
event based? use case maps?
ddd design techniken?
abstraction
dependency matrix
entity relation ship?
methods also as relation? later
specialiced dsls (actions...)
LLm Req -> Model


first existing models:
uml class
sequence
state
functional 
use case? -> functional decomposition + ddd techniques








open: one large model
or n different
n different nearer to uml
sequence/state..
easier to develop
especially state
open functional sepreation?
functional part

how to try model?

+ seqquence -> high level
    mostly rule based
    template for visitor
parameter widening


+ functional (adt, very type based, functions)
gadt?

foreach? functional? (maps...)
ähnlich types nicht direkt uml
@für model typ?

+ functional/


prompt generation and context building
    at
    call...
    super
       -> context building
    logging..
    includes
    uses
    extra prompt?


method resolution
    double dispatch
    visitor pattern 

prompt parer probably best regexp based


difference between service and normal mutable class


UML -> Intermediate oder erst data and functions?
parser for data would be cool
danach alles über X
    use parse method
sequence diagram?
   function list
   wie calls darstellen?

Ids, Streetname...
Types based on only one field based on stereotypes
stereotype definition?

You could call these:
Single-value object
Single-value Value Object
Atomic Value Object
Scalar Value Object
I would probably use Single-Value Value Object if you want the distinction to be explicit.
2. Composite Value Object

One important distinction: Money is often considered a value object even though it might only have two fields (amount, currency). The distinction is therefore not really about the number of fields, but about whether the object's state is atomic or composite from the domain's perspective.
If you're designing a Java type hierarchy or DSL, I'd use Scalar/Atomic Domain Type vs. Composite Domain Type if the distinction is important to your model.


Data:
    Service, Strategy?
    Service and Strategy as extensdion
    without state?

task data to source:
    model file
    functionalModel ->  modelIntermediate
    call intermediateToSource

three actions:
    load model
    functional to modelIntermediate
    call intermediateToSource

später 
   modeltosource






Javadoc klären
perhaps easier to use extra tags and than replace through java doc
+ parsing of java doc 

suche klären
struktur in ordner übernehmen

logging bei hand

Test for Parameters in ParseMethod

clear and test llm config

    
Data, funcition -> Intermediate
define Java Parser as data, function
Action -> Intermediate

logik intermediate:
intermediate model calculate all methods
Inheritance tree
prompt inheritance prompt resolution
-> by intermediate model
type resolution
-> by intermediate model
    

stereotype for models?:
Model:
   classes
   yaml parser
   model -> intermediate


StopAt X
Restart X
for example first create class
than test 
requires to save the created classes 
and than reload

similar intermediate files
define where it makes sense to stop 
requires a save and reload
for example which classes to include
or the created intermediate model
and support for multiple variations 
    workspace a contains variation one
    workspace b contains variation two
stuff like inline static methods
delombok yes/no

makes sense to allow the creation, tools used for intermediate as control 
in the higher model/extra model
so it is not neccesary to save the intermediate model?
of course forvexpreimenting?
but propably easier to create the class perhand if really impossibkle?
or extravtool loike implementClass for special, very complicated stuff?

so:
model (serialized..)
can be generated as multiple alternatives using llm
contains all control directives to create intermediate
allows oveerrides through directives?
for example order?
includes?
perhaps create prompt as intermediate step?

intermediate -> soure
than save and create test

saving intermediate is propably mire useful for debugging
nit so much real usage?



implement assertion in TypeToTextTest

test only for model (reload class)
check

templates from .agentelf template folder

tests on method basis?


module interfaces
module definition


explain, create doku (uml and html or yml)
find usage
 where is x called for example logger.warn
which questions to answer?


extend class
logging

type resolution
   primitive types
   java.lang... (known types from github)
      open when to include, blacklist based
   newly generated

end to end documentation


modellierung anhand function modell
usage from sequence diagram?
test modell ausarbeiten
variationen?

based on intermediate
action -> intermediate
javaparser
search, include
path, correct dir
uml diagram -> uml description
uml -> Intermediate
extends, implements, uses
based on graph sorting

-> resolve prompts
-> super in prompts?
resolve @ in prompt
pattern support?


data -> Intermediate
function call resolution
based on graph

unit tests for actions


next: Usage Model, Change Model, Update mechanism, modules, model for e2e test, e2e tests for task
update mechanism


build, retry?
other llm calls?


example based prompts?


Support for utility functions: use for:...
always in context?
extended search?


refactoring suggestions
+ tidy

prompt as part of history/search


variations:
    how to create multiple variations/models?
    from textual description?
    how to try?


stereotypes
@ resolution

LLM -> setMethod
setAnnotation
setDocumentation

checkFields
checkMethods

später: 
    allgemein prüfungen später
    import berechnung
    prüfung felder
    add and checks import






