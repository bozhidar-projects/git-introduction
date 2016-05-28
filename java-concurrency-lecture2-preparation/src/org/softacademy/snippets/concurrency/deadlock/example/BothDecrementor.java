package org.softacademy.snippets.concurrency.deadlock.example;

import org.softacademy.snippets.concurrency.common.MultiColorBallPool;
import org.softacademy.snippets.concurrency.common.MultiColorBallPoolModifier;

public class BothDecrementor extends MultiColorBallPoolModifier {

    public BothDecrementor(int timesToIncrement,
            MultiColorBallPool multiColorBallPool) {
        super(timesToIncrement, multiColorBallPool);
    }

    @Override
    protected void modifyPool() {
        multiColorBallPool.decrementBoth();
    }

}
