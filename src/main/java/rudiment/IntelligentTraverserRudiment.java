package rudiment;

import org.apache.commons.collections4.CollectionUtils;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntelligentTraverserRudiment {

    static List<Integer> handledNumbers = new ArrayList<>();

    public static void main(String[] args) {

        List<Integer> travelNumbers = Stream.iterate(1, i -> i + 1).limit(100).collect(Collectors.toList());
        boolean[] errorNumbers = new boolean[101];
        for (int i = 0; i < 20; i++) {
            errorNumbers[35 + i] = true;
        }
        for (int i = 0; i < 5; i++) {
            errorNumbers[83 + i] = true;
        }
        for (int i = 0; i < 15; i++) {
            errorNumbers[9 + i] = true;
        }
        for (int i = 0; i < 6; i++) {
            errorNumbers[93 + i] = true;
        }


        List<List<Integer>> skipNumbersSortedBySize = new ArrayList<>();
        handleAllNumbersRecursive(travelNumbers, errorNumbers, skipNumbersSortedBySize);

        compareHandleComplete(travelNumbers);

        // TODO: 2021/2/7 改装成接口式、非数字式的 （改装或者在核心上扩展外接）
    }

    public static void handleAllNumbersRecursive(List<Integer> travelNumbers, boolean[] errorNumbers, List<List<Integer>> skipNumbersSortedBySize) {
        if (CollectionUtils.isEmpty(travelNumbers)) {
            return;
        }
        System.out.println("当前处理分组为："+ JsonUtils.toPrettyString(travelNumbers));
        int forStep = 1;
        int accumulateErrorCount = 0;
        int lastIndex = 0;
        int maxIndex = travelNumbers.size()-1;
        for (int currentIndex = 0; currentIndex <= maxIndex; ) {
            Integer currentNumber = travelNumbers.get(currentIndex);
            handledNumbers.add(currentNumber);

            //保存跳过的部分
            if (currentIndex - lastIndex > 1) {
                List<Integer> skipPart = travelNumbers.subList(lastIndex+1, currentIndex);
                System.out.println("保存跳过的分组："+JsonUtils.toString(skipPart));
                skipNumbersSortedBySize.add(skipPart);
            }

            //判断是否成功来决定是否越过一段数据
            System.out.print("当前数字："+currentNumber+"，情况：");
            if (errorNumbers[currentNumber]) {
                System.out.println("处理失败--------------------------------");
                accumulateErrorCount++;
                forStep += accumulateErrorCount >= 2 ? forStep : 0;
            } else {
                System.out.println("正常处理");
                accumulateErrorCount = 0;
                forStep = 1;
            }

            //利用遍历越过一段数据
            if (currentIndex == maxIndex) {
                break;
            }
            lastIndex = currentIndex;
            currentIndex += forStep;
            if (currentIndex > maxIndex) {
                currentIndex = maxIndex;
            }
        }
        System.out.println("一轮结束后，未处理的分组为："+JsonUtils.toPrettyString(skipNumbersSortedBySize));

        //处理不成功的部分，应该倒序处理，因为处理成功的数据附近往往都是能处理成功的。
        //每次都优先处理最多被跳过的跨度
        if (skipNumbersSortedBySize.isEmpty()) {
            System.out.println("处理结束");
            return;
        }
        System.out.println();
        System.out.println();
        System.out.println("开始下一轮分组处理");
        skipNumbersSortedBySize.sort((o1, o2) ->o2.size() - o1.size());
        List<Integer> maxSizeSkipNumbers = skipNumbersSortedBySize.get(0);
        System.out.println("当前最大的被跳过分组为："+JsonUtils.toPrettyString(maxSizeSkipNumbers));
        skipNumbersSortedBySize.remove(maxSizeSkipNumbers);
        maxSizeSkipNumbers.sort((c1, c2) -> c2 - c1);
        handleAllNumbersRecursive(maxSizeSkipNumbers, errorNumbers, skipNumbersSortedBySize);
    }

    private static void compareHandleComplete(List<Integer> travelNumbers) {
        for (Integer travelNumber : travelNumbers) {
            if (!handledNumbers.contains(travelNumber)) {
                System.out.println("没有处理完："+travelNumber);
            }
        }
    }
}
