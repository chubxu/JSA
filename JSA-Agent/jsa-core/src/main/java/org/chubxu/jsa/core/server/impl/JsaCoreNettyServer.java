package org.chubxu.jsa.core.server.impl;

import org.chubxu.jsa.core.config.JsaCoreConfigure;
import org.chubxu.jsa.core.server.JsaCoreServer;

import java.lang.instrument.Instrumentation;
import java.util.Objects;

/**
 * @ClassName JsaCoreNettyServer
 * @Description TODO
 * @Since 1.0.0
 * @Date 2022/11/13 11:30
 * @Author chubxu
 */
public class JsaCoreNettyServer implements JsaCoreServer {

    private JsaCoreServer jsaCoreServer;
    private Instrumentation inst;
    private JsaCoreConfigure jsaCoreConfigure;

    private JsaCoreNettyServer(Instrumentation inst) {
        this.inst = inst;
        System.out.println("server started");
    }

    public JsaCoreServer getInstance(Instrumentation inst) {
        if (Objects.nonNull(jsaCoreServer)) {
            return jsaCoreServer;
        }
        jsaCoreServer = new JsaCoreNettyServer(inst);
        return jsaCoreServer;
    }
}
