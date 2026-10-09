
import java.util.*;

class Sofa {
    int fsr, fsc, ssr, ssc;
    char dir;
    int moves;

    public Sofa(int fsr, int fsc, int ssr, int ssc, char d, int m) {
        this.fsr = fsr;
        this.fsc = fsc;
        this.ssr = ssr;
        this.ssc = ssc;
        this.dir = d;
        this.moves = m;
    }
}

public class sofa {

    static final String DELIM = "-";

    private static boolean canAdd(int fsr, int fsc, int ssr, int ssc,
                                  char dir, Set<String> vis) {
        String key = fsr + DELIM + fsc + DELIM
                   + ssr + DELIM + ssc + DELIM + dir;

        if (vis.contains(key)) {
            return false;
        }

        vis.add(key);
        return true;
    }

    private static boolean free(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length
            && c >= 0 && c < grid[0].length
            && grid[r][c] != 'H';
    }

    private static boolean canPlace(char[][] grid, int r1, int c1,
                                    int r2, int c2) {
        return free(grid, r1, c1) && free(grid, r2, c2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int R = sc.nextInt();
        int C = sc.nextInt();

        char[][] grid = new char[R][C];

        int fsr = -1, fsc = -1, ssr = -1, ssc = -1;
        int sofaCount = 0, targetCount = 0;

        int tsr = -1, tsc = -1, ttr = -1, ttc = -1;

        Queue<Sofa> q = new LinkedList<>();

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                char ch = sc.next().charAt(0);
                grid[row][col] = ch;

                if (ch == 's') {
                    if (sofaCount == 0) {
                        fsr = row;
                        fsc = col;
                    } else {
                        ssr = row;
                        ssc = col;
                    }
                    sofaCount++;
                }

                if (ch == 'S') {
                    if (targetCount == 0) {
                        tsr = row;
                        tsc = col;
                    } else {
                        ttr = row;
                        ttc = col;
                    }
                    targetCount++;
                }
            }
        }

        if (sofaCount != 2 || targetCount != 2) {
            System.out.println("Impossible");
            return;
        }

        if (fsr == ssr && fsc > ssc) {
            int temp = fsc;
            fsc = ssc;
            ssc = temp;
        } else if (fsc == ssc && fsr > ssr) {
            int temp = fsr;
            fsr = ssr;
            ssr = temp;
        }

        if (tsr == ttr && tsc > ttc) {
            int temp = tsc;
            tsc = ttc;
            ttc = temp;
        } else if (tsc == ttc && tsr > ttr) {
            int temp = tsr;
            tsr = ttr;
            ttr = temp;
        }

        char startDir = (fsr == ssr) ? 'H' : 'V';
        char targetDir = (tsr == ttr) ? 'H' : 'V';

        q.add(new Sofa(fsr, fsc, ssr, ssc, startDir, 0));

        Set<String> vis = new HashSet<>();
        vis.add(fsr + DELIM + fsc + DELIM + ssr + DELIM + ssc
                + DELIM + startDir);

        while (!q.isEmpty()) {
            Sofa s = q.poll();

            if (s.fsr == tsr && s.fsc == tsc
                    && s.ssr == ttr && s.ssc == ttc) {
                System.out.println(s.moves);
                return;
            }

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                int nfsr = s.fsr + dr[i];
                int nfsc = s.fsc + dc[i];
                int nssr = s.ssr + dr[i];
                int nssc = s.ssc + dc[i];

                if (canPlace(grid, nfsr, nfsc, nssr, nssc)) {
                    if (canAdd(nfsr, nfsc, nssr, nssc,
                               s.dir, vis)) {
                        q.add(new Sofa(nfsr, nfsc, nssr, nssc,
                                       s.dir, s.moves + 1));
                    }
                }
            }

            if (s.dir == 'H') {
                int top = s.fsr - 1;
                int left = s.fsc;

                for (int r : new int[]{top, s.fsr}) {
                    if (r >= 0 && r + 1 < R
                            && clearSquare(grid, r, left)) {

                        if (canAdd(r, left, r + 1, left, 'V', vis)) {
                            q.add(new Sofa(r, left, r + 1, left,
                                           'V', s.moves + 1));
                        }

                        if (canAdd(r, left + 1, r + 1, left + 1,
                                   'V', vis)) {
                            q.add(new Sofa(r, left + 1,
                                           r + 1, left + 1,
                                           'V', s.moves + 1));
                        }
                    }
                }
            } else {
                int top = s.fsr;
                int left = s.fsc - 1;

                for (int c : new int[]{left, s.fsc}) {
                    if (c >= 0 && c + 1 < C
                            && clearSquare(grid, top, c)) {

                        if (canAdd(top, c, top, c + 1, 'H', vis)) {
                            q.add(new Sofa(top, c, top, c + 1,
                                           'H', s.moves + 1));
                        }
                        
                        if (canAdd(top + 1, c, top + 1, c + 1,
                                   'H', vis)) {
                            q.add(new Sofa(top + 1, c,
                                           top + 1, c + 1,
                                           'H', s.moves + 1));
                        }
                    }
                }
            }
        }

        System.out.println("Impossible");
    }

    private static boolean clearSquare(char[][] grid, int r, int c) {
        return free(grid, r, c)
            && free(grid, r, c + 1)
            && free(grid, r + 1, c)
            && free(grid, r + 1, c + 1);
    }
}
