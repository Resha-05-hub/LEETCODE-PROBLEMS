import java.util.*;

public class Solution {

    // Disjoint Set / Union Find structure
    class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int findParent(int node) {
            if (parent[node] != node) {
                parent[node] = findParent(parent[node]);
            }
            return parent[node];
        }

        boolean connect(int a, int b) {
            int pa = findParent(a);
            int pb = findParent(b);

            if (pa == pb) return false;

            if (size[pa] < size[pb]) {
                parent[pa] = pb;
                size[pb] += size[pa];
            } else {
                parent[pb] = pa;
                size[pa] += size[pb];
            }

            return true;
        }

        boolean same(int a, int b) {
            return findParent(a) == findParent(b);
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        DSU base = new DSU(n);
        int minMandatory = Integer.MAX_VALUE;

        // First process mandatory edges
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int stability = edge[2];
            int mandatory = edge[3];

            if (mandatory == 0) continue;

            minMandatory = Math.min(minMandatory, stability);

            if (!base.connect(u, v)) {
                return -1;
            }
        }

        int[] baseParent = base.parent.clone();
        int[] baseSize = base.size.clone();

        final int minMand = minMandatory;

    java.util.function.IntPredicate possible = (target) -> {

    if (target > minMand) return false;

            DSU dsu = new DSU(n);
            dsu.parent = baseParent.clone();
            dsu.size = baseSize.clone();

            List<int[]> upgradeCandidates = new ArrayList<>();

            for (int[] edge : edges) {

                int u = edge[0];
                int v = edge[1];
                int stability = edge[2];
                int mandatory = edge[3];

                if (mandatory == 1) continue;

                if (stability >= target) {
                    dsu.connect(u, v);
                }
                else if (stability * 2 >= target) {
                    upgradeCandidates.add(new int[]{u, v});
                }
            }

            int upgradesLeft = k;

            for (int[] pair : upgradeCandidates) {

                int u = pair[0];
                int v = pair[1];

                if (dsu.same(u, v)) continue;

                if (upgradesLeft == 0) return false;

                dsu.connect(u, v);
                upgradesLeft--;
            }

            int leader = dsu.findParent(0);

            for (int i = 1; i < n; i++) {
                if (dsu.findParent(i) != leader) {
                    return false;
                }
            }

            return true;
        };

        int high = 0;
        for (int[] e : edges) {
            high = Math.max(high, e[2]);
        }

        high *= 2;
        int low = -1;

        while (low < high) {

            int mid = low + (high - low + 1) / 2;

            if (possible.test(mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
}