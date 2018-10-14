package main.input;

/**
 * ユーザー入力文字列に対する禁則文字の列挙型.
 * <p>
 * @author Naoto Wada
 *
 */
public enum INVALID_ENUMS {

    /** 禁則文字[\] .*/
    Invalid_Escape("\\"),
    /** 禁則文字[/] .*/
    Invalid_Slash("/"),
    /** 禁則文字[*] .*/
    Invalid_Times("*"),
    /** 禁則文字[?] .*/
    Invalid_Question("?"),
    /** 禁則文字[<] .*/
    Invalid_Bracket_Left("<"),
    /** 禁則文字[>] .*/
    Invalid_Bracket_Right(">"),
    /** 禁則文字[|] .*/
    Invalid_Pipe("|"),;

    /** 禁則文字列 .*/
    private final String word;

    /**
     * enumコンストラクタ
     * @param word
     */
    INVALID_ENUMS(String word){
        this.word = word;
    }

    /**
     * 禁則文字列の取得
     * @return
     */
    public String getWord(){
        return this.word;
    }
}
