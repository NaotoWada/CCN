CCN
====
javaのCCN(循環的複雑度:CyclomaticComplexityNumber)を測定します。

## Description
javaソースコード(utf-8)を文字列として読み取り、if/forなどの分岐を計算します。  
各関数事のCCNをテキストデータとして出力します。  
ツール等でも計算できますが、ツールを導入できない環境での簡易的な調査として使用されることを想定しています。  
  
CCNの出し方はこちらを参照しました。  
[CA1502:メソッドの実装を複雑にしすぎないでください](https://docs.microsoft.com/ja-jp/visualstudio/code-quality/ca1502-avoid-excessive-complexity?view=vs-2015)

## Demo
|項番|クラス名|クラス行数|メソッド数|平均CNN|メソッド名|メソッド行数|CNN|最大ネスト数|
|---|---|---|---|---|---|---|---|---|
|1|MesurementCCN|106|4|1.75|main|9|3|3|
|2|MesurementCCN|106|4|1.75|doSleepForOutput|7|1|3|
|3|MesurementCCN|106|4|1.75|execute|16|1|1|
|4|MesurementCCN|106|4|1.75|canRetryExecute|17|2|3|
|…|…|…|…|…|…|…|…|…|
|58|Display|81|6|1.33|makeHeader|3|1|1|
|59|Display|81|6|1.33|disp|3|1|1|
|60|Display|81|6|1.33|makeContents|10|3|3|

## Requirement
Windows環境のみ動作確認  
* lombok jar == 1.16.16

## Usage
[MesurementCCN.java](https://github.com/NaotoWada/CCN/blob/master/src/main/execute/MesurementCCN.java)を実行して、計算対象のフォルダの絶対パスを指定して下さい。

## Author

[NaotoWada](https://github.com/NaotoWada)

