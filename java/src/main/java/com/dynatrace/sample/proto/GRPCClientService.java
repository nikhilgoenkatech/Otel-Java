package com.dynatrace.sample.proto;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GRPCClientService {
	private final int grpcServerPort = 8081;
	private final String grpcHost = "grpc-spring";

	public Technology getTechnology(Technology request) {
		ManagedChannel channel = null;
		Technology tech;
		try {
			channel = ManagedChannelBuilder.forAddress(grpcHost, grpcServerPort)
					.usePlaintext()
					.build();
			TechnologyCallGrpc.TechnologyCallBlockingStub stub = TechnologyCallGrpc.newBlockingStub(channel);
			tech = stub.callTechnology(
					Technology.newBuilder()
							.setTech(request.getTech())
							.build());
		} catch (Exception e) {
			return Technology.newBuilder().setTech(e.getLocalizedMessage()).build();
		} finally {
			channel.shutdown();
		}
		channel.shutdown();
		return tech;
	}


}
