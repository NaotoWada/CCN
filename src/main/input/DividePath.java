package main.input;

/**
 *
 * @author Naoto Wada
 *
 */
public class DividePath {

    /**
     * 入力値がパス形式の場合、入力パスを分割して配列にする.
     * <p>
     * @param input 入力値
     * @return 階層で分割後の配列
     */
    public static String[] divide(String input) {
        return input.split(InputConsts._PathDiv);
    }

}
