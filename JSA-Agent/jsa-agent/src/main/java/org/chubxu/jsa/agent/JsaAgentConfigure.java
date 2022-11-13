package org.chubxu.jsa.agent;

import java.net.URL;

/**
 * @ClassName JsaAgentConfigure
 * @Description JsaAgent 配置
 * @Since 1.0.0
 * @Date 2022/11/13 13:02
 * @Author chubxu
 */
public class JsaAgentConfigure {
    private String namespace;

    private URL[] urls;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public URL[] getUrls() {
        return urls;
    }

    public void setUrls(URL[] urls) {
        this.urls = urls;
    }
}
