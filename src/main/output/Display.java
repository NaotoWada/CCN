package main.output;

import java.util.List;

import main.dto.ClassDataObject;
import main.dto.ClassDataObject.MethodObject;
import main.output.util.DisplayUtils;

/**
 * 結果出力用のクラス.
 * <p>
 * 出力結果はコンソールに出力する.<br>
 * @author Naoto Wada
 *
 */
public class Display {

    /**
     * 結果を出力する.
     * @param classList 出力対象
     */
    public static void disp(List<ClassDataObject> classList) {
        System.out.println(makeResultWord(classList));
    }

    /**
     * 結果を文字列として生成する.
     * @param classList 結果対象
     * @return 結果文字列
     */
    private static String makeResultWord(List<ClassDataObject> classList) {

        StringBuilder sb = new StringBuilder();
        makeHeader(sb);
        makeContents(sb, classList);
        return sb.toString();
    }

    /**
     * ヘッダー部分の作成.
     * @param sb 作成物を追加する対象
     */
    private static void makeHeader(StringBuilder sb) {
        DisplayUtils.appendHeader(sb);
    }

    /**
     * 内容部分の作成.
     * @param sb 作成物を追加する対象
     * @param classList 作成対象
     */
    private static void makeContents(StringBuilder sb, List<ClassDataObject> classList) {
        int cnt = 0;
        for (ClassDataObject clazz : classList) {
            for (MethodObject mtd : clazz.get_Methods()) {
                cnt++;
                DisplayUtils.appendClassPart(sb, cnt, clazz);
                DisplayUtils.appendMethodPart(sb, mtd);
            }
        }
    }
}
