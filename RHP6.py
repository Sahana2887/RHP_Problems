R = int(input())
C = int(input())
    
grid = []
for _ in range(R):
    row_data = [int(x) for x in input().split()]
    grid.append(row_data)
row = int(input())
col = int(input())
    
diff = [
    (-1, -1), (-1, 0), (-1, 1),
    (0, -1),           (0, 1),
    (1, -1),  (1, 0),  (1, 1)
]
    
total_sum = 0
for dr, dc in diff:
    adj_row = row + dr
    adj_col = col + dc
    if 0 <= adj_row < R and 0 <= adj_col < C:
        total_sum += grid[adj_row][adj_col]
print(total_sum)