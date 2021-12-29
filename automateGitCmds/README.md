#CS61B Git Submission Automation
Author: Meng Lu
## Why:
There will come a time in your 61b venture when you are dreadfully entering 
the repetitive lines of git commands and hoping to see a/another successful 
pass on the test that you are coding for. This anxiety of waiting to see 
your results are further elongated on top of the class server's processing 
of your solution. 

This is where this script comes in.

## Prerequisites:
With great power comes great responsibilities. Before we get into the how, it 
would be in your good conscious to at least get familiar with how this 
script can effectively help you. One of the first prerequisite is knowing 
how to use git, which you will be acquainted with in the first 
two weeks (if you experienced git before already). The last and 
most important prerequisite is knowing about file persistence, which is 
something you will learn towards the final phase of 61B.

TD;LR
you will need to know, conceptually, to a certain extent:
1) git commands
2) file persistence
3) Processes, which is the main thing that this script work.
4) Windows version of file name formatting as it is different from Mac/Linux.

## How to use:
Using terminal: 
1. go (cd) to the correct directory of this code you are saving this script in.
2. type javac autoGit.java (This will compile the java file into a .class file)
3. type java autoGit and file name that you want to git submit without the 
   file type suffix(This will run the java file)

Using IDE (e.g. IntelliJ IDEA):
1. Go to Edit Configuration
2. Hit "+" button
3. add a new Application
4. set the name to "autoGit"
5. set -cp to the directory of this code (should be repo or something like that)
6. set Main-Class to "autoGit"
7. set CLI argument to the file name you want to git submit without the file type suffix
8. click OK
9. run the application


#Thank you for reading.
