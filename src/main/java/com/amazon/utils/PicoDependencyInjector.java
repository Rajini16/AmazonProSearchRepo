package com.amazon.utils;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class PicoDependencyInjector {
    private static MutablePicoContainer container;

    static {
        container = new DefaultPicoContainer();
        container.addComponent(TestContext.class);
    }

    public static MutablePicoContainer getContainer() {
        return container;
    }
}
