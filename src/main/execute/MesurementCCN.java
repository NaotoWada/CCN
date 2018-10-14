package main.execute;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import main.classes.ClassDataCreator;
import main.dto.ClassDataObject;
import main.file.FileOpener;
import main.input.InputReader;
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

    /** リトライ回数 .*/
    private static int _retryCnt = 0;

    /** リトライ回数上限 .*/
    private static int _RetryLimit = 3;

    /** スタックトレース出力用の停止秒数 .*/
    private static long _StopForStackTrace = 100L;
    /**
     * メイン処理
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {

        // 3回リトライ失敗したら終了する
        while (true) {
            if(!canRetryExecute()){
                break;
            }
        }
    }

    /**
     * 入力エラーだが再実行可能である場合、実行可能を返却する.
     * <p>
     * @return true(実行可能)/false(実行不可)
     */
    private static boolean canRetryExecute() {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();

            doSleepForOutput();

            _retryCnt++;

            if (_retryCnt < _RetryLimit) {
                System.out.println("★★★ 入力エラーの為、再入力してください。");
                return true;
            }
        }
        return false;
    }

    /**
     * スタックトレース用のスリープ処理
     */
    private static void doSleepForOutput() {
        try {
            Thread.sleep(_StopForStackTrace);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 実行関数
     * @throws IOException
     */
    private static void execute() throws IOException {

        Display.echoStartLog();

        Path userInputPath = InputReader.readPath();

        // テキストベースでCCNの計測をしたいので、内容を展開して各ファイル毎に全行展開してまとめる
        Map<String, List<String>> javaLineMap = FileOpener.openBy(userInputPath);

        // クラス単位でまとめて、出力展開しやすくする
        List<ClassDataObject> classList = ClassDataCreator.create(javaLineMap);

        Display.echoEndLog();

        Display.disp(classList);
    }
}
