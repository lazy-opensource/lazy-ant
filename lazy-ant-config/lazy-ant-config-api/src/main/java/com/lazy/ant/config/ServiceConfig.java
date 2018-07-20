package com.lazy.ant.config;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/6/29.
 */
public class ServiceConfig<T> extends AbstractConfig {

    protected Class<?> interfaceClass;
    private String interfaceName;
    private T ref;

    protected void export() throws Exception {
        interfaceClass = Class.forName(interfaceName, true, Thread.currentThread().getContextClassLoader());
        checkRef();

    }

    private void checkRef() {
        if (ref == null) {
            throw new IllegalStateException("ref not allow null!");
        }
        if (!interfaceClass.isInstance(ref)) {
            throw new IllegalStateException("The class "
                    + ref.getClass().getName() + " unimplemented interface "
                    + interfaceClass + "!");
        }
    }

    public String getInterface() {
        return interfaceName;
    }

    public void setInterface(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public T getRef() {
        return ref;
    }

    public ServiceConfig setRef(T ref) {
        this.ref = ref;
        return this;
    }
}
