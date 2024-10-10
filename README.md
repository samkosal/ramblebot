# ramblebot

A project to exercise Java, JUnit, git, GitHub, and code-reading skills. Students will create a language model to generate text.

## Expectations

### Academic Honesty

THIS IS AN INDIVIDUAL PROJECT. The following is not allowed:
- You MAY NOT copy any code from an AI.
- You MAY NOT paste any of the project or your code into an AI.
- You MAY NOT copy another student's code.
- You MAY NOT copy substantial portions of your solution from the internet.

You may:
- You are allowed to talk about the project generally with other students.
- You are allowed to get help from tutors, so long as you write all the code and they do not walk you through step by step.
- You are allowed to get help in office hours.
- You are allowed to use search engines (e.g. "find the last character of a string in Java"). If you copy a small line you found in a search, you must provide a link to where you found it in a comment, AND you must be prepared to thoroughly explain it. If you don't understand it, don't use it!

### Commits

YOU ARE EXPECTED TO MAKE SMALL, FREQUENT COMMITS. Doing so is good practice and helps me see that it's less likely you pasted in a large part of your solution from elsewhere.

## Setup

1. Fork and clone this project. MAKE SURE TO CLONE FROM YOUR FORK. The clone URL should have your username in it.
2. Change into the project directory:
    ```
    cd ramblebot
    ```
3. Open the project in VS Code.
    ```
    code .
    ```
    If the above command does not work, you can open VS Code manually and select the ramblebot folder to open.
4. Open `RambleApp.java`
5. Scroll to the bottom to find the `main` method. There should be a small grey "run" button above it. Click "Run". TODO: Add picture of run button.
6. It should ask you for a filename. Give it the following filename:
    ```
    wikipediaData.txt
    ```
    Then hit enter.
7. It should ask you for a number of words. Enter a positive integer and hit enter.
8. You should expect to see an error message. This is good! The error message should end like this:
    ```
    No tokens returned from tokenizer!
    This is probably because you haven't implemented it yet
    Begin with Wave 1 in the instructions, and implement LowercaseSentenceTokenizer
    If you have implemented it, there's a bug in your code where it's returning null for the tokens.
    ```
9. Open the testing side panel by clicking on the beaker on the left of your screen. TODO: add picture.
10. Click the triangle next to ramblebot.
11. You should expect to see all the tests fail. This is good! You haven't written your solution yet, so it's expected for them to fail.
12. Validate that you can push to your repo by making any change to this README, adding, committing, and pushing it.



