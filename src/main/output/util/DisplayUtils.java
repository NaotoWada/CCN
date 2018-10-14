package main.output.util;

import main.dto.ClassDataObject;
import main.dto.ClassDataObject.MethodObject;
import main.output.OutPutConsts;

/**
 * ディスプレイ出力用のユーティルクラス.
 * <p>
 * @author Naoto Wada
 *
 */
public class DisplayUtils {

    /**
     * ヘッダー部分の作成
     * @param sb 追加されるStringBuilder
     */
    public static void appendHeader(StringBuilder sb) {
        appendWithComma(sb, OutPutConsts._Head_Num);
        appendWithComma(sb, OutPutConsts._Head_ClassName);
        appendWithComma(sb, OutPutConsts._Head_ClassSteps);
        appendWithComma(sb, OutPutConsts._Head_ClassMethodNum);
        appendWithComma(sb, OutPutConsts._Head_ClassAveCNN);

        appendWithComma(sb, OutPutConsts._Head_MethodName);
        appendWithComma(sb, OutPutConsts._Head_MethodLine);
        appendWithComma(sb, OutPutConsts._Head_CNN);

        appendWithLine(sb, OutPutConsts._Head_MethodMaxNest);
    }

    /**
     * クラス部分の追加
     * @param sb 追加されるStringBuilder
     * @param cnt
     * @param clazz
     */
    public static void appendClassPart(StringBuilder sb, int cnt, ClassDataObject clazz) {
        appendWithComma(sb, cnt);
        appendWithComma(sb, clazz.get_Name());
        appendWithComma(sb, clazz.get_Steps());
        appendWithComma(sb, clazz.get_Methods().size());
        appendWithComma(sb, clazz.get_AveCCN());
    }

    /**
     * メソッド部分の追加
     * @param sb 追加されるStringBuilder
     * @param mtd 関数オブジェクト
     */
    public static void appendMethodPart(StringBuilder sb, MethodObject mtd) {
        appendWithComma(sb, mtd.get_Name());
        appendWithComma(sb, mtd.get_Steps());
        appendWithComma(sb, mtd.get_CyclomaticComplexityNumber());

        appendWithLine(sb, mtd.get_MaxNest());
    }

    /**
     * 入力値を追加した後、カンマを追加する
     * @param sb 追加されるStringBuilder
     * @param word 追加文字列
     */
    private static void appendWithComma(StringBuilder sb, Object word) {
        sb.append(word);
        sb.append(OutPutConsts._Comma);
    }

    /**
     * 入力値を追加した後、改行\r\nを追加する
     * @param sb 追加されるStringBuilder
     * @param word 追加文字列
     */
    private static void appendWithLine(StringBuilder sb, Object word) {
        sb.append(word);
        sb.append(OutPutConsts._CarriageReturnLineFeed);
    }

}
