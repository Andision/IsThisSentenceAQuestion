import re
import json

TEST_FILE_NAME = "C:/Users/Andision/Documents/GitHub/IsThisSentenceAQuestion/baike_qa_valid.json"

chineseMeasureWords = "人|个|支|枝|只|头|颗|粒|片|张|页|本|册|双|对|副|件|套|顶|辆|台|部|位|身|口|件|首|节|种|条|股|根|棵|座|户|家|栋|幢|盘|条|扇|架|把|项|枝|轮|回|款"
chineseUnits = "公斤|克|千克|吨|毫克|磅|英镑|盎司|克拉" + \
    "米|分米|厘米|毫米|千米|公里|里|尺|寸|英里|英尺|英寸|" + \
    "平方厘米|平方分米|平方米|平方|平|平米|亩|公顷|" + \
    "升|毫升|立方米|立方厘米|立方分米|立方毫米|" + \
    "千瓦|瓦|焦耳|千卡|卡路里|" + \
    "年|月|日|时|分|秒|分钟|秒钟|世纪|" + \
    "元|角|分|厘|" + \
    "美元|日元|欧元|人民币|" + \
    "度|弧度|分钟|秒钟|分|秒|" + \
    "摄氏度|华氏度|度"
chineseNumeral = "十|百|千|万|十万|百万|千万|万万|亿|十亿|百亿|千亿|万亿|兆|十兆|百兆|千兆|万兆"
chineseAdverbOfDegree = "远|长|重|大|宽|深|久"
regexList = [
    ".*(什么|何时|何地|哪|谁|多少|怎么|怎样).*",
    ".*(对不对|能不能|可不可以|可以不可以|是不是|行不行).*",
    "(为什么|怎么|如何).*",
    ".*(吗)",
    ".*(几|多少)("+chineseNumeral+")?(" +
    chineseMeasureWords+'|'+chineseUnits+").*",
    ".*多("+chineseAdverbOfDegree+").*",
]


def IsThisSentenceAQuestion(sentence: str) -> bool:
    for pattern in regexList:
        if re.match(pattern, sentence):
            return True
    return False


if __name__ == "__main__":
    countAll = 0
    countSuccess = 0
    with open(TEST_FILE_NAME, encoding='utf-8') as testFile:
        testObject = testFile.readline()
        while(testObject):
            try:
                testObjectRaw = testFile.readline()
                testObject = json.loads(testObjectRaw)

                sentence = str(testObject["desc"]).strip()
            except:
                continue
                
            if len(sentence) == 0:
                continue
            countAll += 1
            countSuccess += 1
            res = IsThisSentenceAQuestion(sentence)
            print("qid:{}, type:Quest., result:{}, sentence:{}".format(
                testObject["qid"], res, sentence[0:10]))
            if not res:
                print("full sentence:{}".format(sentence))
                countSuccess -= 1
            print("All:{}, Success:{}, Acc:{:.2f}".format(
                countAll, countSuccess, countSuccess/countAll))

            # sentence = testObject["answer"].split("。")[0]
            # res = IsThisSentenceAQuestion(sentence)
            # print("qid:{}, type:Answer, result:{}, sentence:{}".format(
            #     testObject["qid"], res, sentence[0:10]))
            # if res:
            #     print("full sentence:{}".format(sentence))

            # testObject = testFile.readline()

    # sentence = "结果如图"
    # print(IsThisSentenceAQuestion(sentence))
