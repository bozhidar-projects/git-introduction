package org.softacademy.snippets.concurrency.onevariable;

import org.softacademy.snippets.concurrency.common.BallPool;
import org.softacademy.snippets.concurrency.common.PoolModifier;

public class Decrementer extends PoolModifier {

    public Decrementer(int timesToIncrement, BallPool ballPool) {
        super(timesToIncrement, ballPool);
    }

    @Override
    protected void modifyPool() {
        ballPool.decrementBalls();
    }

}
