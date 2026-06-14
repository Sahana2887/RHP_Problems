M, N = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
a.sort()
b.sort()
count = 0
ai = M - 1
bi = N - 1
while ai >= 0 and bi >= 0:
    if b[bi] <= a[ai] * 2:
        count += 1
        ai -= 1
        bi -= 1
    else:
        bi -= 1
print(count)