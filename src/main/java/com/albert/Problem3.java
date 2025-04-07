package com.albert;

import java.util.*;

public class Problem3 {
    public static int calculateFenceArea(String[] animals) {
        // 1. 동물 위치 수집
        List<int[]> positions = new ArrayList<>();
        for (int i = 0; i < animals.length; i++) {
            for (int j = 0; j < animals[i].length(); j++) {
                if (animals[i].charAt(j) == 'a') {
                    positions.add(new int[]{i, j});
                }
            }
        }

        int n = positions.size();

        // 동물이 1마리인 경우
        if (n == 1) {
            return 1;
        }

        // 경계값 찾기 (한 번만 계산)
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        for (int[] pos : positions) {
            minRow = Math.min(minRow, pos[0]);
            maxRow = Math.max(maxRow, pos[0]);
            minCol = Math.min(minCol, pos[1]);
            maxCol = Math.max(maxCol, pos[1]);
        }

        // 모든 동물이 같은 행이나 열에 있는 경우
        boolean sameRow = true;
        boolean sameCol = true;
        int firstRow = positions.get(0)[0];
        int firstCol = positions.get(0)[1];

        for (int i = 1; i < n; i++) {
            int[] pos = positions.get(i);
            if (pos[0] != firstRow) sameRow = false;
            if (pos[1] != firstCol) sameCol = false;
            if (!sameRow && !sameCol) break; // 둘 다 false면 더 확인할 필요 없음
        }

        if (sameRow || sameCol) {
            return Math.max(maxCol - minCol + 1, maxRow - minRow + 1);
        }

        // 직사각형 검사 - 최적화된 방법
        int rectArea = (maxRow - minRow + 1) * (maxCol - minCol + 1);
        // 직사각형이면 동물 수가 직사각형 영역과 일치해야 함
        if (rectArea == n) {
            return rectArea;
        }

        // MST 계산을 위한 Set으로 최종 그리드 위치 추적
        Set<String> gridPoints = new HashSet<>();

        // 모든 동물 위치를 Set에 추가
        for (int[] pos : positions) {
            gridPoints.add((pos[0] - minRow) + "," + (pos[1] - minCol));
        }

        // MST 계산
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    int[] a = positions.get(i);
                    int[] b = positions.get(j);
                    dist[i][j] = Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
                }
            }
        }

        boolean[] visited = new boolean[n];
        int[] key = new int[n];
        int[] parent = new int[n];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;

        for (int count = 0; count < n; count++) {
            int minKey = Integer.MAX_VALUE;
            int u = -1;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && key[v] < minKey) {
                    minKey = key[v];
                    u = v;
                }
            }

            if (u == -1) break; // 연결 불가능한 경우 처리

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && dist[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = dist[u][v];
                }
            }
        }

        // MST에 따라 경로 추가 (모든 점 추적을 Set으로 최적화)
        for (int i = 1; i < n; i++) {
            int[] from = positions.get(parent[i]);
            int[] to = positions.get(i);

            int startRow = from[0] - minRow;
            int startCol = from[1] - minCol;
            int endRow = to[0] - minRow;
            int endCol = to[1] - minCol;

            // 수직 경로 추가
            int dr = Integer.compare(endRow, startRow);
            for (int r = startRow; r != endRow + (dr == 0 ? 1 : 0); r += (dr == 0 ? 1 : dr)) {
                gridPoints.add(r + "," + startCol);
            }

            // 수평 경로 추가
            int dc = Integer.compare(endCol, startCol);
            for (int c = startCol; c != endCol + (dc == 0 ? 1 : 0); c += (dc == 0 ? 1 : dc)) {
                gridPoints.add(endRow + "," + c);
            }
        }

        // Set의 크기가 곧 영역
        return gridPoints.size();
    }
}

