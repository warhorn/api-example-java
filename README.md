# Java API Example

This is an example of how to use the [Warhorn API](https://warhorn.net/developers/docs/products/graphql-api/overview) in a Java application.

## Build the app and run the tests

```text
$ ./gradlew build

BUILD SUCCESSFUL in 309ms
7 actionable tasks: 7 up-to-date
```

## Run the app at the command line

Before running the program, you must store a [Warhorn API access token](https://warhorn.net/developers/docs/guides/access-tokens) in the `WARHORN_API_TOKEN` environment variable.

The command line arguments are as follows:

1. `slug`: the unique identifier for the event (found in Warhorn event page URLs)
2. `email`: the email address of the user whose registration you want to find

Note that the app is actually run by Gradle. It requires you to wrap this program's command line arguments in an `--args` command line argument of its own. Also note that you need to wrap this program's arguments in double quotes. See the example below.

```text
$ export WARHORN_API_TOKEN=<your API token>
$ ./gradlew run --args "bionic-dwarf test@example.com"

> Task :app:run
✅ Found an active, cleared registration for test@example.com at Bionic Dwarf.

BUILD SUCCESSFUL in 2s
3 actionable tasks: 1 executed, 2 up-to-date
```

Yes, this did actually run the tests! (*cough* test *cough*)

## Credit where it is due

The GraphQL code in this repository is a simplified "extract" from [GraphQL Spring Webclient](https://github.com/graphql-java-kickstart/graphql-spring-webclient). Many thanks to the [contributors](https://github.com/graphql-java-kickstart/graphql-spring-webclient/graphs/contributors)!

I chose not to use that library directly because I'm not working in a Spring Boot context and I didn't want to drag along all of that baggage. Understanding and reproducing a streamlined version of the original work was also a nice way to dip my toe back into Java after 10+ years off.
