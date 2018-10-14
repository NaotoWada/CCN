package main.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.dto.ClassDataObject;
import main.dto.ClassDataObject.MethodObject;
import main.method.count.CCNCounter;
import main.method.manage.MethodManager;

/**
 * クラスデータを生成するクラス.
 * <p>
 * @author Naoto Wada
 *
 */
public class ClassDataCreator {

    /**
     * クラス毎のデータをリストにして生成する.
     * <p>
     * @param javaLineMap<クラス名、クラスの全内容>
     * @return クラスデータリスト
     */
    public static List<ClassDataObject> create(Map<String, List<String>> javaLineMap) {
        // 各ファイル単位でループさせる
        List<ClassDataObject> classList = new ArrayList<>();
        for (Entry<String, List<String>> classMap : javaLineMap.entrySet()) {

            ClassDataObject classInfo = ClassDataCreator.create(classMap.getKey(), classMap.getValue());
            setMethodInfo(classInfo, classMap.getValue());
            setAveCNN(classInfo);
            classList.add(classInfo);
        }
        return classList;
    }

    private static void setAveCNN(ClassDataObject classInfo) {
        List<MethodObject> methods = classInfo.get_Methods();
        classInfo.set_AveCCN(CCNCounter.countAve(methods));
    }

    /**
     * 関数オブジェクトをクラスデータオブジェクトにセットする.
     * @param classInfo クラスデータオブジェクト
     * @param wholeList クラスの全内容
     */
    private static void setMethodInfo(ClassDataObject classInfo, List<String> wholeList) {
        classInfo.set_Methods(MethodManager.createMethodList(classInfo, wholeList));
    }

    /**
     * クラスデータを生成する.
     * <p>
     * クラスが保持する関数部分は生成されない
     *
     * @param className 生成するクラス名
     * @param wholeLine クラスの全内容
     * @return 生成したクラスデータ
     */
    private static ClassDataObject create(String className, List<String> wholeLine) {
        ClassDataObject classInfo = new ClassDataObject(className);
        setLines(classInfo, wholeLine);

        return classInfo;
    }

    /**
     * クラスの行数をカウントしてクラスデータに設定する.
     * @param retClass 設定するクラス
     * @param wholeLine クラスのステップ数
     */
    private static void setLines(ClassDataObject classInfo, List<String> wholeLine) {
        classInfo.set_Steps(wholeLine.size());
    }
}
