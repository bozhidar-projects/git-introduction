package org.softacademy.snippets.concurrency.common;

public abstract class PoolModifier extends Modifier {
    protected BallPool ballPool;

    public PoolModifier(int timesToIncrement, BallPool ballPool) {
        super(timesToIncrement);
        this.ballPool = ballPool;
    }

}
