str_val = input().strip()
n = len(str_val)
first = [-2] * (2 * n + 1)
offset = n
first[offset] = -1
curr = 0
max_len = 0
for i in range(n):
    if str_val[i] == '1':
        curr += 1
    else:
        curr -= 1
    idx = curr + offset
    if first[idx] != -2:
        max_len = max(max_len, i - first[idx])
    else:
        first[idx] = i
print(max_len)