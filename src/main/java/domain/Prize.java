package domain;

public enum Prize {
    NONE(0, false, 0),
    FIFTH_PRIZE(3, false, 5000),
    FOURTH_PRIZE(4, false, 50000),
    THIRD_PRIZE(5, false, 1500000),
    SECOND_PRIZE(5, true, 30000000),
    FIRST_PRIZE(6, false, 2000000000);

    private final int toWin;
    private final boolean requireBonus;
    private final int reward;

    Prize(int toWin, boolean requireBonus, int reward) {
        this.toWin = toWin;
        this.requireBonus = requireBonus;
        this.reward = reward;
    }

    public int getToWin() {
        return toWin;
    }

    public boolean getRequireBonus() {
        return requireBonus;
    }

    public int getReward() {
        return reward;
    }

    public boolean isMatchedPrize(int matched) {
        return matched == toWin;
    }

    public static Prize matchPrize(int matched, boolean bonusMatched) {
        if (SECOND_PRIZE.isMatchedPrize(matched) && bonusMatched) {
            return SECOND_PRIZE;
        }

        for (Prize prize : Prize.values()) {
            if (prize.isMatchedPrize(matched)) {
                return prize;
            }
        }

        return NONE;
    }
}
