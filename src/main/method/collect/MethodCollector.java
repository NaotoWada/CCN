package main.method.collect;

import java.util.ArrayList;
import java.util.List;

import main.method.detect.DetectorUtils;

/**
 * 関数データの作成・収集を行うクラス.
 * <p>
 * @author Naoto Wada
 *
 */
public class MethodCollector {

    /**
     * クラス全行から、関数行のみを対象に抽出してリスト生成する.
     * <p>
     * @param detected 検出された関数行。スキップ用
     * @param wholeLine クラス全行
     * @return 検出された関数のみの全行リスト
     */
    public static List<String> createMethodLine(String detected, List<String> wholeLine) {

        List<String> fromDetectLine = createWholeLinesAfterDetected(detected, wholeLine);

        return createOnlyMethodPart(fromDetectLine);
    }

    /**
     * 関数部分のみを文字列リストとして生成する.
     * <p>
     * @param fromDetectLine 関数宣言行から全行含むリスト
     * @return 関数のみの文字列リスト
     */
    private static List<String> createOnlyMethodPart(List<String> fromDetectLine) {

        // FIXME:全体的にもっさりしているので改修したい
        List<String> methodLine = new ArrayList<>();
        List<Boolean> blocks = new ArrayList<>();
        blocks.add(true);
        int blockCnt = 0;

        for (String line : fromDetectLine) {

            methodLine.add(line);

            if (DetectorUtils.isBlockStart(line)) {
                blocks.add(true);
                blockCnt++;
            }

            if (DetectorUtils.isBlockEnd(line)) {
                blocks.set(blockCnt - 1, false);
                blockCnt--;
            }

            // メソッドの終了
            if (!blocks.get(0)) {
                break;
            }
        }
        return methodLine;
    }

    /**
     * 入力の文字列リストから、指定された行までスキップした文字列リストを返却する.
     * <p>
     * 入力例:入力文字列["", "", "a", "abc", "abc", ""], 指令行["abc"] <br>
     * 出力例:出力文字列["abc", "abc", ""]<br>
     *
     * @param detected 検出行
     * @param wholeLine 全行
     * @return スキップ後リスト
     */
    private static List<String> createWholeLinesAfterDetected(String detected, List<String> wholeLine) {

        boolean hasDetected = false;
        List<String> retList = new ArrayList<>();
        for (String line : wholeLine) {

            if (hasDetected) {
                retList.add(line);
                continue;
            }
            if (line.equals(detected)) {
                hasDetected = true;
                retList.add(line);
            }

        }
        return retList;
    }

}
