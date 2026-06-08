text = input("Enter a string: ")
n = len(text)

comb = 2**n
for i in range(1, comb):
    s = ""
    for j in range(n):
        if (i & (1 << j)) > 0:
            s += text[j]
    print(s)