Intermediate -> Source, Test

Client
Factory
Create -> Based On Task call in client

CreateIntermediateModel
   inheritance graph:
      parent to child for sorting 
      child to parent for method resolution
   map handle -> type

create order based on inheritance graph and usage + fields?
-> topolical sort with cycles?

method -> tree? (only declared?)

constellations

a implements B

a implements B , C implements D

a implements B implements D , E , C implements D

breadth first
path for check
linear durchlaufen?
if we visit breadth first and have found one declaration at one of the siblings
we know we need to go this way
we can than iterate over all to see if there are outside declarations


constructor
super resolution


if interface only declared methods
if class all methods
multiple super?
super copy + value from 

map and sort class 
two maps:
  key -> String (replace with result)
  key -> model probably better to model + test prompt
  list of keys for order

)
for each class 
   create prompt and for other classes without prompt
   call create Class
   store created class in map
for each class
   call create Test


createPrompt, createDoc:
   probably at the last possible time, e.g at the intermediate model
   needs all other info from the previous phases available


generic processing:
   propably best to do the resolution inside the intermediate layer?

only regenerate when changed
   could be done on the string of the class
   perhaps before method replacement, since method replacement can be done through refactoring
   and rule based methods excluded or marked as rule based


reload
implement Class propably also interesting


from sequence diagram zu class diagram zu descriotion zu intermediate

