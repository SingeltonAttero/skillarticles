package ru.skillbranch.skillarticles

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class RxImmediateSchedulerRule : TestRule {
    private val immediateScheduler = object : Scheduler() {
        @NonNull
        override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
            // Hack to prevent stack overflows in unit tests when scheduling with a delay;
            return super.scheduleDirect(run, 0, unit)
        }

        @NonNull
        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker(Executor {
                it.run()
            }, true, true)
        }
    }

    @NonNull
    override fun apply(@NonNull base: Statement, @NonNull description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }
                RxJavaPlugins.setInitComputationSchedulerHandler { immediateScheduler }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { immediateScheduler }
                RxJavaPlugins.setInitSingleSchedulerHandler { immediateScheduler }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediateScheduler }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}