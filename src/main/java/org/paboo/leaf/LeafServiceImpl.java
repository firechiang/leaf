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

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import org.paboo.leaf.proto.LeafProto;
import org.paboo.leaf.proto.LeafReq;
import org.paboo.leaf.proto.LeafResp;
import org.paboo.leaf.proto.LeafService;

/**
 * @author Leonard Woo
 */
public class LeafServiceImpl extends LeafService implements BindableService {

    @Override
    public void idGen(RpcController controller, LeafReq request, RpcCallback<LeafResp> done) {
        BaseIdGenerator idGenerator = new BaseIdGenerator(request.getServiceId(), request.getNodeId());
        LeafResp resp = LeafResp.newBuilder().setId(idGenerator.nextId()).build();
        done.run(resp);
    }

    @Override
    public ServerServiceDefinition bindService() {
        return ServerServiceDefinition.builder("leaf").build();
    }
}
