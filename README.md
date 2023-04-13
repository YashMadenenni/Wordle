# SEP Practical-1

### Build

##### Requirements

1. Should have installed Maven.
2. Navigate to wordle, where pom.xml file is present.(`cd Wordle/wordle/`)

##### Building the project
Navigate to wordle dir
Type commmand: 
```
mvn package
```
This builds the project and creates a jar file which can be used to execute the program.

it would look like `Building jar: C:\Users\Yashw\OneDrive\Documents\GitHub\SEPPractical1\wordle\target\wordle-1.0-SNAPSHOT.jar`

### Testing

To test the test file enter the command: 
```
mvn test -Dtest=WordleAppTest
```
### Running the code

To run the code use the command: 
```
java -cp target\wordle-1.0-SNAPSHOT.jar stacs.wordle.WordleApp
```
