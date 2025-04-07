package com.albert;

public class Problem2 {

    public static int minMaxDistance(int[] houses, int k) {
        if (k >= houses.length) {
            return 0;
        }

        return findMinMaxDistance(houses, k);
    }

    private static int findMinMaxDistance(int[] houses, int k) {
        int[] lockers = new int[k];
        int[] result = {Integer.MAX_VALUE};

        optimizedBacktrack(houses, lockers, 0, 0, result);

        return result[0];
    }

    private static void optimizedBacktrack(int[] houses, int[] lockers, int index, int start, int[] result) {
        // 기저 조건: 모든 로커를 배치한 경우
        if (index == lockers.length) {
            int maxDistance = calculateMaxDistance(houses, lockers, result[0]);
            result[0] = Math.min(result[0], maxDistance);
            return;
        }

        // 가지치기 - 남은 로커보다 선택할 수 있는 집이 적으면 중단
        if (houses.length - start < lockers.length - index) {
            return;
        }

        // 백트래킹 수행
        for (int i = start; i < houses.length; i++) {
            lockers[index] = i;

            // 같은 위치의 집은 건너뛰기
            if (i > 0 && i < houses.length - 1 && houses[i] == houses[i-1]) {
                continue;
            }

            optimizedBacktrack(houses, lockers, index + 1, i + 1, result);
        }
    }

    private static int calculateMaxDistance(int[] houses, int[] lockers, int currentBest) {
        int maxDistance = 0;

        for (int i = 0; i < houses.length; i++) {
            int house = houses[i];
            int minDistToLocker = Integer.MAX_VALUE;

            for (int lockerIdx : lockers) {
                int lockerPos = houses[lockerIdx];
                int distance = Math.abs(house - lockerPos);
                minDistToLocker = Math.min(minDistToLocker, distance);

                // 거리가 0이면 더 이상 계산할 필요 없음
                if (minDistToLocker == 0) {
                    break;
                }
            }

            maxDistance = Math.max(maxDistance, minDistToLocker);

            // 이미 최적값보다 크면 더 계산할 필요 없음
            if (maxDistance >= currentBest) {
                return Integer.MAX_VALUE;  // 최적값보다 크면 큰 값 반환
            }
        }

        return maxDistance;
    }

}
