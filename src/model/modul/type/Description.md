A collection of all referenced Type for resolution of inheritance
and loading of classes from external sources for example maven

we have three types of classes
from the model (e.g. with prompt)
from source (e.g. with documentation)
from class (only meta info)


Prompt Calculation:
Start by current class
go backwards and calculate the complete Path
for each Path start at parent calculate prompt for method
if prompts from different pathes are different throw error


method resolution:
   basically is this method the same based on the name and the parameter types
   can variable x used for type y
   here we only need the relation ship between all imported classes 


type resolution
   can type x used for type y only for currently imported classes

inheritance tree:
    find potential classes for import (by name)
    get all relations between x and y (set of classes)
    rather large tree

we propably need a cache for the jars... for the project
    

