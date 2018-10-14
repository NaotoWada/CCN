package main.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ユーザー入力を受信するクラス.
 * <p>
 * 入力値が空文字の場合、空文字のPathを返却する.<br>
 * 入力値がパス形式でない場合、IOExceptionをスローする.<br>
 *
 * @author Naoto Wada
 *
 */
public class InputReader {

    /**
     * ユーザー入力を受信してパス形式で返却する.
     * @return 入力パス
     */
    public static Path readPath() {
        String[] dirs = DividePath.divide(read());
        Validator.valid(dirs);
        return createPath(dirs);
    }

    /**
     * 入力文字列配列をパスオブジェクトにして返却する.
     * <p>
     * @param dirs 入力文字列配列
     * @return パスオブジェクト
     */
    private static Path createPath(String[] dirs) {
        return Paths.get("", dirs);
    }

    /**
     * ユーザー入力を受信して文字列にして返却する.
     * <p>
     * @return
     */
    private static String read() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String input = in.readLine();
            return input;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
