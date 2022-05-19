package com.dynatrace.sample.proto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.1)",
    comments = "Source: grpc_java.proto")
public class TechnologyCallGrpc {

  private TechnologyCallGrpc() {}

  public static final String SERVICE_NAME = "com.dynatrace.sample.proto.TechnologyCall";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Technology,
      Technology> METHOD_CALL_TECHNOLOGY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.dynatrace.sample.proto.TechnologyCall", "CallTechnology"),
          io.grpc.protobuf.ProtoUtils.marshaller(Technology.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Technology.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TechnologyCallStub newStub(io.grpc.Channel channel) {
    return new TechnologyCallStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TechnologyCallBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TechnologyCallBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static TechnologyCallFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TechnologyCallFutureStub(channel);
  }

  /**
   */
  public static abstract class TechnologyCallImplBase implements io.grpc.BindableService {

    /**
     */
    public void callTechnology(Technology request,
        io.grpc.stub.StreamObserver<Technology> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CALL_TECHNOLOGY, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CALL_TECHNOLOGY,
            asyncUnaryCall(
              new MethodHandlers<
                Technology,
                Technology>(
                  this, METHODID_CALL_TECHNOLOGY)))
          .build();
    }
  }

  /**
   */
  public static final class TechnologyCallStub extends io.grpc.stub.AbstractStub<TechnologyCallStub> {
    private TechnologyCallStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TechnologyCallStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TechnologyCallStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TechnologyCallStub(channel, callOptions);
    }

    /**
     */
    public void callTechnology(Technology request,
        io.grpc.stub.StreamObserver<Technology> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CALL_TECHNOLOGY, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TechnologyCallBlockingStub extends io.grpc.stub.AbstractStub<TechnologyCallBlockingStub> {
    private TechnologyCallBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TechnologyCallBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TechnologyCallBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TechnologyCallBlockingStub(channel, callOptions);
    }

    /**
     */
    public Technology callTechnology(Technology request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CALL_TECHNOLOGY, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TechnologyCallFutureStub extends io.grpc.stub.AbstractStub<TechnologyCallFutureStub> {
    private TechnologyCallFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TechnologyCallFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TechnologyCallFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TechnologyCallFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Technology> callTechnology(
        Technology request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CALL_TECHNOLOGY, getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL_TECHNOLOGY = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TechnologyCallImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(TechnologyCallImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL_TECHNOLOGY:
          serviceImpl.callTechnology((Technology) request,
              (io.grpc.stub.StreamObserver<Technology>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_CALL_TECHNOLOGY);
  }

}
