package main.execute;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import main.classes.ClassDataCreator;
import main.dto.ClassDataObject;
import main.file.FileOpener;
import main.output.Display;

/**
 * CCN(CyclomaticComplexityNumber:循環的複雑度)を測定するクラス.
 * <p>
 * 計測は関数毎に行う.<br>
 * if文やfor文、case文の該当箇所の合計に 1 追加した数がCCNとなる.<br>
 * 関数のネストが深い場合でも、CCNに影響は出ないがネスト数もメトリックスとして計測しておく.<br>
 *
 * 出力形式は、エクセルファイルに張り付ける事を想定してデータ毎にカンマ","区切りで出力する.<br>
 * コンソール画面に出力した結果をコピペする事で使用する.<br>
 *
 * @author Naoto Wada
 *
 */
public class MesurementCCN {

    /**
     * メイン処理
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {

        execute();
    }

    /**
     * 実行関数
     */
    private static void execute() {

        // テキストベースでCCNの計測をしたいので、内容を展開して各ファイル毎に全行展開してまとめる
        Map<String, List<String>> javaLineMap = FileOpener.open();

        // クラス単位でまとめて、出力展開しやすくする
        List<ClassDataObject> classList = ClassDataCreator.create(javaLineMap);

        Display.disp(classList);
    }
}
