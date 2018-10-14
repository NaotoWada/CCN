package main.method.manage;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.dto.ClassDataObject;
import main.dto.ClassDataObject.MethodObject;
import main.method.count.CCNCounter;
import main.method.count.NestCounter;
import main.method.count.StepCounter;
import main.method.detect.MethodDetector;

/**
 * 関数のパラメータをハンドリングする.
 * <p>
 * @author Naoto Wada
 *
 */
public class MethodManager {

    /**
     * クラスデータの関数オブジェクト部分を生成する.
     * <p>
     * @param classInfo クラスデータ
     * @param wholeLine クラス全行
     * @return 関数オブジェクトリスト
     */
    public static List<MethodObject> createMethodList(ClassDataObject classInfo, List<String> wholeLine) {

        // 関数名毎の内容を抽出してMapにする事でループで扱いやすくする
        Map<String, List<String>> methodMap = MethodDetector.accumulate(classInfo.get_Name(), wholeLine);
        List<MethodObject> methods = createMethods(classInfo, methodMap);

        return methods;
    }

    /**
     * 関数リストの生成.
     * @param classInfo クラスデータオブジェクト
     * @param methodMap 関数MAP<関数名、関数の全行>
     * @return 関数リスト
     */
    private static List<MethodObject> createMethods(ClassDataObject classInfo, Map<String, List<String>> methodMap) {
        List<MethodObject> retMethods = classInfo.get_Methods();
        for (Entry<String, List<String>> methodNameContents : methodMap.entrySet()) {

            MethodObject method = classInfo.newMethod();
            setParams(method, methodNameContents.getKey(), methodNameContents.getValue());
            retMethods.add(method);
        }
        return retMethods;
    }

    /**
     * 関数データのパラメータをセットする.
     * @param method 関数データ
     * @param mName 関数名
     * @param mContents 関数の行内容
     */
    private static void setParams(MethodObject method, String mName, List<String> mContents) {

        setName(method, mName);
        setSteps(method, mContents);
        setMaxNest(method, mContents);
        setCCN(method, mContents);
    }

    /**
     * 関数名のセット
     * @param method 関数オブジェクト
     * @param mName 関数名
     */
    private static void setName(MethodObject method, String mName) {
        method.set_Name(mName);
    }

    /**
     * ステップ数のセット
     * @param method 関数オブジェクト
     * @param mContents ステップ数
     */
    private static void setSteps(MethodObject method, List<String> mContents) {
        method.set_Steps(StepCounter.count(mContents));
    }

    /**
     * 最大ネスト数のセット
     * @param method 関数オブジェクト
     * @param lines 関数内容
     */
    private static void setMaxNest(MethodObject method, List<String> mContents) {
        method.set_MaxNest(NestCounter.countMaxNest(mContents));
    }

    /**
     * CCNのセット
     * @param method 関数オブジェクト
     * @param mContents 関数内容
     */
    private static void setCCN(MethodObject method, List<String> mContents) {
        method.set_CyclomaticComplexityNumber(CCNCounter.countCCN(mContents));
    }
}
