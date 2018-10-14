package main.method.detect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.method.collect.MethodCollector;

/**
 * 関数の抽出を行うクラス.
 * <p>
 * @author Naoto Wada
 *
 */
public class MethodDetector {

    /** 分割用 丸括弧 .*/
    private static final String _SplitBracket = "\\(";

    /** 分割用 スペース .*/
    private static final String _SplitSpace = " ";

    /**
     * クラスの全行から、関数毎の行のみを抽出する.
     * <p>
     * @param classInfo クラスデータオブジェクト
     * @param wholeLine クラス全行
     * @return Map<関数名、関数の内容行>
     */
    public static Map<String, List<String>> accumulate(String className, List<String> wholeLine) {

        Map<String, List<String>> methodMap = new HashMap<>();
        for (String line : wholeLine) {
            if (isMethodDeclareLine(className, line)) {
                methodMap.put(getMethodName(line), MethodCollector.createMethodLine(line, wholeLine));
            }
        }
        return methodMap;
    }

    /**
     * 関数宣言行から、メソッド名のみ取得する.
     * <p>
     * 括弧の左側が関数名なので、括弧でスプリットして0番目の要素の一番最後の文字列を取得する.<br>
     * @param line 関数宣言行
     * @return 関数名
     */
    private static String getMethodName(String line) {
        String methodAndDeclare = line.split(_SplitBracket)[0];
        String[] declareArr = methodAndDeclare.split(_SplitSpace);
        return declareArr[declareArr.length - 1];
    }

    /**
     * 対象行が関数行かどうか判定する.
     * <p>
     * @param className クラス名
     * @param line 対象行
     * @return true:関数宣言行である/false:関数宣言行でない
     */
    private static boolean isMethodDeclareLine(String className, String line) {

        // クラス宣言、フィールド宣言等は除外
        if (DetectorUtils.isContainsExclusion(line)) {
            return false;
        }

        // コンストラクタは除外
        if (DetectorUtils.isConstructor(line, className)) {
            return false;
        }

        // 引数の括弧は必ず記載される為、記載なしは除外
        if (DetectorUtils.isNotContainEssentialWord(line)) {
            return false;
        }

        // 残った要素で特定条件ならメソッド宣言行
        if (DetectorUtils.isMethodDeclare(line)) {
            return true;
        }

        return false;
    }
}
