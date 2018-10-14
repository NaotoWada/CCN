package main.method.count;

import java.util.ArrayList;
import java.util.List;

import main.method.detect.DetectorUtils;

/**
 * ネストをカウントするクラス.
 * <p>
 * @author Naoto Wada
 *
 */
public class NestCounter {

    /** ネスト数の初期値 .*/
    private static final int _InitNestCnt = 0;

    /** ネストが存在しない場合の初期値 .*/
    private static final int _NotExistElement = 0;

    /**
     * 関数内の最大ネスト数をカウントする.
     * <p>
     * @param mContents 関数内容
     * @return 最大ネスト数
     */
    public static int countMaxNest(List<String> mContents) {

        int nestCnt = _InitNestCnt;
        List<Integer> retList = new ArrayList<>();
        for (String line : mContents) {

            // ブロック開始がネストの加算条件
            if (DetectorUtils.isBlockStart(line)) {
                nestCnt++;
            }

            // ブロック開始→ブロック開始でネストの加算なので、終了したらネストは0になる
            if (DetectorUtils.isBlockEnd(line)) {
                retList.add(nestCnt);
                nestCnt = 0;
            }
        }
        return getMax(retList);
    }

    /**
     * 入力リストの最大値の返却.
     * @param retList
     * @return
     */
    private static int getMax(List<Integer> retList) {
        return retList.stream().mapToInt(s -> s).max().orElse(_NotExistElement);
    }

}
