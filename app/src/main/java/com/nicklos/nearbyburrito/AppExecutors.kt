package com.nicklos.nearbyburrito

import java.util.concurrent.Executor

/**
 * Global executor pools for the whole application.
 *
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
class AppExecutors(private val diskIO: Executor,
                   private val networkIO: Executor,
                   private val mainThread: Executor) {

    fun diskIO(func: () -> Unit) {
        diskIO.execute(func)
    }

    fun networkIO(func: () -> Unit) {
        networkIO.execute(func)
    }

    fun mainThread(func: () -> Unit) {
        mainThread.execute(func)
    }
}