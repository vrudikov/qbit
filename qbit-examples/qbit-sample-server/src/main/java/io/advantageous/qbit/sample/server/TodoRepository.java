package io.advantageous.qbit.sample.server;

import java.util.List;

/**
 * Created by rhightower on 11/5/14.
 */
public interface TodoRepository {


    List<TodoItem> list();

    void add(TodoItem item);
}