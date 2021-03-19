package com.kgk;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import io.micronaut.function.aws.proxy.MicronautLambdaContainerHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LambdaHandler implements RequestStreamHandler {

    private static final MicronautLambdaContainerHandler handler;

    static {
      try {
        handler = new MicronautLambdaContainerHandler();
      } catch (ContainerInitializationException e) {
        e.printStackTrace();
        throw new RuntimeException("Could not initialize Micronaut", e);
      }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
        throws IOException {
      handler.proxyStream(inputStream, outputStream, context);
    }
}
