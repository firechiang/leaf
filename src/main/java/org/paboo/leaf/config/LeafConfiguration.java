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

package org.paboo.leaf.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.paboo.leaf.entity.LeafConfigEntity;
import org.paboo.leaf.utils.IOUtil;
import org.paboo.leaf.utils.JsonUtil;

import java.io.*;

/**
 * @author Leonard Woo
 */
@Slf4j
public class LeafConfiguration {

    private static final LeafConfiguration INSTANCE = new LeafConfiguration();
    public static LeafConfiguration getInstance() {
        return INSTANCE;
    }

    private LeafConfiguration(){
    }

    public LeafConfigEntity loadConfig() {

        LeafConfigEntity conf = new LeafConfigEntity();
        try {
            String json = IOUtil.readerToString(getReader("leaf.json"));
            log.info(json);
            conf = JsonUtil.fromJson(json, LeafConfigEntity.class);

        } catch (IOException ex) {
            log.warn("File not found", ex);
        }
        return conf;
    }

    public void printBanner() {
        try {
            BufferedReader reader = getReader("banner.txt");
            String version = new MavenXpp3Reader().read(new FileReader("pom.xml")).getVersion();
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.contains("${version}")) {
                    line = line.replace("${version}", version);
                }
                System.out.println(line);
            }
        } catch (Exception ex) {
        }
    }

    private BufferedReader getReader(String name) {
        return IOUtil.loadStream(getPath(name));
    }

    private InputStream getPath(String name) {
        if (name.startsWith("/")) {
            name = name.substring(1);
        }
        ClassLoader cl = LeafConfiguration.class.getClassLoader();
        InputStream is = cl.getResourceAsStream(name);
        try {
            if (is == null) {
                is = IOUtil.getStream(new File(cl.getResource(name).getPath()));
            }
        } catch (FileNotFoundException e) {
            log.warn("File not found.", e);
        }
        return is;
    }
}
