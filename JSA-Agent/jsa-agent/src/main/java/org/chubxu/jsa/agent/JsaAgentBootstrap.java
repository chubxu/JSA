package org.chubxu.jsa.agent;

import java.lang.instrument.Instrumentation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName JsaAgentBootstrap
 * @Description Agent 启动类
 * @Since 1.0.0
 * @Date 2022/11/12 17:03
 * @Author chubxu
 */
public class JsaAgentBootstrap {

    private static final Map<String, ClassLoader> namespaceToClassLoader = new ConcurrentHashMap<>();

    private static final String JSA_CORE_SERVER = "org.chubxu.jsa.core.server.impl.JsaCoreNettyServer";
    public static final String JSA_CORE_SERVER_GET_INSTANCE_METHOD = "getInstance";
    public static final String JSA_CORE_SERVER_IS_BIND_METHOD = "isBind";
    public static final String JSA_CORE_SERVER_BIND_METHOD = "bind";

    public static void premain(String args, Instrumentation inst) throws Exception {
        JsaAgentConfigure jsaAgentConfigure = parseArgs(args);
        System.out.println("premain started");
        attach(inst, jsaAgentConfigure);
    }

    public static void agentmain(String args, Instrumentation inst) throws Exception {
        JsaAgentConfigure jsaAgentConfigure = parseArgs(args);
        System.out.println("agentmain started");
    }

    public static JsaAgentConfigure parseArgs(String args) throws MalformedURLException {
        JsaAgentConfigure configure = new JsaAgentConfigure();
        configure.setNamespace(JsaAgentClassLoader.DEFAULT_NAMESPACE);
        configure.setUrls(new URL[]{new URL("file:JSA-Agent\\jsa-core\\src\\main\\java\\org\\chubxu\\jsa\\core")});
        return configure;
    }

    private static void attach(Instrumentation inst, JsaAgentConfigure configure) throws Exception {
        ClassLoader loader = loadOrDefineClassLoader(configure);

        Class<?> jsaCoreServerCLass = loader.loadClass(JSA_CORE_SERVER);
        Object jsaCoreServer = jsaCoreServerCLass.getMethod(JSA_CORE_SERVER_GET_INSTANCE_METHOD, Instrumentation.class)
                .invoke(null, inst);

    }

    private static ClassLoader loadOrDefineClassLoader(JsaAgentConfigure configure) {
        String namespace = configure.getNamespace();
        if (Objects.nonNull(namespaceToClassLoader.get(namespace))) {
            return namespaceToClassLoader.get(namespace);
        }
        JsaAgentClassLoader loader = new JsaAgentClassLoader(configure.getUrls(), configure.getNamespace());
        namespaceToClassLoader.put(namespace, loader);
        return loader;
    }
}
