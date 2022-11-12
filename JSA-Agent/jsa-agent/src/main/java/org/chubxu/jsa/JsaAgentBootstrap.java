package org.chubxu.jsa;

import java.lang.instrument.Instrumentation;
import java.util.Map;

/**
 * @ClassName JsaAgentBootstrap
 * @Description Agent 启动类
 * @Since 1.0.0
 * @Date 2022/11/12 17:03
 * @Author chubxu
 */
public class JsaAgentBootstrap {
    public static void premain(String[] args, Instrumentation inst) {
        Map<String, Object> argsMap = parseArgs(args);
        System.out.println("premain started");
    }

    public static void agentmain(String[] args, Instrumentation inst) {
        Map<String, Object> argsMap = parseArgs(args);
        System.out.println("agentmain started");
    }

    public static Map<String, Object> parseArgs(String[] args) {
        return null;
    }
}
