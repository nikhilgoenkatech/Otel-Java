### Scenario
service java0 is calling service java1. Both the services are running as different dockers.

#### Logs

##### Calling service java0
1653367730226 Headers when landing on service : java0 {host=localhost:8080, user-agent=curl/7.58.0, accept=*/*}
1653367730242 Skipping OTEL_IN as it is not set java0 calling doCall
1653367730261 Printing openTelemetry (OTEL_OUT): io.opentelemetry.javaagent.instrumentation.opentelemetryapi.ApplicationOpenTelemetry@7dc0c8dc
1653367730268 setting tracer java0 io.opentelemetry.javaagent.instrumentation.opentelemetryapi.trace.ApplicationTracer@1baae761
1653367730362 this is asset service calling core service, so pushing context: java1 {traceparent=[00-68b5ea826b274a9cf2f38a33ac5fe0eb-8d39e67ccec82006-01]}
1653367731183 Ending span io.opentelemetry.javaagent.instrumentation.opentelemetryapi.trace.ApplicationSpan@1ca3f5d4 java0

##### Called service java1
1653367731058 Headers when landing on java1 {accept=text/plain, application/json, application/*+json, */*, traceparent=00-68b5ea826b274a9cf2f38a33ac5fe0eb-b81ee26fc8527669-01, user-agent=Java/11.0.15, host=java1:8080, connection=keep-alive}
1653367731072 OTEL_IN: io.opentelemetry.javaagent.instrumentation.opentelemetryapi.ApplicationOpenTelemetry@f0402b
1653367731107 printing headers in textMapGetter 00-68b5ea826b274a9cf2f38a33ac5fe0eb-b81ee26fc8527669-01
1653367731110 printing headers in textMapGetter null
1653367731110 printing headers in textMapGetter null
1653367731110 The request has landed on core service from asset service extracted headers {accept=text/plain, application/json, application/*+json, */*, traceparent=00-68b5ea826b274a9cf2f38a33ac5fe0eb-b81ee26fc8527669-01, user-agent=Java/11.0.15, host=java1:8080, connection=keep-alive}
1653367731126 Service name java1 io.opentelemetry.javaagent.instrumentation.opentelemetryapi.trace.ApplicationSpan@6d56ab52