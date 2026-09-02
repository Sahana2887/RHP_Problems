#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#define ll long long int
using namespace std;

const int diff[5] = {0, -1, 0, 1, 0};

void dfs(int R, int C, int row, int col, vector<string> &g) {
    g[row][col] = '#';
    for (int i = 0; i < 4; i++) {
        int ar = row + diff[i];
        int ac = col + diff[i + 1];
        if (ar >= 0 && ar < R && ac >= 0 && ac < C && g[ar][ac] == '.') {
            dfs(R, C, ar, ac, g);
        }
    }
}
void solve(){
    int R, C; cin >> R >> C;
    vector<string> g(R);
    for (int r = 0; r < R; r++) {
        cin >> g[r];
    }
    int rooms = 0;
    for (int row = 0; row < R; row++) {
        for (int col = 0; col < C; col++) {
            if (g[row][col] == '.') {
                rooms++;
                dfs(R, C, row, col, g);
            }
        }
    }
    cout << rooms << endl;
} 

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int tc = 1;
    while (tc--) solve();
}