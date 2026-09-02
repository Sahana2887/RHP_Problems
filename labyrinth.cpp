#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>

using namespace std;

const int dr[4] = {0, -1, 0, 1};
const int dc[4] = {-1, 0, 1, 0};
const char dir_char[4] = {'L', 'U', 'R', 'D'};

void solve() {
    int R, C;
    if (!(cin >> R >> C)) return;

    vector<string> g(R);
    int start_r = -1, start_c = -1;
    int end_r = -1, end_c = -1;

    for (int r = 0; r < R; r++) {
        cin >> g[r];
        for (int c = 0; c < C; c++) {
            if (g[r][c] == 'A') {
                start_r = r;
                start_c = c;
            } else if (g[r][c] == 'B') {
                end_r = r;
                end_c = c;
            }
        }
    }

    vector<vector<int>> parent_dir(R, vector<int>(C, -1));
    queue<pair<int, int>> q;

    q.push({start_r, start_c});
    bool found = false;

    while (!q.empty()) {
        auto [row, col] = q.front();
        q.pop();

        if (row == end_r && col == end_c) {
            found = true;
            break;
        }

        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                if (g[nr][nc] == '.' || g[nr][nc] == 'B') {
                    g[nr][nc] = '#'; // Mark as visited
                    parent_dir[nr][nc] = i;
                    q.push({nr, nc});
                }
            }
        }
    }

    if (!found) {
        cout << "NO\n";
        return;
    }
    cout << "YES\n";
    string path = "";
    int curr_r = end_r, curr_c = end_c;

    while (curr_r != start_r || curr_c != start_c) {
        int d = parent_dir[curr_r][curr_c];
        path += dir_char[d];
        curr_r -= dr[d];
        curr_c -= dc[d];
    }

    reverse(path.begin(), path.end());

    cout << path.length() << "\n";
    cout << path << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
    return 0;
}