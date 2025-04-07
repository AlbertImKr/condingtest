package com.albert;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Problem1 {

    public static int minOperations(int a, int b, int n) {
        if (a == b) {
            return 0;
        }

        if (a > b) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a, 0, 0, 0});

        Set<String> visited = new HashSet<>();
        visited.add(a + "," + 0 + "," + 0);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            long value = current[0];
            int lastOp = current[1];
            int consecutiveCount = current[2];
            int operations = current[3];

            for (int opIdx = 1; opIdx <= 3; opIdx++) {
                long newValue;

                // 선택한 연산 적용
                if (opIdx == 1) {
                    newValue = value + 3L;
                } else if (opIdx == 2) {
                    newValue = value * 2;
                } else {
                    newValue = value * 4;
                }

                // 새 값이 목표값보다 작거나 같은 경우만 고려
                if (newValue <= b) {
                    // 이전과 같은 연산이면 횟수 증가, 다른 연산이면 1로 초기화
                    int newConsecutiveCount = (opIdx == lastOp) ? consecutiveCount + 1 : 1;

                    // 같은 연산이 연속 n번 초과하여 사용되지 않는 경우만 진행
                    if (!(opIdx == lastOp && newConsecutiveCount > n)) {
                        // 목표값에 도달했다면 현재까지의 연산 횟수 + 1 반환
                        if (newValue == b) {
                            return operations + 1;
                        }

                        String stateKey = newValue + "," + opIdx + "," + newConsecutiveCount;

                        if (!visited.contains(stateKey)) {
                            visited.add(stateKey);
                            queue.add(new int[]{(int) newValue, opIdx, newConsecutiveCount, operations + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }
}
