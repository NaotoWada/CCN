package main.input;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 入力有効チェック.
 * <p>
 * 入力値がパスディレクトリとして適切かチェックする.<br>
 * 入力として不適切な場合、IllegalArgumentExceptionをthrowする<br>
 *
 * 定義値はENUMとして{@code INVALID_ENUMS.java}に列挙する.<br>
 * @author Naoto Wada
 *
 */
public class Validator {

    /**
     * 入力値チェック.
     * @param elements
     */
    public static void valid(String[] elements) {
        for (String element : elements) {
            throwExceptionIfSpace(element);
            throwExceptionIfBlank(element);
            throwExceptionIfInvalidWord(element);
        }
    }

    /**
     * 入力文字列がスペースかどうか検査する.
     * <p>
     * スペースだった場合、IllegalArgumentExceptionをthrowする
     * @param element 入力文字列
     * @return true(スペース)/false(それ以外)
     */
    private static void throwExceptionIfSpace(String element) throws IllegalArgumentException {
        if (element.equals(InputConsts._Invalid_Space)) {
            throw new IllegalArgumentException(InputConsts._ErrorInputIsSpace + element);
        }
    }

    /**
     * 入力文字列が空文字かどうか検査する.
     * <p>
     * 空文字だった場合、IllegalArgumentExceptionをthrowする
     * @param element 入力文字列
     * @return true(空文字)/false(それ以外)
     */
    private static void throwExceptionIfBlank(String element) {
        if (element.equals(InputConsts._Invalid_Space)) {
            throw new IllegalArgumentException(InputConsts._ErrorInputIsSpace + element);
        }
    }

    /**
     * 禁則文字列を含んでいるか検査する.
     * <p>
     * 禁則文字列を含んだ場合、IllegalArgumentExceptionをthrowする
     * @param element
     */
    private static void throwExceptionIfInvalidWord(String element) throws IllegalArgumentException {
        for (String invalid : getInvalidWord()) {
            if (element.contains(invalid)) {
                throw new IllegalArgumentException(
                        InputConsts._ErrorInputContainsInvalidWord + element + InputConsts._IsContains + invalid + "]");
            }
        }
    }

    /**
     * 禁則文字の列挙型から、文字列を取得して返却する.
     * @return 禁則文字列リスト
     */
    private static List<String> getInvalidWord() {
        return Arrays.stream(INVALID_ENUMS.values()).map(s -> s.getWord()).collect(Collectors.toList());
    }
}
