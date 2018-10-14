package main.method.count;

import java.util.Arrays;
import java.util.List;

import main.dto.ClassDataObject.MethodObject;
import main.method.count.util.CountUtils;

/**
 * CCNをカウントするクラス.
 * <p>
 * @author Naoto Wada
 *
 */
public class CCNCounter {

    /** CCNの初期値 .*/
    private static final int _InitCCN = 1;

    /** CCNの平均値の初期値 .*/
    private static final double _InitAveCCN = 1.0;

    /** CCN対象文字列 .*/
    // FIXME : プロパティファイルにしたい
    private static final List<String> _CCN_Words = Arrays.asList(
            "if(",
            "if (",
            "for(",
            "for (",
            "while(",
            "while (",
            "case ",
            "||",
            "&&",
            ".orElse");

    /**
     * 関数のCNNをカウントする.
     * <p>
     * @param mContents 関数内容
     * @return CCN
     */
    public static int countCCN(List<String> mContents) {

        int ccn = _InitCCN;
        for (String line : mContents) {
            for (String ccnTerm : _CCN_Words) {
                if (line.contains(ccnTerm)) {
                    ccn++;
                }
            }
        }
        return ccn;
    }

    /**
     * 関数全体の平均CCNを算出する.
     * <p>
     * @param methods 関数リスト
     * @return 平均CCN
     */
    public static double countAve(List<MethodObject> methods) {
        double average = methods.stream().mapToInt(s -> s.get_CyclomaticComplexityNumber()).average()
                .orElse(_InitAveCCN);
        return CountUtils.convertSecondDecimal(average);
    }

}
