package com.dynatrace.sample.proto;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpc_java.proto

public final class GrpcJava {
  private GrpcJava() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Technology_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Technology_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017grpc_java.proto\"\032\n\ncom.dynatrace.sample.proto.Technology\022\014\n\004tech\030" +
      "\001 \001(\t2>\n\016TechnologyCall\022,\n\016CallTechnolog" +
      "y\022\013.com.dynatrace.sample.proto.Technology\032\013.com.dynatrace.sample.proto.Technology\"\000B\007H\001P\001\210\001\001b\006" +
      "proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_Technology_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Technology_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Technology_descriptor,
        new java.lang.String[] { "Tech", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
