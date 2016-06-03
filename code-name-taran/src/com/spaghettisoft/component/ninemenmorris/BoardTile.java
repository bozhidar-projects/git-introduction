package com.spaghettisoft.component.ninemenmorris;

public class BoardTile {
    public static final String WHITE = "White";
    public static final String BLACK = "Black";
    public static final String EMPTY = "Empty";

    private String poolType;
    private String background;

    public BoardTile(String background) {
        this.background = background;
        this.poolType = EMPTY;
    }

    public void addWhitePool() {
        poolType = WHITE;
    }

    public void addBlackPool() {
        poolType = BLACK;
    }

    public void emptyTile() {
        poolType = EMPTY;
    }

    public String getBackground() {
        return background;
    }

    public void setPoolType(String poolType) {
        this.poolType = poolType;
    }

    public String getPoolType() {
        return poolType;
    }

    public String getVisalization() {
        if (poolType.equals(WHITE)) {
            return " (" + background + ") ";
        }
        if (poolType.equals(BLACK)) {
            return " [" + background + "] ";
        }
        return "  " + background + "  ";
    }
}
