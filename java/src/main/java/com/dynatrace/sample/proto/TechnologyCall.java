package com.dynatrace.sample.proto;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc_java.proto

/**
 * Protobuf service {@code com.dynatrace.sample.proto.TechnologyCall}
 */
public  abstract class TechnologyCall
    implements com.google.protobuf.Service {
  protected TechnologyCall() {}

  public interface Interface {
    /**
     * <code>rpc CallTechnology(.com.dynatrace.sample.proto.Technology) returns (.com.dynatrace.sample.proto.Technology);</code>
     */
    public abstract void callTechnology(
        com.google.protobuf.RpcController controller,
        Technology request,
        com.google.protobuf.RpcCallback<Technology> done);

  }

  public static com.google.protobuf.Service newReflectiveService(
      final Interface impl) {
    return new TechnologyCall() {
      @java.lang.Override
      public  void callTechnology(
          com.google.protobuf.RpcController controller,
          Technology request,
          com.google.protobuf.RpcCallback<Technology> done) {
        impl.callTechnology(controller, request, done);
      }

    };
  }

  public static com.google.protobuf.BlockingService
      newReflectiveBlockingService(final BlockingInterface impl) {
    return new com.google.protobuf.BlockingService() {
      public final com.google.protobuf.Descriptors.ServiceDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }

      public final com.google.protobuf.Message callBlockingMethod(
          com.google.protobuf.Descriptors.MethodDescriptor method,
          com.google.protobuf.RpcController controller,
          com.google.protobuf.Message request)
          throws com.google.protobuf.ServiceException {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.callBlockingMethod() given method descriptor for " +
            "wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return impl.callTechnology(controller, (Technology)request);
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

      public final com.google.protobuf.Message
          getRequestPrototype(
          com.google.protobuf.Descriptors.MethodDescriptor method) {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.getRequestPrototype() given method " +
            "descriptor for wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return Technology.getDefaultInstance();
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

      public final com.google.protobuf.Message
          getResponsePrototype(
          com.google.protobuf.Descriptors.MethodDescriptor method) {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.getResponsePrototype() given method " +
            "descriptor for wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return Technology.getDefaultInstance();
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

    };
  }

  /**
   * <code>rpc CallTechnology(.com.dynatrace.sample.proto.Technology) returns (.com.dynatrace.sample.proto.Technology);</code>
   */
  public abstract void callTechnology(
      com.google.protobuf.RpcController controller,
      Technology request,
      com.google.protobuf.RpcCallback<Technology> done);

  public static final
      com.google.protobuf.Descriptors.ServiceDescriptor
      getDescriptor() {
    return GrpcJava.getDescriptor().getServices().get(0);
  }
  public final com.google.protobuf.Descriptors.ServiceDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }

  public final void callMethod(
      com.google.protobuf.Descriptors.MethodDescriptor method,
      com.google.protobuf.RpcController controller,
      com.google.protobuf.Message request,
      com.google.protobuf.RpcCallback<
        com.google.protobuf.Message> done) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.callMethod() given method descriptor for wrong " +
        "service type.");
    }
    switch(method.getIndex()) {
      case 0:
        this.callTechnology(controller, (Technology)request,
          com.google.protobuf.RpcUtil.<Technology>specializeCallback(
            done));
        return;
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public final com.google.protobuf.Message
      getRequestPrototype(
      com.google.protobuf.Descriptors.MethodDescriptor method) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.getRequestPrototype() given method " +
        "descriptor for wrong service type.");
    }
    switch(method.getIndex()) {
      case 0:
        return Technology.getDefaultInstance();
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public final com.google.protobuf.Message
      getResponsePrototype(
      com.google.protobuf.Descriptors.MethodDescriptor method) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.getResponsePrototype() given method " +
        "descriptor for wrong service type.");
    }
    switch(method.getIndex()) {
      case 0:
        return Technology.getDefaultInstance();
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public static Stub newStub(
      com.google.protobuf.RpcChannel channel) {
    return new Stub(channel);
  }

  public static final class Stub extends TechnologyCall implements Interface {
    private Stub(com.google.protobuf.RpcChannel channel) {
      this.channel = channel;
    }

    private final com.google.protobuf.RpcChannel channel;

    public com.google.protobuf.RpcChannel getChannel() {
      return channel;
    }

    public  void callTechnology(
        com.google.protobuf.RpcController controller,
        Technology request,
        com.google.protobuf.RpcCallback<Technology> done) {
      channel.callMethod(
        getDescriptor().getMethods().get(0),
        controller,
        request,
        Technology.getDefaultInstance(),
        com.google.protobuf.RpcUtil.generalizeCallback(
          done,
          Technology.class,
          Technology.getDefaultInstance()));
    }
  }

  public static BlockingInterface newBlockingStub(
      com.google.protobuf.BlockingRpcChannel channel) {
    return new BlockingStub(channel);
  }

  public interface BlockingInterface {
    public Technology callTechnology(
        com.google.protobuf.RpcController controller,
        Technology request)
        throws com.google.protobuf.ServiceException;
  }

  private static final class BlockingStub implements BlockingInterface {
    private BlockingStub(com.google.protobuf.BlockingRpcChannel channel) {
      this.channel = channel;
    }

    private final com.google.protobuf.BlockingRpcChannel channel;

    public Technology callTechnology(
        com.google.protobuf.RpcController controller,
        Technology request)
        throws com.google.protobuf.ServiceException {
      return (Technology) channel.callBlockingMethod(
        getDescriptor().getMethods().get(0),
        controller,
        request,
        Technology.getDefaultInstance());
    }

  }

  // @@protoc_insertion_point(class_scope:com.dynatrace.sample.proto.TechnologyCall)
}

