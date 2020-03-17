package com.madman.doc.protocol;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

/**
 * 超文本传输协议，基于TCP/IP
 */
@Slf4j
public class Http {

    public static void main(String[] args) {
        System.out.println("Http.main");
        System.out.println("args = " + Arrays.deepToString(args));
        System.out.println("args = " + args);

        try {
            test();

            try {
                log.error("====== main =============");
            } catch (Exception e) {
                log.error("main", e);
            } finally {
                log.error("========= main finally==  ====");
            }
        } catch (Exception e) {
            log.error("last ======");
        }

    }

    public static void test() {
        try {
            if (true) {
                throw new IOException();
            }
        } catch (IOException e) {
            log.error("test ", e);
        }
    }

}
