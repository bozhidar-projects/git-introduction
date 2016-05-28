package org.softacademy.snippets.concurrency;

import org.softacademy.snippets.concurrency.common.MultiColorBallPool;
import org.softacademy.snippets.concurrency.common.MultiColorBallPoolModifier;

public class BlueBallDecrementor extends MultiColorBallPoolModifier {

    public BlueBallDecrementor(int timesToIncrement,
            MultiColorBallPool multiColorBallPool) {
        super(timesToIncrement, multiColorBallPool);
    }

    @Override
    protected void modifyPool() {
        multiColorBallPool.decrementBlueBalls();
    }

}
