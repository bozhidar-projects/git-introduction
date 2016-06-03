package org.softacademy.snippets.concurrency;

import org.softacademy.snippets.concurrency.common.MultiColorBallPool;
import org.softacademy.snippets.concurrency.common.MultiColorBallPoolModifier;

public class BlueBallIncrementor extends MultiColorBallPoolModifier {
    public BlueBallIncrementor(int timesToIncrement,
            MultiColorBallPool multiColorBallPool) {
        super(timesToIncrement, multiColorBallPool);
    }

    @Override
    protected void modifyPool() {
        multiColorBallPool.incrementBlueBalls();
    }
}
