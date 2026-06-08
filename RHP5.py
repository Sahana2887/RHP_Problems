rows, cols = map(int, input().split())
grid = []
for _ in range(rows):
    grid.append(list(map(int, input().split())))
previous_col = [grid[i][0] for i in range(rows)]
for j in range(1, cols):
    current_col = []
    for i in range(rows):
        valid_choices = previous_col[:i] + previous_col[i+1:]
        best_score = grid[i][j] + max(valid_choices)
        current_col.append(best_score)
    previous_col = current_col
print(max(previous_col))