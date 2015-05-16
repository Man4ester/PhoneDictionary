# PhoneDictionary


CONVENTIONS:
* separate words in the names of methods, classes and variables by lowercase and uppercase without '_';
* names in the database writes with lowercase and uppercase without '_';
* methods MUST BE SHORT (less than 20 lines)
* don't repeat your self;
* documentation of your code;
* use patterns;
* getters and setters always located under all methods;
* static fields and methods located at the beginning;
* all static fields writes with UPPERCASE;
* all interfaces should starts with I*Service;
* all services should be the same like interfaces but without first 'I';
* public interfaces and models must be in the 'phone-interfaces' project;
* all services and not public interfaces and models must be in the 'phone' project;
* branch configurations:
  *   all spaces rewrites with '_';
  *   if a modification 
    * adding new functionality, the branch should starts with 'feature/#number ticket + ticket title';
    * fixing old functionality, the branch should starts with 'bugfix/#number ticket + ticket title';
  * all commits should contains shot description
