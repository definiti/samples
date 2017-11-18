# Theater sample

This project show a real-life example of an aggregate and its tests.

## How to launch the project?

```bash
$ git clone -b theater https://github.com/definiti/samples.git theater
$ cd theater
$ nut run
$ sbt test
```

## Project structure

```
/
├── src/
    ├── main/definiti # Definiti source files
    ├── main/scala    # Generated scala files (becoming sbt source files)
    └── test/scala    # Scala test files
├── definiti.conf     # Configuration of the Definiti project
├── build.sbt         # Configuration of the sbt project
└── nut.yml           # Nut file to use Definiti
```