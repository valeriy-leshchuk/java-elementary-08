package org.demo.birds.application;

import org.demo.birds.processor.IInfiniteLoopProcessor;

public class BirdStoreApplication {

    public static void main(String[] args) {
         IInfiniteLoopProcessor userCommandProcessor = null; //создать реальный объект
         userCommandProcessor.processInLoop();
    }
}
