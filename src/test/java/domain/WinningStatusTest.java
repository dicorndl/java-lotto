package domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningStatusTest {

    @Test
    public void 로또_1등() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 5, 6);
        WinningNumber winningNumber = makeWinningNumber(10, 1, 2, 3, 4, 5, 6);

        WinningStatus status = new WinningStatus(makeLottoGames(lotto), winningNumber);

        assertThat(status.getPrizeCount(Prize.FIRST_PRIZE)).isEqualTo(1);
    }

    @Test
    public void 로또_2등() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 5, 10);
        WinningNumber winningNumber = makeWinningNumber(10, 1, 2, 3, 4, 5, 7);

        WinningStatus status = new WinningStatus(makeLottoGames(lotto), winningNumber);

        assertThat(status.getPrizeCount(Prize.SECOND_PRIZE)).isEqualTo(1);
    }

    @Test
    public void 로또_3등() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 5, 6);
        WinningNumber winningNumber = makeWinningNumber(10, 1, 2, 3, 4, 5, 7);

        WinningStatus status = new WinningStatus(makeLottoGames(lotto), winningNumber);

        assertThat(status.getPrizeCount(Prize.THIRD_PRIZE)).isEqualTo(1);
    }

    @Test
    public void 로또_4등() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 5, 6);
        WinningNumber winningNumber = makeWinningNumber(10, 1, 2, 3, 4, 7, 8);

        WinningStatus status = new WinningStatus(makeLottoGames(lotto), winningNumber);

        assertThat(status.getPrizeCount(Prize.FOURTH_PRIZE)).isEqualTo(1);
    }

    @Test
    public void 로또_5등() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 5, 6);
        WinningNumber winningNumber = makeWinningNumber(10, 1, 2, 3, 7, 8, 9);

        WinningStatus status = new WinningStatus(makeLottoGames(lotto), winningNumber);

        assertThat(status.getPrizeCount(Prize.FIFTH_PRIZE)).isEqualTo(1);
    }

    @Test
    public void 수익률_계산() {
        Lotto lotto1 = makeLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = makeLotto(8, 9, 10, 11, 12, 13);
        WinningNumber winningNumber = makeWinningNumber(10, 1, 2, 3, 4, 5, 6);

        WinningStatus status = new WinningStatus(makeLottoGames(lotto1, lotto2), winningNumber);

        assertThat(status.getProfit()).isEqualTo(0.5);
    }

    private Lotto makeLotto(Integer... numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers)
            .map(LottoNumber::new)
            .collect(Collectors.toList());

        return new Lotto(new LottoNumbers(lottoNumbers), true);
    }

    private WinningNumber makeWinningNumber(int bonus, Integer... numbers) {
        return new WinningNumber(Arrays.asList(numbers), new LottoNumber(bonus));
    }

    private LottoGames makeLottoGames(Lotto... games) {
        LottoGames lottoGames = new LottoGames();

        Arrays.asList(games).forEach(lottoGames::add);

        return lottoGames;
    }
}
