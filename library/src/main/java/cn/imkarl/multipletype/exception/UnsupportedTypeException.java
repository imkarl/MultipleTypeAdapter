package cn.imkarl.multipletype.exception;

/**
 * 不支持的类型
 * @author imkarl
 */
public class UnsupportedTypeException extends RuntimeException {

    public UnsupportedTypeException() {
    }
    public UnsupportedTypeException(String detailMessage) {
        super(detailMessage);
    }
    public UnsupportedTypeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
    public UnsupportedTypeException(Throwable throwable) {
        super(throwable);
    }

}
