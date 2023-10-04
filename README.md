# subscription-timer
This is a microservice to subscribe and unsubscribe microservices in cumulocity platform at configured timestamps.

At the moment we have 3 endpoints:
```
/setup/subscribeWithTimer
/setup/subscribe
/setup/unsubscribe
```

The main endpoint of microservice is:
```
{...}/setup/subscribeWithTimer
Objective: save configuration of subscribe time (start time) and unsubscribe time (end time) of a microservice (use id)
JSON body:
{
    "id": "21",
    "startTime": "2023-07-14T09:14:34.984Z",
    "endTime": "2024-08-14T09:14:34.984Z"
}
```

**Further development**: 
- Transform the above body into this two elements and save it into **MongoDB** instead of JPA at the moment.

```
[
    {"id": "21",
    "time": "2023-07-14T09:14:34.984Z",
    "action":"subscribe"},

    {"id": "21",
    "time": "2023-07-14T09:14:34.984Z",
    "action":"unsubscribe"},

]
```

- Use SQL to get the element with the earliest time and let the microservice to countdown and will do the defined action (subscribe/unsubscribe) for the microservice with assigned id.
- Add appropriate amount of time into the current time and use SQL again to get another element.

----
These tools are provided as-is and without warranty or support. They do not constitute part of the Software AG product suite. Users are free to use, fork and modify them, subject to the license agreement. While Software AG welcomes contributions, we cannot guarantee to include every contribution in the master project.
