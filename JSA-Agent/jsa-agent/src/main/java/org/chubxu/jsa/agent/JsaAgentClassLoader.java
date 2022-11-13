package org.chubxu.jsa.agent;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @ClassName JsaClassLoader
 * @Description TODO
 * @Since 1.0.0
 * @Date 2022/11/13 10:36
 * @Author chubxu
 */
public class JsaAgentClassLoader extends URLClassLoader {

    public static final String DEFAULT_NAMESPACE = "default_namespace";

    private final String namespace;
    private final URL[] urls;

    public JsaAgentClassLoader(URL url) {
        this(new URL[]{url});
    }

    public JsaAgentClassLoader(URL[] urls) {
        super(urls);
        this.urls = urls;
        this.namespace = DEFAULT_NAMESPACE;
    }

    public JsaAgentClassLoader(URL url, String namespace) {
        this(new URL[]{url}, namespace);
    }

    public JsaAgentClassLoader(URL[] urls, String namespace) {
        super(urls);
        this.urls = urls;
        this.namespace = namespace;
    }

    @Override
    public URL getResource(String name) {
        URL url = findResource(name);
        if (Objects.nonNull(url)) {
            return url;
        }
        return super.getResource(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        Enumeration<URL> urls = findResources(name);
        if (null != urls) {
            return urls;
        }
        return super.getResources(name);
    }

    public String getNamespace() {
        return namespace;
    }

    public URL[] getUrls() {
        return urls;
    }
}
