package org.softacademy.snippets.concurrency.common;

public abstract class MultiColorBallPoolModifier extends Modifier {
    protected MultiColorBallPool multiColorBallPool;

    public MultiColorBallPoolModifier(int timesToIncrement,
            MultiColorBallPool multiColorBallPool) {
        super(timesToIncrement);
        this.multiColorBallPool = multiColorBallPool;
    }

}
