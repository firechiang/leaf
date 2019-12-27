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

package org.paboo.leaf;

import io.grpc.stub.StreamObserver;
import org.paboo.leaf.proto.LeafMsg;
import org.paboo.leaf.proto.LeafReq;
import org.paboo.leaf.proto.LeafResp;
import org.paboo.leaf.proto.LeafServiceGrpc;

/**
 * @author Leonard Woo
 */
public class LeafServiceImpl extends LeafServiceGrpc.LeafServiceImplBase {

    @Override
    public void idGen(LeafReq request, StreamObserver<LeafResp> responseObserver) {
        SnowflakeImpl sf = new SnowflakeImpl(request.getServiceId(), request.getNodeId());

        responseObserver.onNext(LeafResp.newBuilder().setId(sf.nextId()).build());

        responseObserver.onCompleted();
    }

    @Override
    public void idPar(LeafResp request, StreamObserver<LeafMsg> responseObserver) {
        SnowflakeImpl sf = new SnowflakeImpl(0,0);

        responseObserver.onNext(LeafMsg.newBuilder().setMessage(sf.formatId(request.getId())).build());

        responseObserver.onCompleted();
    }

}
