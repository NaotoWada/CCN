package main.method.count.util;

/**
 * 数値カウントのユーティルクラス.
 * <p>
 * @author Naoto Wada
 *
 */
public class CountUtils {

    /**
     * 入力値の小数点第二以下を切り捨てる.
     * @param input
     * @return 切り捨て後の値
     */
    public static double convertSecondDecimal(double input) {
        String formatted = String.format("%.2f", input);
        return Double.parseDouble(formatted);
    }
}
