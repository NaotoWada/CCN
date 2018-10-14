package main.method.count;

import java.util.List;

/**
 * ステップ数をカウントするクラス
 * @author Naoto Wada
 *
 */
public class StepCounter {

    /**
     * 関数のステップ数をカウントする
     * @param mContents 関数の全行
     */
    public static int count(List<String> mContents) {
        return mContents.size();
    }

}
