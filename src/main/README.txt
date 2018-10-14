このソースはCCN(循環的複雑度:CyclomaticComplexityNumber)を測定する為のコードです。

[諸注意]
javaのみ対応です。
UTF-8のエンコードのみ対応です。
入力値はデフォルトではカレントディレクトリを対象としています。
入力値によっては正常に動作しない可能性があります。

[FIXME]
・入力値が[C:\]だった場合、NullpointerExceptionが発生する
・対象が読み取り専用だった場合等、AccessDeniedExceptionが発生する

[TODO]
・入力値によって、CCNの条件を追加・現象させる
・条件のデフォルト値をプロパティファイルに変更する

