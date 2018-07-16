package com.jubaka.remoting.model.impl;

import com.jubaka.remoting.model.RemoteFutureController;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class RemoteFutureControllerImpl implements RemoteFutureController {
    private Random r = new Random();
    private HashMap<Long,Future> futureContainer = new HashMap<>();


    public long addFuture(Future f) {
        long id = r.nextLong(); // replace with uuid
        futureContainer.put(id,f);
        return id;
    }


    @Override
    public Object get(long id) throws InterruptedException, ExecutionException {
        return futureContainer.get(id).get();
    }

    @Override
    public Object get(long id, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return futureContainer.get(id).get(timeout,unit);
    }

    @Override
    public boolean isDone(long id) {
        return futureContainer.get(id).isDone();
    }

    @Override
    public boolean isCancelled(long id) {
        return futureContainer.get(id).isCancelled();
    }

    @Override
    public boolean cancel(long id, boolean mayInterruptIfRunning) {
        return futureContainer.get(id).cancel(mayInterruptIfRunning);
    }
}
