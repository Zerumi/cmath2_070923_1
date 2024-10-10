# cmath2_070923_1 [![CodeFactor](https://www.codefactor.io/repository/github/zerumi/cmath2_070923_1/badge)](https://www.codefactor.io/repository/github/zerumi/cmath2_070923_1)

Computational mathematics Lab #2, ITMO SE (var no 1)

---

# What is this?

This is a full-stack non-linear equation and system of equations solver. You enter some algebraic equation (or system of equations), provide information about root range, and the program will find a [root of this function/system](https://simple.wikipedia.org/wiki/Root_of_a_function)!
- Support parsing of any equation
- Dynamically updating graph on front-side (using Desmos API)
- Solution are calculating by numerical methods (Half Division, Simple Iteration, Newton Methods)
- (<3) Written in pure Kotlin/JS (front-end) & Kotlin/JVM (back-end) | KVision + Ktor

Here's a demo!


https://github.com/user-attachments/assets/6c49902c-a627-47a7-b620-931761e371db

# How to run?

Just clone this repository, and write:
```
./gradlew -t jsRun
./gradlew -t jvmRun
```
for front and back-end respectively. Full guideline available [here](https://kvision.gitbook.io/kvision-guide/6.-full-stack-development-guide/setting-up-1)  
Only front-end part of this project available on [SE Ifmo faculty hosting](https://se.ifmo.ru/~s367837/cmath2_070324_1-1.0.0-SNAPSHOT/)
