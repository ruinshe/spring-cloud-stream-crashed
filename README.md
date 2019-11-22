Some investigation about the sample project:

### About the dependencies
The breakage is invoked when I add `org.springframework.cloud:spring-cloud-kubernetes-discovery`
to my `build.gradle`, on the other hand, I think the root caused is exactly the package itself and
not the dependencies of it, because I exclude all the dependencies of it and only keep
`org.springframework.cloud:spring-cloud-kubernetes-core`, and when I manually include the
`org.springframework.cloud:spring-cloud-kubernetes-core` without discovery dependencies,
the application also starts normally.

### About the application logs

When I remove the kubernetes related packages, I can find a log entry indicating 
the `springCloudBusOutput` is bind as the spring cloud bus output.
``` log
Binding :interface org.springframework.cloud.bus.SpringCloudBusClient:springCloudBusOutput
```

But when I added them back, this log entry is missing and the configuration process shows java 
bean `output` is not found.

### Dig into the spring code

When I debug on the issue, I figure out that the method
 `FunctionConfiguration.FunctionChannelBindingInitializer#afterPropertiesSet` maps each
 inputs and outputs together, and the only function wrapper is:
 
 ```
java.util.function.Function<io.fabric8.kubernetes.client.KubernetesClient, io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable<io.fabric8.kubernetes.api.model.Service, io.fabric8.kubernetes.api.model.ServiceList, java.lang.Boolean, io.fabric8.kubernetes.client.Watch, io.fabric8.kubernetes.client.Watcher<io.fabric8.kubernetes.api.model.Service>>>
```

And I found a class named `KubernetesClientServicesFunction` in that package which causes the bug.
