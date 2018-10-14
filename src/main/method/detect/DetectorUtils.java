package main.method.detect;

import java.util.Arrays;
import java.util.List;

public class DetectorUtils {

    /** 中括弧 開始 .*/
    private static final String _BlockStart = "{";

    /** 中括弧 終了 .*/
    private static final String _BlockEnd = "}";

    /** 関数検出用 単語リスト .*/
    private static final List<String> _MethodDetectionWords = Arrays.asList("void ", "private ", "protected ",
            "public ");

    /** 関数検出用 不要単語リスト .*/
    private static final List<String> _ExclusionWords = Arrays.asList("class ", "=");

    /** 関数検出用 必須単語 .*/
    private static final String _EssentialWord = "(";

    /**
     * 対象行で中括弧の開始が含んでいるかどうか判定する.
     * <p>
     * @param line 対象行
     * @return true:含んでいる/false:含んでいない
     */
    public static boolean isBlockStart(String line) {
        if (line.contains(_BlockStart)) {
            return true;
        }
        return false;
    }

    /**
     * 対象行で中括弧の終了が含んでいるかどうか判定する.
     * <p>
     * @param line 対象行
     * @return true:含んでいる/false:含んでいない
     */
    public static boolean isBlockEnd(String line) {
        if (line.contains(_BlockEnd)) {
            return true;
        }
        return false;
    }

    /**
     * 対象行が関数宣言行として除外されるべき行か判定する.
     * <p>
     * @param line 対象行
     * @return true(除外行)/false(その他行)
     */
    public static boolean isContainsExclusion(String line) {
        for (String exclusion : _ExclusionWords) {
            if (line.contains(exclusion)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 対象行がコンストラクタかどうか判定する.
     * <p>
     * @param line 対象行
     * @param className クラス名
     * @return true:コンストラクタ行/false:コンストラクタ行でない
     */
    public static boolean isConstructor(String line, String className) {
        if (line.contains(className)) {
            return true;
        }
        return false;
    }

    /**
     * 関数宣言の必須条件である括弧を含んでいるか判定する.
     * <p>
     * @param line 対象行
     * @return true:含んでいる/false:含んでいない
     */
    public static boolean isNotContainEssentialWord(String line) {
        if (!line.contains(_EssentialWord)) {
            return true;
        }
        return false;
    }

    /**
     * 対象行が関数行となるかどうか判定する.
     * <p>
     * @param line 対象行
     * @return true:関数行/false:関数行ではない
     */
    public static boolean isMethodDeclare(String line) {
        for (String term : _MethodDetectionWords) {
            if (line.contains(term)) {
                return true;
            }
        }
        return false;
    }
}
