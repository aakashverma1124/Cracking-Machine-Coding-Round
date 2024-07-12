# Design API Rate Limiter
### Duration - 1 Hour

When an API becomes available to the public, there can be a large number of users and services. Anyone can use it at any time and as much as they want, potentially preventing other legitimate users. Moreover, the API providers have limited resources per unit of time provisioned, and they want their services to be fairly available to all of their customers.

The API rate limiter throttles clients' requests that exceed the predefined limit in a unit time instead of disconnecting them. Throttling refers to controlling the flow by discarding some of the requests. It can also be considered a security feature to prevent bot and DoS attacks that can overwhelm a server by sending a burst of requests.

### Problem Statement

Create a system for rate limit.

Each customer can make X requests per Y seconds. 
Perform rate limiting logic for provided customer ID. Return `true` if the request is allowed
and `false` if it is not.

You need to complete the below method.
```java
boolean rateLimit(int customerId)
```

- Your code should be scalable, extensible, and follow object-oriented principles if needed.
- Completeness matters
- More points will be given if you can implement thread safety.
- More points will be given if you can write test cases.

### Note:

I have implemented all 5 algorithms in this repository. But you will be expected to complete only one and if time permits you can talk about more.






