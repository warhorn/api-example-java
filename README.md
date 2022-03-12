# Java API Example

## Build and Test

```sh
$ ./gradlew build

BUILD SUCCESSFUL in 309ms
7 actionable tasks: 7 up-to-date
```

## Run

Before running the program, you must store a [Warhorn API](https://warhorn.net/developers/docs/guides/access-tokens) token in the `WARHORN_API_TOKEN` environment variable.

```sh
$ export WARHORN_API_TOKEN=<your API token>
$ ./gradlew run --args paizocon-2022

> Task :app:run
Event: PaizoCon 2022

BUILD SUCCESSFUL in 1s
3 actionable tasks: 1 executed, 2 up-to-date
```
