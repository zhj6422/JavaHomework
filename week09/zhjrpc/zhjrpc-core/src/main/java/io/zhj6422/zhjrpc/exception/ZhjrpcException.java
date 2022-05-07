package io.zhj6422.zhjrpc.exception;

public class ZhjrpcException extends RuntimeException{
    public ZhjrpcException(){
        super();
    }

    public ZhjrpcException(String exception){
        super(exception);
    }

    public ZhjrpcException(Throwable cause){
        super(cause);
    }

    public ZhjrpcException(String message, Throwable cause){
        super(message, cause);
    }
}
