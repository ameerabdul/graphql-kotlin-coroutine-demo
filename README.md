## About the repo
* GraphQL kotlin spring boot server for demoing Kotlin co-routines

## Demo Setup
* `mvn clean install`
* `git fetch --tags` to pull down all the tags
* `git checkout step-0` to get started with a non-lazy version of `PropertyResults`

### Session details

During the session we will work towards converting `PropertyResults` downstream calls to lazy co-routines
* `step-1` Duplicate downstream calls
* `step-2` Converting `PropertyResults` to lazy
* `step-3` Converting `PropertyResults.properties` to lazy

### Downstream calls

![Downstream calls](docs/downstream-api-calls.png)