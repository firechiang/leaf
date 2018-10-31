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

package org.paboo.leaf.entity;

import lombok.Getter;
import lombok.Setter;
import org.paboo.leaf.utils.JsonUtil;

/**
 * @author Leonard Woo
 */
@Getter
@Setter
public class RpcEntity {

    private String host;

    private Integer port;

    public RpcEntity() {
    }

    public RpcEntity(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
