/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.paboo.leaf.proto.LeafReq;
import org.paboo.leaf.proto.LeafResp;
import org.paboo.leaf.proto.LeafServiceGrpc;

import java.util.concurrent.TimeUnit;

/**
 * @author Leonard Woo
 */
public class Client {


    private final ManagedChannel channel;
    private final LeafServiceGrpc.LeafServiceBlockingStub blockingStub;

    public Client(String host, int port) {
        this( ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() );
    }

    Client(ManagedChannelBuilder<?> channelBuilder) {
        this.channel = channelBuilder.build();
        this.blockingStub = LeafServiceGrpc.newBlockingStub(this.channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    public long getId(){
        LeafReq req = LeafReq.newBuilder().setServiceId(1).setNodeId(1).build();
        LeafResp resp = blockingStub.idGen(req);
        return resp.getId();
    }


    public static void main(String[] args) throws Exception{
        Client client = new Client("localhost", 9800);
        System.out.println(client.getId());
    }
}