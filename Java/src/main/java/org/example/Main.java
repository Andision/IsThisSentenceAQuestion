package org.example;

//import com.robrua.nlp.bert.Bert;

import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
//    public float[] getBERTEmbedding(String str) {
//        try (Bert bert = Bert.load("com/robrua/nlp/easy-bert/bert-chinese-L-12-H-768-A-12")) {
//            // Embed some sequences
//            System.out.println(System.currentTimeMillis());
//            float[] embedding = bert.embedSequence("今天天气怎么样");
//            System.out.println(System.currentTimeMillis());
//            System.out.print('[');
//            for (int i = 0; i < embedding.length; i++) {
//                System.out.print(embedding[i] + ", ");
//            }
//            System.out.println(']');
//            return embedding;
//        }
//    }

    //  ".*(?:什么|哪|谁|哪儿|怎么|为什么|几|多少|怎么样|如何|何时|多久|多远|多高|多重|多大|多宽|多深|多早|多晚|多久能|多久才能|能不能|可不可以|好不好|行不行|是不是|对不对).*"
    private static String chineseMeasureWords = "人|个|支|枝|只|头|颗|粒|片|张|页|本|册|双|对|副|件|套|顶|辆|台|部|位|身|口|件|首|节|种|条|股|根|棵|座|户|家|栋|幢|盘|条|扇|架|把|项|枝|轮|回|款";
    private static String chineseUnits = "公斤|克|千克|吨|毫克|磅|英镑|盎司|克拉" +
            "米|分米|厘米|毫米|千米|公里|里|尺|寸|英里|英尺|英寸|" +
            "平方厘米|平方分米|平方米|平方|平|平米|亩|公顷|"+
            "升|毫升|立方米|立方厘米|立方分米|立方毫米|" +
            "千瓦|瓦|焦耳|千卡|卡路里|" +
            "年|月|日|时|分|秒|分钟|秒钟|世纪|" +
            "元|角|分|厘|" +
            "美元|日元|欧元|人民币|" +
            "度|弧度|分钟|秒钟|分|秒|" +
            "摄氏度|华氏度|度";
    private static String chineseNumeral = "十|百|千|万|十万|百万|千万|万万|亿|十亿|百亿|千亿|万亿|兆|十兆|百兆|千兆|万兆";
    private static String chineseAdverbOfDegree = "远|长|重|大|宽|深|久";
    private static String[] regexList = new String[]{
            ".*(什么|何时|何地|哪|谁|多少|怎么|怎样|是否|有无|可否).*",
            ".*(对不对|能不能|可不可以|可以不可以|是不是|行不行|需不需要|有没有).*",
            "(为什么|怎么|如何).*",
            ".*(吗)",
            ".*(几|多少)("+chineseNumeral+")?(" +
            chineseMeasureWords+'|'+chineseUnits+").*",
            ".*多("+chineseAdverbOfDegree+").*",
    };

    public static boolean isSentenceAQuestion(String sentence) {
        for (int i = 0; i < regexList.length; i++) {
            String pattern = regexList[i];
            if (Pattern.matches(pattern, sentence)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String sentence = "从北京出发，多久能到上海";
        System.out.println(isSentenceAQuestion(sentence) + " " + sentence);
    }
}