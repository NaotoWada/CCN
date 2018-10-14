package main.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ファイルをオープンするクラス
 * <p>
 * 特定のディレクトリを再帰的にオープンさせる.<br>
 *
 * @author Naoto Wada
 *
 */
public class FileOpener {

    /** 開く対象の拡張子 .*/
    private static final String _Extension = "java";

    /** 開く対象ディレクトリ .*/
    // TODO : できればプロパティファイルにしたい
    private static final Path _Root = Paths.get(".");

    /** 拡張子のドット部分 .*/
    private static final String _SplitDot = "\\.";

    /**
     * 入力のルートパスから特定拡張子のファイルのみを取得して、ファイル毎にまとめる.
     * <p>
     * @return Map<クラス名、ファイルの全行>
     */
    public static Map<String, List<String>> open() {

        Map<String, List<String>> javaLineMap = new HashMap<String, List<String>>();
        try {
            // 特定のファイルの全読み込み
            List<Path> pathList = FileOpener.readDir(_Root);

            // テキストベースでCCNの計測をしたので、内容を展開して各ファイル毎にまとめる
            javaLineMap = openJavaFile(pathList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaLineMap;
    }

    /**
     * ファイルリストからファイル名をキーに、全行抽出.
     * <p>
     * @param pathList クラスパスリスト
     * @return Map<クラス名、ファイルの全行>
     * @throws IOException [readAllLines]に依存したスロー。入力パスが読み込みできない場合スローされる<br>
     */
    private static Map<String, List<String>> openJavaFile(List<Path> pathList) throws IOException {

        Map<String, List<String>> javaLineMap = new HashMap<>();
        for (Path path : pathList) {
            javaLineMap.put(getClassName(path), Files.readAllLines(path));
        }
        return javaLineMap;
    }

    /**
     * クラス名の取得.
     * <p>
     * パス名を拡張子宣言のドットで区切って、ファイル名のみ取得する.
     *
     * @param path パス名
     * @return クラス名
     */
    private static String getClassName(Path path) {
        return path.getFileName().toString().split(_SplitDot)[0];
    }

    /**
     * ディレクトリの再帰読み込み.
     * <p>
     * ファイル名の拡張子を指定する
     *
     * @param dirPath ファイルパスを取得するディレクトリ
     * @return ファイルパスリスト
     * @throws IOException [walk]に依存したスロー
     */
    private static List<Path> readDir(Path dirPath) throws IOException {

        return Files.walk(dirPath)
                .filter(s -> s.getFileName().toString().contains(_Extension))
                .collect(Collectors.toList());
    }
}
