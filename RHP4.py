rows, cols = map(int, input().split())
grid = []
for _ in range(rows):
    grid.append(list(map(int, input().split())))
previous_row = grid[0]
for i in range(1, rows):
    current_row = []
    for j in range(cols):
        valid_choices = previous_row[:j] + previous_row[j+1:]
        best_score = grid[i][j] + max(valid_choices)
        current_row.append(best_score)
    previous_row = current_row
print(max(previous_row))