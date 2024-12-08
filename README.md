# Mini Compiler (Variable Declaration)

**A simple java program that demonstrates the compiler process, lexical, syntax, and semantic. This program only accepts variable declaration in java.** <br>

    Note: This project a requirement for our Final Term 
    in the course Theory of Programming Languages,
    1st Sem AY 2024-2025.
    - J.ALBAN & K.BANGAHON


<br>


## This Source Code Contains
📁 `public/` - contians imgs, svgs, etc.<br>
📁 `testfiles/` - contains 4 text files for testing <br>
<img src="public/java-svgrepo-com.svg" alt="java" width="20"/> `Lexical Analyzer.java` - java class for lexical phase of compiler <br>
<img src="public/java-svgrepo-com.svg" alt="java" width="20"/> `Syntax Analyzer.java` - java class for syntax phase of compiler  <br>
<img src="public/java-svgrepo-com.svg" alt="java" width="20"/> `Semantic Analyzer.java` - java class for semantic phase of compiler  <br>
<img src="public/java-svgrepo-com.svg" alt="java" width="20"/> `Main.java`  - java class that contains the main method <br>


<br>

## Flowchart
This shows the phases of the compiler and logic for the program. 

![flowchart](public/flowchart.jpg) <br>



<br>

## The UI
The simple Java GUI contains 3 panels, the result panel at the top, the buttons at the left, and the output panel on the right.
 <br><br>
![alt text](public/lexical.png) <br>

<img src="public/upload-file.png" alt="java" width="20"/> `Open File` - opens a file limited to .txt only. <br>
<img src="public/chart.png" alt="java" width="20"/> `Lexical Analysis` - Starts the Lexical analysis phase to tokenize each term in the input file. Can only start once file is uploaded. <br>
<img src="public/gold.png" alt="java" width="20"/> `Syntax Analysis` - Starts the Syntax Phase where tokens are parsed and are analyzed based on grammar. Can only start once Lexical is accepted. <br>
<img src="public/tick.png" alt="java" width="20"/>`Semantic Analysis` - Starts Semantic Phase for type checking, see if the data type matches the value of the variable. Can only start once Syntax is accepted. <br>
<img src="public/delete.png" alt="java" width="20"/>`Clear` - Clears everything and starts over to Open File
 <br><br>

 ## Test Cases
`📁 testfiles/` contains 3 test cases. <br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="public/check.png" alt="java" width="20"/> [1] *int var = 0;* <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Accepted**. Proper variable declaration
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<img src="public/wrong.png" alt="java" width="20"/>
 [2] *int v@r = 55;* <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Failed in Lexical.** Token ' v@r ' is an inappropriate variable name
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="public/wrong.png" alt="java" width="20"/>
[3] **String var = "hello"** <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Failed in Syntax.** Missing delimeter ' ; '
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="public/wrong.png" alt="java" width="20"/>
[4] **boolean var = "maybe";** <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Failed in Semantic.** Value does not match data type.
  

 <br><br>


    Instructor: Prof. Irysh Paulo R. Tipay
    Students: Jhoen Alban & Keanu Bangahon
    Theory of Programming Languages Final Term 2024-2025
