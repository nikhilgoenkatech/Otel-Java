package com.dynatrace.sample.springapp; 
                                
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;  
import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.context.propagation.TextMapSetter;
import org.springframework.http.HttpEntity;             
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
                
                        
import javax.annotation.Nullable;
import java.util.Map;   
import java.util.concurrent.TimeUnit;
                                
@RestController                                 
public class HelloController {                  
                                
        private static final String CALL_TO = System.getenv("CALL_TO");
        private static final boolean OTEL_IN = Boolean.parseBoolean(System.getenv("OTEL_IN"));
        private static final boolean OTEL_OUT = Boolean.parseBoolean(System.getenv("OTEL_OUT"));
        private static final String CUSTOM_HOSTNAME = System.getenv("CUSTOM_HOSTNAME");
        private boolean delayEnabled = false;
        private int delayDuration = 2;

        private static final TextMapSetter<MultiValueMap> setter = MultiValueMap::add;
        private static final TextMapGetter<Map<String, String>> getter =
                        new TextMapGetter<>() {
                                @Override
                                public Iterable<String> keys(Map<String, String> headers) {
                                        return headers.keySet();
                                }
                                @Nullable
                                @Override
                                public String get(@Nullable Map<String, String> headers, String headerName) {
                                        System.out.println(System.currentTimeMillis() + " printing headers in textMapGetter " + headers.get(headerName));
                                        return headers.get(headerName);
                                }
                        };  

        //endpoint 1 to enable 2seconds or configured delay
        @RequestMapping("/delay/enable")
        public String enableDelay(@RequestParam(required = false, defaultValue = "2", value = "seconds") String seconds) {
                delayEnabled = true;
                delayDuration = (seconds == null) ? 2 : Integer.parseInt(seconds);
                return CUSTOM_HOSTNAME + " got delay of " + delayDuration + " seconds enabled.";
        }   

        //endpoint 2 to disable delay
        @RequestMapping("/delay/disable")
        public String disableDelay() {
                delayEnabled = false;
                return CUSTOM_HOSTNAME + " delay disable.";
        }   

        //endpoint 3 to interact with the service or the entry point to interact with the service 
        @RequestMapping("/")
        public String index(@RequestHeader Map<String, String> headers) {
                System.out.println(System.currentTimeMillis() + " Headers when landing on service : " + CUSTOM_HOSTNAME + " " + headers );
                if (delayEnabled) {
                        //Sleep if delay is configured 
                        try {
                                TimeUnit.SECONDS.sleep(delayDuration);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }   
                }   

                if (OTEL_IN) {
                        //for java0, OTEL_IN is set to true while for java1, it is set to false
                        OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();
                        System.out.println(System.currentTimeMillis() + " OTEL_IN: " + openTelemetry);

                        // check if we have a remote parent
                        Context remoteCtx = openTelemetry.getPropagators().getTextMapPropagator().extract(Context.current(), headers, getter);
                        Tracer tracer = openTelemetry.getTracer("com.dynatrace.sample");
                        System.out.println(System.currentTimeMillis() + " The request has landed on core service from asset service extracted headers " + headers);
                        Span span = tracer.spanBuilder("requestIn").setParent(remoteCtx).startSpan();

                        try (Scope scope = span.makeCurrent()) {
                                span.setAttribute("hostname", CUSTOM_HOSTNAME);
                                System.out.println(System.currentTimeMillis() + " Service name " + CUSTOM_HOSTNAME + " "  + span);
                                return doCall();
                        } finally {
                                span.end();
                        }
                } else {
                        // no remote parent so this means request to another service or request would terminate here
                        System.out.println(System.currentTimeMillis() + " Skipping OTEL_IN as it is not set " + CUSTOM_HOSTNAME +  " calling doCall");
                        return doCall();
                }
        }

        private String doCall() {
                if (OTEL_OUT) {
                        //for java0, OTEL_OUT is set to true while for java1, it is set to false
                        OpenTelemetry openTelemetry = GlobalOpenTelemetry.get();
                        System.out.println(System.currentTimeMillis() + " Printing openTelemetry (OTEL_OUT): " + openTelemetry);

                        Tracer tracer = openTelemetry.getTracer("com.dynatrace.sample");
                        System.out.println(System.currentTimeMillis() + " setting tracer " + CUSTOM_HOSTNAME + " " + tracer);

                        Span span = tracer.spanBuilder("requestOut").startSpan();

                        try (Scope scope = span.makeCurrent()) {
                                span.setAttribute("hostname", CUSTOM_HOSTNAME);

                                if (CALL_TO.isEmpty()) {
                                        //for java0, call_to is enabled, so would not enter here for java0
                                        //for java1, call_to is empty, so would terminate here 
                                        return "Hello from Java (" + CUSTOM_HOSTNAME + ")";
                                } else {
                                        //for java0,context would be propagated 
                                        RestTemplate restTemplate = new RestTemplate();

                                        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
                                        openTelemetry.getPropagators().getTextMapPropagator().inject(Context.current(), headers, setter);
                                        System.out.println(System.currentTimeMillis() + " this is asset service calling core service, so pushing context: " + CALL_TO + " " + headers);

                                        ResponseEntity<String> response = restTemplate
                                                        .exchange("http://" + CALL_TO + ":8080", HttpMethod.GET, new HttpEntity(headers), String.class);

                                        return CUSTOM_HOSTNAME + " > " + response.getBody();
                                }
                        } finally {
                                System.out.println(System.currentTimeMillis()+ " Ending span " + span + " " + CUSTOM_HOSTNAME);
                                span.end();
                        }
                } else {
                        if (CALL_TO.isEmpty()) {
                                return "Hello from Java (" + CUSTOM_HOSTNAME + ")";
                        } else {
                                RestTemplate restTemplate = new RestTemplate();
                                ResponseEntity<String> response = restTemplate
                                                .getForEntity("http://" + CALL_TO + ":8080", String.class);

                                return CUSTOM_HOSTNAME + " > " + response.getBody();
                        }
                }
        }
}
