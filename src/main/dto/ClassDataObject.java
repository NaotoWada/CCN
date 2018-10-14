package main.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * クラスデータオブジェクト.
 *
 * @author Naoto Wada
 *
 */
@Data
public class ClassDataObject {

    /** クラス名 .*/
    private String _Name;

    /** クラスの総ステップ数 .*/
    private int _Steps;

    /** クラス全体のCCN .*/
    private double _AveCCN;

    /** クラス内のメソッド情報リスト .*/
    private List<MethodObject> _Methods = new ArrayList<>();

    /**
     * コンストラクタ.
     * <p>
     * クラス名は予め貰っておく
     * @param className クラス名
     */
    public ClassDataObject(String className) {
        this._Name = className;
    }

    /**
     * メソッドオブジェクトのインスタンス生成.
     * @return メソッドオブジェクト
     */
    public MethodObject newMethod() {
        return this.new MethodObject();
    }

    /**
     * 関数オブジェクト.
     *
     * @author Naoto Wada
     *
     */
    @Data
    public class MethodObject { // クラスデータの内部データなので、本当はprivateにしたい

        /** 関数名 .*/
        private String _Name;

        /** 関数の総ステップ数 .*/
        private int _Steps;

        /** クラスの最大ネスト数 .*/
        private int _MaxNest;

        /** 循環的複雑度 .*/
        private int _CyclomaticComplexityNumber;
    }
}